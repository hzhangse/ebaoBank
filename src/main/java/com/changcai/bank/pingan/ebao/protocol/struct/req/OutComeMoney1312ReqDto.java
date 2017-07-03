/**
 * ChangCai.com Inc.
 * Coyright (c) 2004-2016 All Rights Reserved.
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
 * 出金（银行发起）【1312】-基本
 * 接口说明：
 * 资金从平台账户转出到会员账户，同时减少会员子账户的余额。即会员从平台提现资金。
 * @author gaoxiaofei
 */
@Component
@Scope("prototype")
public class OutComeMoney1312ReqDto extends RequestMessageBody {

    // 交易网名称
    @NotNull
    @Length(max = 120)
    @Order(1)
    @Value("${TranWebName}")
    private String tranWebName;

    // 交易网会员代码
    @NotNull
    @Size(max = 32)
    @Order(2)
    private byte[] thirdCustId;

    // 子账户账号
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] custAcctId;

    // 会员名称
    @NotNull
    @Size(max = 120)
    @Order(4)
    private byte[] custName;

    // 资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(5)
    @Value("${SupAcctId}")
    private String supAcctId;


    // 出金账号  即收款账户
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] outAcctId;

    // 出金账户名称    与会员名称一致
    @NotNull
    @Size(max = 120)
    @Order(7)
    private byte[] outAcctIdName;

    // 币种-默认为RMB
    @Size(max = 3)
    @Order(8)
    private byte[] ccyCode;

    // 申请出金金额    -- 不包括转账手续费
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(9)
    private Double tranAmount;

    // 转账手续费
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(10)
    private Double handFee;

    // 支付转账手续费的子账户--预留字段，无实际作用
    @Size(max = 32)
    @Order(11)
    private byte[] freeOutCustId;

    // 保留域
    @Size(max = 120)
    @Order(12)
    private byte[] reserve;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        ccyCode = "RMB".getBytes(this.encoding);
    }

    public String getTranWebName() {
        return tranWebName;
    }

    public void setTranWebName(String tranWebName) {
        this.tranWebName = tranWebName;
    }

    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
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

    public String getCustName() throws UnsupportedEncodingException {
        if (custName != null) {
            return new String(custName, this.encoding);
        } else {
            return null;
        }

    }

    public void setCustName(String custName) throws UnsupportedEncodingException {
        if (custName != null) {
            this.custName = custName.getBytes(this.encoding);
        }
    }

    public String getOutAcctId() throws UnsupportedEncodingException {
        if (outAcctId != null) {
            return new String(outAcctId, this.encoding);
        } else {
            return null;
        }

    }

    public void setOutAcctId(String outAcctId) throws UnsupportedEncodingException {
        if (outAcctId != null) {
            this.outAcctId = outAcctId.getBytes(this.encoding);
        }
    }

    public String getOutAcctIdName() throws UnsupportedEncodingException {
        if (outAcctIdName != null) {
            return new String(outAcctIdName, this.encoding);
        } else {
            return null;
        }

    }

    public void setOutAcctIdName(String outAcctIdName) throws UnsupportedEncodingException {
        if (outAcctIdName != null) {
            this.outAcctIdName = outAcctIdName.getBytes(this.encoding);
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

    public Double getTranAmount() {
        return this.tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        if (tranAmount != null) {
            this.tranAmount = tranAmount;
        }
    }

    public String getFreeOutCustId() throws UnsupportedEncodingException {
        if (freeOutCustId != null) {
            return new String(freeOutCustId, this.encoding);
        } else {
            return null;
        }

    }

    public void setFreeOutCustId(String freeOutCustId) throws UnsupportedEncodingException {
        if (freeOutCustId != null) {
            this.freeOutCustId = freeOutCustId.getBytes(this.encoding);
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
