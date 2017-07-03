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
 * 子账户直接支付【1329】-不建议使用
 * 接口说明：
 * 会员A直接支付给会员B。钱从会员A的子账户支付到会员B的银行卡。
 * 可用支付：即从会员A子账户的可用余额里扣减，用来支付给B的银行卡。
 * 冻结支付：即从会员A子账户的冻结余额里扣减，用来支付给B的银行卡，方便平台先冻结会员资金，然后再进行支付。
 * 子账户间支付【1332】-基本
 * 接口说明：
 * 会员A直接支付给会员B。钱从会员A的子账户支付到会员B的子账户。
 * @author ryan
 * @version $Id: PayMoneyDirectReqDto.java, v 0.1 2016年9月16日 下午8:30:42 ryan Exp $
 */
@Component
@Scope("prototype")
public class PayMoneyReqDto extends RequestMessageBody {

    //  资金汇总账号  SupAcctId   C(32)   必输  资金汇总账号
    @NotNull
    @Size(max = 32)
    @Order(1)
    private byte[] supAcctId;

    @Value("${SupAcctId}")
    private String supAcctIdStr;

    //   功能标志       C(1)    必输  1：可用支付 2：冻结支付
    @NotNull
    @Size(max = 1)
    @Order(2)
    private byte[] funcFlag;

    //   转出子账户   OutCustAcctId   C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] outCustAcctId;

    //   转出会员代码  OutThirdCustId  C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(4)
    private byte[] outThirdCustId;

    //   转入子账户   InCustAcctId    C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(5)
    private byte[] inCustAcctId;

    //   转入会员代码  InThirdCustId   C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] inThirdCustId;

    //   支付金额    TranAmount  9(15)   必输  
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(7)
    private Double tranAmount;

    //   手续费金额   HandFee 9(15)   必输  
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(8)
    private Double handFee;

    //   币种  CcyCode C(3)    必输  默认：RMB
    @NotNull
    @Size(max = 3)
    @Order(9)
    private byte[] ccyCode;

    //   支付指令号   PaySerialNo C(20)   必输  根据该字段判断是否指令重复
    @NotNull
    @Size(max = 30)
    @Order(10)
    private byte[] paySerialNo;

    //   支付订单号   ThirdHtId   C(30)   必输 
    @NotNull
    @Size(max = 30)
    @Order(11)
    private byte[] thirdHtId;

    //   支付订单内容  ThirdHtCont C(500)  可选  
    @Size(max = 500)
    @Order(12)
    private byte[] thirdHtCont;

    //   备注  Note    C(120)  可选  
    @Size(max = 120)
    @Order(13)
    private byte[] note;

    //   保留域 Reserve C(120)  可选  
    @Size(max = 120)
    @Order(14)
    private byte[] reserve;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        supAcctId = supAcctIdStr.getBytes(this.encoding);
        this.setCcyCode("RMB");
    }

    public String getSupAcctId() throws UnsupportedEncodingException {
        if (supAcctId != null)
            return new String(supAcctId, this.encoding);
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

    public String getOutCustAcctId() throws UnsupportedEncodingException {
        if (outCustAcctId != null)
            return new String(outCustAcctId, this.encoding);
        else
            return null;
    }

    public void setOutCustAcctId(String outCustAcctId) throws UnsupportedEncodingException {
        if (outCustAcctId != null)
            this.outCustAcctId = outCustAcctId.getBytes(this.encoding);

    }

    public String getOutThirdCustId() throws UnsupportedEncodingException {
        if (outThirdCustId != null)
            return new String(outThirdCustId, this.encoding);
        else
            return null;
    }

    public void setOutThirdCustId(String outThirdCustId) throws UnsupportedEncodingException {
        if (outThirdCustId != null)
            this.outThirdCustId = outThirdCustId.getBytes(this.encoding);
    }

    public String getInCustAcctId() throws UnsupportedEncodingException {
        if (inCustAcctId != null)
            return new String(inCustAcctId, this.encoding);
        else
            return null;
    }

    public void setInCustAcctId(String inCustAcctId) throws UnsupportedEncodingException {
        if (inCustAcctId != null)
            this.inCustAcctId = inCustAcctId.getBytes(this.encoding);
    }

    public String getInThirdCustId() throws UnsupportedEncodingException {
        if (inThirdCustId != null)
            return new String(inThirdCustId, this.encoding);
        else
            return null;
    }

    public void setInThirdCustId(String inThirdCustId) throws UnsupportedEncodingException {
        if (inThirdCustId != null)
            this.inThirdCustId = inThirdCustId.getBytes(this.encoding);
    }

    public Double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

    public Double getHandFee() {
        return handFee;
    }

    public void setHandFee(Double handFee) {
        this.handFee = handFee;
    }

    public String getCcyCode() throws UnsupportedEncodingException {
        return new String(ccyCode, this.encoding);
    }



    public void setCcyCode(String ccyCode) throws UnsupportedEncodingException {
        if (ccyCode != null)
            this.ccyCode = ccyCode.getBytes(this.encoding);
    }

    public String getPaySerialNo() throws UnsupportedEncodingException {
        if (paySerialNo != null)
            return new String(paySerialNo, this.encoding);
        else
            return null;
    }

    public void setPaySerialNo(String paySerialNo) throws UnsupportedEncodingException {
        if (paySerialNo != null)
            this.paySerialNo = paySerialNo.getBytes(this.encoding);
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
