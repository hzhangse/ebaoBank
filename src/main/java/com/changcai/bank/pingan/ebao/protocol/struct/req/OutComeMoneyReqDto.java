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
 * @author ryan
 * 出金（交易网发起）【1318】
 * 资金从平台账户转出到会员账户，同时减少会员子账户的余额。即会员从平台提现资金。
 * 请求包：交易网－> 监管系统
 */
@Component
@Scope("prototype")
public class OutComeMoneyReqDto extends RequestMessageBody {

    //交易网名称    -- 平台名称          
    @NotNull
    @Length(max = 120)
    @Order(1)
    @Value("${TranWebName}")
    private String tranWebName;

    //交易网会员代码    
    @NotNull
    @Size(max = 32)
    @Order(2)
    private byte[] thirdCustId;

    //会员证件类型  
    @NotNull
    @Size(max = 2)
    @Order(3)
    private byte[] idType;

    //会员证件号码   
    @NotNull
    @Size(max = 20)
    @Order(4)
    private byte[] idCode;

    //出金类型  1：会员出金
    @NotNull
    @Size(max = 2)
    @Order(5)
    private byte[] tranOutType;

    //子账户账号    
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] custAcctId;

    //会员名称  
    @NotNull
    @Size(max = 120)
    @Order(7)
    private byte[] custName;

    //资金汇总账号  
    @NotNull
    @Length(max = 32)
    @Order(8)
    @Value("${SupAcctId}")
    private String supAcctId;

    //转账方式   1：行内转账
    @NotNull
    @Size(max = 1)
    @Order(9)
    private byte[] tranType;

    //出金账号  即收款账户 
    @NotNull
    @Size(max = 32)
    @Order(10)
    private byte[] outAcctId;

    //出金账户名称    与会员名称一致
    @NotNull
    @Size(max = 120)
    @Order(11)
    private byte[] outAcctIdName;

    //出金账号开户行名--填“平安银行”
    @NotNull
    @Size(max = 120)
    @Order(12)
    private byte[] outAcctIdBankName;

    //出金账号开户联行号
    @Size(max = 12)
    @Order(13)
    private byte[] outAcctIdBankCode;

    //出金账号开户行地址    
    @Size(max = 120)
    @Order(14)
    private byte[] address;

    //币种-默认为RMB
    @Size(max = 3)
    @Order(15)
    private byte[] ccyCode;

    //申请出金金额    -- 不包括转账手续费      
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(16)
    private Double tranAmount;

    //支付转账手续费的子账户--预留字段，无实际作用
    @Size(max = 32)
    @Order(17)
    private byte[] freeOutCustId;

    //保留域 
    @Size(max = 120)
    @Order(18)
    private byte[] reserve;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        outAcctIdBankName = "平安银行".getBytes(this.encoding);
        tranType = "1".getBytes(this.encoding);// 1：行内转账
        tranOutType = "1".getBytes(this.encoding);//1：会员出金
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

    public String getTranOutType() throws UnsupportedEncodingException {
        if (tranOutType != null) {
            return new String(tranOutType, this.encoding);
        } else {
            return null;
        }

    }

    public void setTranOutType(String tranOutType) throws UnsupportedEncodingException {
        if (tranOutType != null) {
            this.tranOutType = tranOutType.getBytes(this.encoding);
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

    public String getTranType() throws UnsupportedEncodingException {
        if (tranType != null) {
            return new String(tranType, this.encoding);
        } else {
            return null;
        }

    }

    public void setTranType(String tranType) throws UnsupportedEncodingException {
        if (tranType != null) {
            this.tranType = tranType.getBytes(this.encoding);
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

    public String getOutAcctIdBankName() throws UnsupportedEncodingException {
        if (outAcctIdBankName != null) {
            return new String(outAcctIdBankName, this.encoding);
        } else {
            return null;
        }

    }

    public void setOutAcctIdBankName(String outAcctIdBankName) throws UnsupportedEncodingException {
        if (outAcctIdBankName != null) {
            this.outAcctIdBankName = outAcctIdBankName.getBytes(this.encoding);
        }
    }

    public String getOutAcctIdBankCode() throws UnsupportedEncodingException {
        if (outAcctIdBankCode != null) {
            return new String(outAcctIdBankCode, this.encoding);
        } else {
            return null;
        }

    }

    public void setOutAcctIdBankCode(String outAcctIdBankCode) throws UnsupportedEncodingException {
        if (outAcctIdBankCode != null) {
            this.outAcctIdBankCode = outAcctIdBankCode.getBytes(this.encoding);
        }
    }

    public String getAddress() throws UnsupportedEncodingException {
        if (address != null) {
            return new String(address, this.encoding);
        } else {
            return null;
        }

    }

    public void setAddress(String address) throws UnsupportedEncodingException {
        if (address != null) {
            this.address = address.getBytes(this.encoding);
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
