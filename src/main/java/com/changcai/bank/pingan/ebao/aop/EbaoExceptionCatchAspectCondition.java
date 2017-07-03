/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.aop;

import com.changcai.bank.pingan.ebao.config.EbaoSpringCondition;


/**
 * 
 * 
 * @author ryan
 * @version $Id: EbaoExceptionCatchAspectCondition.java, v 0.1 2016年9月29日 下午3:47:15 ryan Exp $
 */
public class EbaoExceptionCatchAspectCondition  extends EbaoSpringCondition  {

    public EbaoExceptionCatchAspectCondition(){
        this.setConditionField("enableThrowableIntercept");
    }
}
