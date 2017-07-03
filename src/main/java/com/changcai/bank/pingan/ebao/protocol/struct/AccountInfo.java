/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

/**
 * ebao 账户信息类
 * @author ryan
 * @version $Id: AccountInfo.java, v 0.1 2016年9月17日 上午10:37:09 ryan Exp $
 */
public class AccountInfo {

    //账户证件类型
    private String idType;
    //账户证件号码
    private String idNo;
    // 账户类型
    private String accType;
    //账号
    private String accNo;
    //账户名称
    private String accName;
    //网银用户名
    private String netBankName;
    //登录密码
    private String loginPass;
    //会员代码
    private String memberCode;
    //手机号码
    private String mobile;

    private String relatedAcctId;
    
    private String  thirdCustId;
    /**
     * 
     */
    public AccountInfo() {
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    

 

    public String getRelatedAcctId() {
        return relatedAcctId;
    }

    public void setRelatedAcctId(String relatedAcctId) {
        this.relatedAcctId = relatedAcctId;
    }

    public String getThirdCustId() {
        return thirdCustId;
    }

    public void setThirdCustId(String thirdCustId) {
        this.thirdCustId = thirdCustId;
    }

}
