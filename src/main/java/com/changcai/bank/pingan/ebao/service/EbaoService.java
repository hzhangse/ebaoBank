package com.changcai.bank.pingan.ebao.service;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changcai.bank.pingan.ebao.constant.ResponseCode;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.BizzMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.NetMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessage;


@Service
public class EbaoService {
    protected final Logger       LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected IEbaoClientInvoker ebaoTcpClientInvoker;

    public IEbaoClientInvoker getEbaoTcpClientInvoker() {
        return this.ebaoTcpClientInvoker;
    }

    public B2BMessage invokeEbaoService(BaseMessageBody reqBody, String funcId) {
        B2BMessage rspMsg = null;
        try {
            rspMsg = ebaoTcpClientInvoker.callRemoteService(reqBody, funcId);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            rspMsg = new ResponseMessage();
            rspMsg.setBizzHeader(new BizzMsgHeader());
            rspMsg.setNetHeader(new NetMsgHeader());
            try {
                rspMsg.setRspCode(ResponseCode.RES_ERR185.getCode());
                rspMsg.setRspMsg(e.getMessage());
            } catch (UnsupportedEncodingException e1) {
                LOGGER.error(e1.getMessage());
            }  
        }
        return rspMsg;
    }

    public B2BMessage invokeEbaoService(String reqBody) {
        B2BMessage rspMsg = null;
        try {
            rspMsg = ebaoTcpClientInvoker.callRemoteService(reqBody);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            rspMsg = new ResponseMessage();
            rspMsg.setBizzHeader(new BizzMsgHeader());
            rspMsg.setNetHeader(new NetMsgHeader());
            try {
                rspMsg.setRspCode(ResponseCode.RES_ERR185.getCode());
                rspMsg.setRspMsg(e.getMessage());
            } catch (UnsupportedEncodingException e1) {
                LOGGER.error(e1.getMessage());
            }  
        }
        return rspMsg;
    }
    public static void main(String[] args) throws Exception {
        //        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        //            JavaConfig.class);
        //        EbaoService service = context.getBean(EbaoService.class);
        //
        //        OutComeMoneyReqDto body = context.getBean(OutComeMoneyReqDto.class);
        //
        //        BaseMessageBody result = service.invokeEbaoService(body, MessageType.OUTCOME_MONEY.value());
        //        //        factory.setFuncId(MessageType.OUTCOME_MONEY.value());
        //        //        BaseMessageBody body = (BaseMessageBody) context.getBean(beanName);
        //
        //        System.out.println(result);
        //        context.close();
//        B2BMessage b= new B2BMessage();
//        b.setBizzHeader(new BizzMsgHeader());
//        b.setNetHeader(new NetMsgHeader());
//        b.setRspMsg("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//        System.out.println(b.getBizzHeader().getRspMsg());
//        System.out.println(b.getNetHeader().getRspMsg());
    }
}
