/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.service;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessage;

/**
 * 
 * @author ryan
 * @version $Id: EbaoClientInvoker.java, v 0.1 2016年9月19日 下午8:39:46 ryan Exp $
 */
public interface IEbaoClientInvoker extends ApplicationContextAware,
                                   ApplicationListener<ContextClosedEvent> {
    B2BMessage callRemoteService(BaseMessageBody reqBody, String funcId) throws Throwable;

    B2BMessage callRemoteService(String reqBody) throws Throwable;
    
    B2BMessage getReqMsg();
    
     String getLogUUID() ;
     
     RequestMessage convertReqMsg(String reqMsgStr) throws Throwable ;
}
