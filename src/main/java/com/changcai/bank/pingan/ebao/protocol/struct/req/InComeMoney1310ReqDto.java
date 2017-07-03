/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.req;

import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import org.hibernate.validator.constraints.Length;
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
 * @author ryan
 * 入金（银行发起）【1310】- 入参
 */
@Component
@Scope("prototype")
public class InComeMoney1310ReqDto extends RequestMessageBody {

    //资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    private String supAcctId;

    //子账户账号
    @NotNull
    @Size(max = 32)
    @Order(2)
    private byte[] custAcctId;

    //入金金额
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(3)
    private Double tranAmount;

    //入金账号
    @NotNull
    @Size(max = 32)
    @Order(4)
    private byte[] inAcctId;

    //入金账户名称
    @NotNull
    @Size(max = 120)
    @Order(5)
    private byte[] inAcctIdName;

    //币种
    @NotNull
    @Size(max = 3)
    @Order(6)
    private byte[] ccyCode ;

    //会计日期-即银行主机记账日期
    @NotNull
    @Size(max = 8)
    @Order(7)
    private byte[] acctDate;

    //保留域-返回“交易网会员代码”
    @NotNull
    @Size(max = 120)
    @Order(8)
    private byte[] reserve;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        this.ccyCode = "RMB".getBytes(this.encoding);
    }
    
    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
    }

    public Double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getCustAcctId() throws UnsupportedEncodingException {
        if (custAcctId != null) {
            return new String(custAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setCustAcctId(String custAcctId) throws UnsupportedEncodingException {
        if (custAcctId != null) {
            this.custAcctId = custAcctId.getBytes(this.encoding);
        }
    }

    public String getInAcctId() throws UnsupportedEncodingException {
        if (inAcctId != null) {
            return new String(inAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setInAcctId(String inAcctId) throws UnsupportedEncodingException {
        if (inAcctId != null) {
            this.inAcctId = inAcctId.getBytes(this.encoding);
        }
    }

    public String getInAcctIdName() throws UnsupportedEncodingException {
        if (inAcctIdName != null) {
            return new String(inAcctIdName, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setInAcctIdName(String inAcctIdName) throws UnsupportedEncodingException {
        if (inAcctIdName != null) {
            this.inAcctIdName = inAcctIdName.getBytes(this.encoding);
        }
    }

    public String getCcyCode() throws UnsupportedEncodingException {
        if (ccyCode != null) {
            return new String(ccyCode, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setCcyCode(String ccyCode) throws UnsupportedEncodingException {
        if (ccyCode != null) {
            this.ccyCode = ccyCode.getBytes(this.encoding);
        }
    }

    public String getAcctDate() throws UnsupportedEncodingException {
        if (acctDate != null) {
            return new String(acctDate, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setAcctDate(String acctDate) throws UnsupportedEncodingException {
        if (acctDate != null) {
            this.acctDate = acctDate.getBytes(this.encoding);
        }
    }

    public String getReserve() throws UnsupportedEncodingException {
        if (reserve != null) {
            return new String(reserve, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setReserve(String reserve) throws UnsupportedEncodingException {
        if (reserve != null) {
            this.reserve = reserve.getBytes(this.encoding);
        }
    }

}
