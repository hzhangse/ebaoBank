/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.config;

import org.springframework.context.annotation.ConditionContext;

import com.changcai.bank.pingan.ebao.config.EbaoSpringCondition;

/**
 * 
 * 
 * @author ryan
 * @version $Id: EbaoTimeCostPrintAspectCondition.java, v 0.1 2016年9月29日 下午3:47:21 ryan Exp $
 */
public class EBaoModeCondition extends EbaoSpringCondition {

   protected String modeValue;
   
    public EBaoModeCondition() {
        this.setConditionField("mode");
    }

    protected Boolean isMatched(ConditionContext context) {
        if (isMatch == null) {
            prop = getProp(context);
            String mode = prop.getProperty(conditionField);
            if (modeValue.equalsIgnoreCase(mode))
                isMatch = true;
            else
                isMatch = false;
        }
        return isMatch;
    }
}