/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.BizzMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.MessageObjInput;
import com.changcai.bank.pingan.ebao.protocol.struct.NetMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.req.InComeMoney1310ReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.MemberRegistInReqDto;
import com.changcai.bank.pingan.ebao.service.RspBodyFactoryBean;

/**
 * 
 * @author ryan
 * @version $Id: String2Message.java, v 0.1 2016年9月26日 下午5:09:06 ryan Exp $
 */
//@ContextConfiguration(classes = { JavaConfig.class })
@Component
public class String2Message extends AbstractTestNGSpringContextTests {

    @Autowired
    private RspBodyFactoryBean rspBodyFac;

    @Autowired
    protected Validator        validator;

    protected final Logger     LOGGER = LoggerFactory.getLogger(this.getClass());

    private B2BMessage getRspMsg(String resStr) throws ClassNotFoundException, IOException {
        B2BMessage rspMsg = null;

        Assert.notNull(resStr, "response string must not be null");
        ByteArrayInputStream baips = new ByteArrayInputStream(resStr.getBytes("GBK"));
        rspMsg = new B2BMessage();
        rspMsg.setNetHeader(new NetMsgHeader());
        rspMsg.setBizzHeader(new BizzMsgHeader());
        MessageObjInput in = new MessageObjInput(baips, rspMsg);

        try {
            rspMsg.getNetHeader().readExternal(in);
            System.out.println(rspMsg.getNetHeader().toJSONString());
            rspMsg.getBizzHeader().readExternal(in);
            System.out.println(rspMsg.getBizzHeader().toJSONString());
        } catch (Exception ex) {
           throw ex;
        }
      

        //  if (rspMsg.getRspCode().trim().equalsIgnoreCase(ResponseCode.RES_000000.getCode()))

        Assert.isTrue(rspMsg.getTranFunc() != null, "tran func id should not be null!");
        String rspFuncId = rspMsg.getTranFunc();

        BaseMessageBody rspBody = this.createReqBody(rspFuncId);
        Assert.notNull(rspBody, "response body must not be null");
        rspMsg.setBody(rspBody);
        rspBody.readExternal(in);
        rspMsg.setValidator(validator);
        rspMsg.validateDto();
        in.close();
        LOGGER.error("res:" + rspMsg.toJSONString());
        return rspMsg;
    }

    private BaseMessageBody createReqBody(String tranFunc) {
        BaseMessageBody msgBody = null;
        if (MessageType.MEMBER_REGIST_IN.value().equalsIgnoreCase(tranFunc)) {
            msgBody = new MemberRegistInReqDto();
        } else if (MessageType.INCOME_MONEY_FROM_BANK.value().equalsIgnoreCase(tranFunc)) {
            msgBody = new InComeMoney1310ReqDto();
        }
        return msgBody;

    }

    @Test(invocationCount = 1, threadPoolSize = 1)
    public void testRequest() throws Exception {
        // String req=  "A0010301018892                0000000203000000PA001012016092417293220160924172932697886999999                                                                                                    000001RSA-SHA1    00000000000131001                20160924172932999999                                          000000081PA00120160924172932697886889211014971274008&888800000189550&50.0&11014970134006&张生&RMB&20160920&13813813813&";
        String req = "A0010301018892                0000000000000000PA001012016092816251500320160928000458696AFE004:通讯异常-接收后台服务响应                                                                          000001RSA-SHA1    00000000000";
        getRspMsg(req);
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            JavaConfig.class);
        String2Message service = context.getBean(String2Message.class);
      //  String req = "A0010301018892                0000000000000000PA001012016092816251500320160928000458696AFE004:通讯异常-接收后台服务响应                                                                          000001RSA-SHA1    00000000000";
        String req="A0010301018892                0000000233      EB001012016100916511116100960328159      999999                                                                                                    00000000000000000000000000000130301                20161009165111999999                                          000000111EB00116100960328159      88921&11015091833000&888800150219650&陈春晖&18516603218&1&321281199102136351&11017761808005&3&1&陈春晖&&平安银行&&&";
        service.getRspMsg(req);
    }
}
