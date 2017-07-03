package com.changcai.bank.pingan.ebao.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.changcai.bank.pingan.ebao.annotation.Action;
import com.changcai.bank.pingan.ebao.constant.ResponseCode;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.BizzMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.MessageObjInput;
import com.changcai.bank.pingan.ebao.protocol.struct.NetMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessage;
import com.changcai.core.hessian.HessianHeaderContext;
import com.changcai.core.hessian.HessianHeaderContextKeys;
import com.changcai.netty.client.NettyTCPClient;
import com.changcai.netty.client.NettyTCPRequest;
import com.changcai.netty.client.NettyTCPResponse;
import com.changcai.netty.client.NettyTCPResponseFuture;

@Service("ebaoTcpClientInvoker")
public class EbaoTcpClientInvoker implements IEbaoClientInvoker {
    protected final Logger     LOGGER       = LoggerFactory.getLogger(this.getClass());

    private ApplicationContext context;

    @Value("${b2b.client.serverIPAddress}")
    private String             hostName;

    @Value("${b2b.client.serverPort}")
    private int                port;

    @Value("${b2b.client.maxIdleTime}")
    private int                maxIdleTimeInMilliSecondes;

    @Value("${b2b.client.connectTimeOut}")
    private int                connectTimeOutInMilliSecondes;

    @Value("${b2b.client.forceCreate}")
    private boolean            forceConnect;

    @Value("${b2b.client.poolSize}")
    protected int              poolSize;

    @Value("${b2b.client.retryReqSize}")
    protected int              retryReqSize = 1;

    @Value("${enableSendValidator}")
    protected boolean          enableSendValidator;

    @Value("${enableRecValidator}")
    protected boolean          enableRecValidator;

    @Value("${enableLog}")
    protected boolean          enableLog;

    protected NettyTCPClient   client       = null;

    @Autowired
    protected Validator        validator;  

    @Autowired
    private RspBodyFactoryBean rspBodyFac;

    @Autowired
    private ReqBodyFactoryBean reqBodyFac;
    
    @Value("${msgEncoding}")
    private Charset            charset;
    
   protected    ThreadLocal<RequestMessage>  reqMsgThreadLocal  = new ThreadLocal<RequestMessage>() {
        protected RequestMessage initialValue() {
            return null;
        }
    };
  
    protected    ThreadLocal<String>  logUUIDThreadLocal  = new ThreadLocal<String>() {
        protected String initialValue() {
           String uuid= HessianHeaderContext.getContext().getHeader(HessianHeaderContextKeys.LOG_TOKEN);
          if (StringUtils.isBlank(uuid)){
              uuid =  UUID.randomUUID().toString()+System.currentTimeMillis();
          }
           return uuid+":" ;
        }
    };
    
   
    public B2BMessage getReqMsg() {
        B2BMessage reqMsg =  this.reqMsgThreadLocal.get();
        if ( reqMsg ==null){
            reqMsg=  this.createReqMsg();
        }
        return reqMsg;
    }
    
    private RequestMessage createReqMsg() {
        RequestMessage request= context.getBean(RequestMessage.class);
        this.reqMsgThreadLocal.set(request);
        return request;
    }

    @PostConstruct
    public void init() throws Exception {
        InetSocketAddress serverAddr = new InetSocketAddress(hostName, port);
        Map<String, Integer> maxPerRoute = new HashMap<String, Integer>();
        maxPerRoute.put(serverAddr.getHostName() + ":" + serverAddr.getPort(), poolSize);

        client = new NettyTCPClient.ConfigBuilder()
            .maxIdleTimeInMilliSecondes(maxIdleTimeInMilliSecondes * 1000).maxPerRoute(maxPerRoute)
            .connectTimeOutInMilliSecondes(connectTimeOutInMilliSecondes * 1000)
            .forceConnect(forceConnect).build();

    }

