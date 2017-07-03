/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.service.IEbaoClientInvoker;

/**
 * 
 * @author ryan
 * @version $Id: EboBaseTes.java, v 0.1 2016年9月19日 上午10:58:59 ryan Exp $
 */
@ContextConfiguration(classes = { JavaConfig.class })
public abstract class EbaoTcpClientInvokerTest extends EbaoClientInvokerTest {
    
    @Autowired
    @Qualifier("ebaoTcpClientInvoker")
    protected IEbaoClientInvoker          ebaoTcpClientInvoker;
    protected final Logger     LOGGER = LoggerFactory.getLogger(this.getClass());
    @Test(invocationCount = 1, threadPoolSize = 1)
    public void testRequest() throws Throwable {
        B2BMessage b2bMsg=    ebaoTcpClientInvoker.callRemoteService(this.createReqBody(), funcId);
        LOGGER.error(b2bMsg.toJSONString());
    }

   
    
}
