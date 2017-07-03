/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.service.IEbaoClientInvoker;

/**
 * 
 * @author ryan
 * @version $Id: EboBaseTes.java, v 0.1 2016年9月19日 上午10:58:59 ryan Exp $
 */

public abstract class EbaoHttpClientInvokerTest extends EbaoClientInvokerTest {
  
    @Autowired
    protected IEbaoClientInvoker          ebaoHttpClientInvoker;

    @Test(invocationCount = 1, threadPoolSize = 1)
    public void testRequest() throws Throwable {
        B2BMessage b2b=    ebaoHttpClientInvoker.callRemoteService(this.createReqBody(), funcId);
        LOGGER.info("res:"+b2b.toJSONString());
    }

}
