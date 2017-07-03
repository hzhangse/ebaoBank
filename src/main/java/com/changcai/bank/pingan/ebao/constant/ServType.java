/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.constant;

/**
 * 
 * 报文的功能标识码（01:请求02:应答）
 * @author ryan
 * @version $Id: ServType.java, v 0.1 2016年9月14日 下午5:11:43 ryan Exp $
 */
public enum ServType {
    REQ("01"), RSP("02");
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    ServType(String code) {
        this.code = code;
    }
}