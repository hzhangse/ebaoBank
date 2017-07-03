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
 * 入金（交易网发起）【1316】
 *接口说明：
 *资金从会员账户转入到平台的账户，同时增加会员子账户的余额。即会员充值资金到平台。
 */
@Component
@Scope("prototype")
public class InComeMoney1316ReqDto extends RequestMessageBody {

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

    //交易网会员代码 , C(32)   必输
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] thirdCustId;

    //会员证件类型  IdType  C(2)    必输
    @NotNull
    @Size(max = 2)
    @Order(4)
    private byte[] idType;

    //会员证件号码 , C(20)   必输
    @NotNull
    @Size(max = 20)
    @Order(5)
    private byte[] idCode;

    //入金金额
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(6)
    private Double tranAmount;

    //入金账号
    @NotNull
    @Size(max = 32)
    @Order(7)
    private byte[] inAcctId;

    //入金账户名称
    @NotNull
    @Size(max = 120)
    @Order(8)
    private byte[] inAcctIdName;

    //币种
    @NotNull
    @Size(max = 3)
    @Order(9)
    private byte[] ccyCode;

    //保留域-返回“交易网会员代码”
    @NotNull
    @Size(max = 120)
    @Order(10)
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

    public String getThirdCustId() throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            return new String(thirdCustId, this.encoding);
        } else {
            return null;
        }

    }

    public void setThirdCustId(String thirdCustId) throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            this.thirdCustId = thirdCustId.getBytes(this.encoding);
        }
    }

    public String getIdType() throws UnsupportedEncodingException {
        if (idType != null) {
            return new String(idType, this.encoding);
        } else {
            return null;
        }

    }

    public void setIdType(String idType) throws UnsupportedEncodingException {
        if (idType != null) {
            this.idType = idType.getBytes(this.encoding);
        }
    }

    public String getIdCode() throws UnsupportedEncodingException {
        if (idCode != null) {
            return new String(idCode, this.encoding);
        } else {
            return null;
        }

    }

    public void setIdCode(String idCode) throws UnsupportedEncodingException {
        if (idCode != null) {
            this.idCode = idCode.getBytes(this.encoding);
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