    /**
     * 创建基于funcid 和 request body 的消息字节流,用于发送到银行方
     * 
     * @param reqBody
     * @param funcId
     * @return
     * @throws IOException
     */
    private byte[] genRequestByteArr(BaseMessageBody reqBody, String funcId) throws IOException {
        byte[] bodyReq = reqBody.writeMessage();
        //计算body长度
        int bodyLength = bodyReq.length;
        B2BMessage reqMsg = this.getReqMsg();
        reqMsg.setBody(reqBody);
        reqMsg.setTranFunc(funcId);
        //设置报文头消息长度的相关字段
        reqMsg.setRecMsgLength(bodyLength);
        if (enableSendValidator) {
            reqMsg.setValidator(validator);
        }
        byte[] req = reqMsg.writeMessage();

        if (enableLog) {
            String logUUID = logUUIDThreadLocal.get();
            LOGGER.info(logUUID + "Send request message to bank: "
                        + new String(req, reqMsg.getEncoding()));
        }
        return req;
    }
    
    
 
    @Action(name = "retryRequest")
    protected NettyTCPResponse retryRequest(byte[] req, int retrySize) {
        boolean isSuccess = false;
        NettyTCPResponse response = null;
        NettyTCPResponseFuture responseFuture = null;
        if (retrySize == -1) {
            retrySize = Integer.MAX_VALUE;
        }
        while (!isSuccess && retrySize > 0) {
            try {
                NettyTCPRequest request = new NettyTCPRequest(hostName, port);
                request.content(req);
                responseFuture = client.sendRequest(request);
                response = (NettyTCPResponse) responseFuture.get();
                if (response.isSuccess()) {
                    isSuccess = true;
                }
            } catch (InterruptedException | IOException | ExecutionException e) {
                String logUUID = logUUIDThreadLocal.get();
                LOGGER.error(logUUID + " send request exception:" + e.getMessage());
            }
            retrySize--;
        }
        return response;
    }

