/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.req;

import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;

/**
 * 子账户冻结解冻【1029】
 * 接口说明：
 * 冻结：将会员子账户的可用资金冻结，即会员子账户金额从可用金额变为冻结金额。
 * 解冻：将会员子账户的已冻结资金解冻，即会员子账户金额从冻结金额变为可用金额。
 * @author ryan
 * @version $Id: PayMoneyDirectReqDto.java, v 0.1 2016年9月16日 下午8:30:42 ryan Exp $
 */
@Component
@Scope("prototype")
public class LockAccountReqDto extends RequestMessageBody {

    //  资金汇总账号  SupAcctId   C(32)   必输  资金汇总账号
    @NotNull
    @Size(max = 32)
    @Order(1)
    private byte[] supAcctId;

    @Value("${SupAcctId}")
    private String supAcctIdStr;

    //   功能标志       C(1)    必输  1：冻结 2：解冻
    @NotNull
    @Size(max = 1)
    @Order(2)
    private byte[] funcFlag;

    //   子账户   CustAcctId   C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] custAcctId;

    //  会员代码  ThirdCustId  C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(4)
    private byte[] thirdCustId;

    //   支付金额    TranAmount  9(15)   必输  
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(5)
    private Double tranAmount;

    //   币种  CcyCode C(3)    必输  默认：RMB
    @NotNull
    @Size(max = 3)
    @Order(6)
    private byte[] ccyCode;

    //   支付订单号   ThirdHtId   C(30)   必输 
    @NotNull
    @Size(max = 30)
    @Order(7)
    private byte[] thirdHtId;

    //   支付订单内容  ThirdHtCont C(500)  可选  
    @Size(max = 500)
    @Order(8)
    private byte[] thirdHtCont;

    //   备注  Note    C(120)  可选  
    @Size(max = 120)
    @Order(9)
    private byte[] note;

    //   保留域 Reserve C(120)  可选  
    @Size(max = 120)
    @Order(10)
    private byte[] reserve;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        supAcctId = supAcctIdStr.getBytes(this.encoding);
        this.setCcyCode("RMB");
    }

    public String getSupAcctId() throws UnsupportedEncodingException {
        if (supAcctId != null)
            return new String(supAcctId, this.encoding);
        else
            return null;
    }

    public void setSupAcctId(String supAcctId) throws UnsupportedEncodingException {
        if (supAcctId != null)
            this.supAcctId = supAcctId.getBytes(this.encoding);
    }

    public String getSupAcctIdStr() {
        return supAcctIdStr;
    }

    public void setSupAcctIdStr(String supAcctIdStr) {
        this.supAcctIdStr = supAcctIdStr;
    }

    public String getFuncFlag() throws UnsupportedEncodingException {
        if (funcFlag != null)
            return new String(funcFlag, this.encoding);
        else
            return null;
    }

    public void setFuncFlag(String funcFlag) throws UnsupportedEncodingException {
        if (funcFlag != null)
            this.funcFlag = funcFlag.getBytes(this.encoding);
    }

    public String getCustAcctId() throws UnsupportedEncodingException {
        if (custAcctId != null)
            return new String(custAcctId, this.encoding);
        else
            return null;
    }

    public void setCustAcctId(String custAcctId) throws UnsupportedEncodingException {
        if (custAcctId != null)
            this.custAcctId = custAcctId.getBytes(this.encoding);
    }

    public String getThirdCustId() throws UnsupportedEncodingException {
        if (thirdCustId != null)
            return new String(thirdCustId, this.encoding);
        else
            return null;
    }

    public void setThirdCustId(String thirdCustId) throws UnsupportedEncodingException {
        if (thirdCustId != null)
            this.thirdCustId = thirdCustId.getBytes(this.encoding);
    }

    public Double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getCcyCode() throws UnsupportedEncodingException {
        if (ccyCode != null)
            return new String(ccyCode, this.encoding);
        else
            return null;
    }

    public void setCcyCode(String ccyCode) throws UnsupportedEncodingException {
        if (ccyCode != null)
            this.ccyCode = ccyCode.getBytes(this.encoding);
    }

    public String getThirdHtId() throws UnsupportedEncodingException {
        if (thirdHtId != null)
            return new String(thirdHtId, this.encoding);
        else
            return null;
    }

    public void setThirdHtId(String thirdHtId) throws UnsupportedEncodingException {
        if (thirdHtId != null)
            this.thirdHtId = thirdHtId.getBytes(this.encoding);
    }

    public String getThirdHtCont() throws UnsupportedEncodingException {
        if (thirdHtCont != null)
            return new String(thirdHtCont, this.encoding);
        else
            return null;
    }

    public void setThirdHtCont(String thirdHtCont) throws UnsupportedEncodingException {
        if (thirdHtCont != null)
            this.thirdHtCont = thirdHtCont.getBytes(this.encoding);
    }

    public String getNote() throws UnsupportedEncodingException {
        if (note != null)
            return new String(note, this.encoding);
        else
            return null;
    }

    public void setNote(String note) throws UnsupportedEncodingException {
        if (note != null)
            this.note = note.getBytes(this.encoding);
    }

    public String getReserve() throws UnsupportedEncodingException {
        if (reserve != null)
            return new String(reserve, this.encoding);
        else
            return null;
    }

   
    public void setReserve(String reserve) throws UnsupportedEncodingException {
        if (reserve != null)
            this.reserve = reserve.getBytes(this.encoding);
    }
}
