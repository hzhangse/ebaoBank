/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.constant;

/**
 * 记账标志
 * @author ryan
 * @version $Id: TranFlag.java, v 0.1 2016年9月20日 下午8:36:42 ryan Exp $
 */
public enum TranFlag {
    TranFlag_1   ("1","申请支付") ,
    TranFlag_2   ("2","冻结"), 
    TranFlag_3   ("3","解冻"), 
    TranFlag_4  ("4","收费"), 
    TranFlag_5  ("5","退费"),
    TranFlag_6  ("6","会员支付到市场"), 
    TranFlag_7  ("7","市场支付到会员"), 
    TranFlag_8  ("8","确认支付"),  
    TranFlag_9  ("9","可用直接支付"),  
    TranFlag_10  ("10","撤销支付"),  
    TranFlag_11  ("11","代理确认支付"),  
    TranFlag_12  ("12","强制支付"),  
    TranFlag_13  ("13","冻结直接支付"),  
    TranFlag_14  ("14","冻结收费"),  
    TranFlag_15  ("15","会员冻结支付到市场"), 
    TranFlag_16  ("16","子账户间可用支付"), 
    TranFlag_17  ("17","子账户间冻结支付");
    
    private String code;
    private String desc;

    private TranFlag(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