    /**
     *   交易网向银行发送请求
     * 
     * @param reqBody  请求参数dto
     * @param funcId  接口的func id
     * @return
     * @throws InterruptedException 
     * @throws ExecutionException 
     * @throws Exception
     */
    @Action(name = "callRemoteService")
    public B2BMessage callRemoteService(BaseMessageBody reqBody, String funcId) throws Throwable {
        try {
            int retrySize = this.retryReqSize;
            Assert.isTrue(reqBody != null, "request body should not be null!");
            Assert.isTrue(funcId != null, "func Id should  be set!");
            reqBody.setEncoding(charset.name());
//            charset = Charset.forName(reqBody.getEncoding());
            byte[] req = this.genRequestByteArr(reqBody, funcId);
            NettyTCPResponse response = retryRequest(req, retrySize);
            B2BMessage rspB2BMsg = getRspMsg(response, funcId);
            return rspB2BMsg;
        } catch (Throwable ex) {
            throw ex;
        }finally{
            this.reqMsgThreadLocal.set(null);
        }
    }

   
    /**
     * get  b2bMsg when  get successful response
     * 
     * @param response
     * @param funcId
     * @return
     * @throws Throwable 
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private B2BMessage getRspMsg(NettyTCPResponse response, String funcId) throws Throwable {
        B2BMessage rspMsg = new B2BMessage();

        Assert.notNull(response, "response must not be null");
        Assert.isTrue(response.isSuccess(), "does not get success response, reason:"+response.getResponseBody(charset));
        String rspStr = response.getResponseBody(charset);
        if (this.enableLog) {
            String logUUID = logUUIDThreadLocal.get();
            LOGGER.info(logUUID + " response message from bank:" + rspStr);
        }
        ByteArrayInputStream baips = new ByteArrayInputStream(rspStr.getBytes(charset));

        rspMsg.setNetHeader(new NetMsgHeader());
        rspMsg.setBizzHeader(new BizzMsgHeader());
        MessageObjInput in = new MessageObjInput(baips, rspMsg);

        rspMsg.getNetHeader().readExternal(in);
        if (!rspMsg.getNetHeader().getRspCode().equalsIgnoreCase(ResponseCode.RES_000000.getCode())) {
            //            throw new IOException(rspMsg.getNetHeader().getRspCode() + ":"
            //                                  + rspMsg.getNetHeader().getRspMsg());
            return rspMsg;
        }

        rspMsg.getBizzHeader().readExternal(in);
        if (!rspMsg.getBizzHeader().getRspCode()
            .equalsIgnoreCase(ResponseCode.RES_000000.getCode())) {
            //            throw new IOException(rspMsg.getBizzHeader().getRspCode() + ":"
            //                                  + rspMsg.getBizzHeader().getRspMsg());
            return rspMsg;
        }

        // rspMsg = (B2BMessage) in.readObject();

        //  if (rspMsg.getRspCode().trim().equalsIgnoreCase(ResponseCode.RES_000000.getCode()))

        Assert.isTrue(rspMsg.getTranFunc() != null, "tran func id should not be null!");
        String rspFuncId = rspMsg.getTranFunc();
        if (rspFuncId.equalsIgnoreCase(funcId)) {
            rspBodyFac.setFuncId(funcId);
            BaseMessageBody rspBody = (BaseMessageBody) context
                .getBean(RspBodyFactoryBean.beanName);
            Assert.notNull(rspBody, "response body must not be null");
            rspMsg.setBody(rspBody);
            rspBody.readExternal(in);

            if (enableRecValidator) {
                rspMsg.setValidator(validator);
                rspMsg.validateDto();
            }
            in.close();
        }
        return rspMsg;
    }

   /**
    *  convert req msg string to msg object
    * 
    * @param reqMsgStr
    * @return
    * @throws Throwable
    */
    public RequestMessage convertReqMsg(String reqMsgStr) throws Throwable {
    	RequestMessage reqMsg = new RequestMessage();
        Assert.notNull(reqMsgStr, "reqMsg must not be null");
        if (this.enableLog) {
            String logUUID = logUUIDThreadLocal.get();
            LOGGER.info(logUUID + " send req message to bank:" + reqMsgStr);
        }
        ByteArrayInputStream baips = new ByteArrayInputStream(reqMsgStr.getBytes(charset));

        reqMsg.setNetHeader(new NetMsgHeader());
        reqMsg.setBizzHeader(new BizzMsgHeader());
        MessageObjInput in = new MessageObjInput(baips, reqMsg);

        reqMsg.getNetHeader().readExternal(in);
       
        reqMsg.getBizzHeader().readExternal(in);
          
        Assert.isTrue(reqMsg.getTranFunc() != null, "tran func id should not be null!");
        String reqFuncId = reqMsg.getTranFunc();
        if (reqFuncId!=null) {
            reqBodyFac.setFuncId(reqFuncId);
            BaseMessageBody reqBody = (BaseMessageBody) context
                .getBean(ReqBodyFactoryBean.beanName);
            Assert.notNull(reqBody, "request body must not be null");
            reqMsg.setBody(reqBody);
            reqBody.readExternal(in);

            if (enableRecValidator) {
                reqMsg.setValidator(validator);
                reqMsg.validateDto();
            }
            if (this.enableLog) {
                String logUUID = logUUIDThreadLocal.get();
                LOGGER.info(logUUID + " send req message to bank json format:" + reqMsg.toJSONString());
            }
            in.close();
        }
        return reqMsg;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 释放socket连接
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            client.close();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    

    @Action(name = "callRemoteService")
    public B2BMessage callRemoteService(String reqStr) throws Throwable {
        try {
            B2BMessage rspB2BMsg = null;
            NettyTCPResponse response = null;
            int retrySize = this.retryReqSize;
            Assert.isTrue(reqStr != null, "request body should not be null!");
            RequestMessage reqMsg=  convertReqMsg(reqStr);
            String funcId= reqMsg.getTranFunc();
            Assert.isTrue(funcId != null, "func Id should  be set!");
            byte[] req = reqStr.getBytes(charset);
            response = retryRequest(req, retrySize);
            rspB2BMsg = getRspMsg(response, funcId);
            return rspB2BMsg;
        } catch (Throwable e) {
            String logUUID = logUUIDThreadLocal.get();
            LOGGER.error(logUUID + "  call remote service exception:" + e.getMessage());
            throw e;
        }

    }
    @Override
    public String getLogUUID() {
        return  logUUIDThreadLocal.get();
    }
}
