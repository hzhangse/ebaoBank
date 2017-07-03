package com.changcai.bank.pingan.ebao;

import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.service.IEbaoClientInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author gaoxiaofei
 * @since 2016/10/09
 * 同EbaoTcpClientInvokerTest，testRequest()返回B2BMessage
 */
public abstract class CaseInvokerTest extends EbaoClientInvokerTest {

    @Autowired
    protected IEbaoClientInvoker ebaoTcpClientInvoker;
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Test(invocationCount = 1, threadPoolSize = 1)
    public B2BMessage testRequest() throws Throwable {
        B2BMessage b2bMsg = ebaoTcpClientInvoker.callRemoteService(this.createReqBody(), funcId);
        LOGGER.error(b2bMsg.toJSONString());
        if(!b2bMsg.getRspCode().equals("000000")){
            throw new RuntimeException(b2bMsg.getNetHeader().getRspMsg());
        }
        return b2bMsg;
    }
}
