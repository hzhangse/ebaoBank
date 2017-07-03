/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 应答包：监管系统－>交易网
 * 查银行端会员资金台帐余额【1010】- 返回结果列表
 *
 * @author ryan
 * @version $Id: QueryMoneyRecordSetReqDto.java, v 0.1 2016年9月15日 下午6:00:59 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryBankAccountFundBalanceSubRspDto extends ResponseMessageBody {

    //子账户  
    @NotNull
    @Size(max = 20)
    @Order(1)
    private byte[] custAcctId;

    // 子账户性质,1：虚拟账号，2：实体账号
    @NotNull
    @Size(max = 1)
    @Order(2)
    private byte[] custFlag;

    //子账户属性 C(1)    必输 1：普通会员子账户 2：挂账子账户  3：手续费子账户 4：利息子账户 6：清收子账户
    @NotNull
    @Size(max = 1)
    @Order(3)
    private byte[] custType;

    // 子账户状态 C(1)    必输 1：正常  2：已销户
    @NotNull
    @Size(max = 1)
    @Order(4)
    private byte[] custStatus;

    //交易网会员代码   C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(5)
    private byte[] thirdCustId;

    //上级监管账号  MainAcctId  C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] mainAcctId;

    // 会员名称    CustName    C(120)  必输  
    @NotNull
    @Size(max = 120)
    @Order(7)
    private byte[] custName;

    //账户总余额   TotalAmount 9(15)   必输  
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(8)
    private Double totalAmount;

    // 账户可用余额  TotalBalance    9(15)   必输  
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(9)
    private Double totalBalance;

    // 账户总冻结金额 TotalFreezeAmount   9(15)   必输
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(10)
    private Double totalFreezeAmount;

    //维护日期    TranDate    C(8)    必输  开户日期或修改日期
    @NotNull
    @Size(max = 8)
    @Order(11)
    private byte[] tranDate;

    public String getCustAcctId() throws UnsupportedEncodingException {
        if (custAcctId != null) {
            return new String(custAcctId, this.encoding);
        } else {
            return null;
        }
    }

    public String getCustFlag() throws UnsupportedEncodingException {
        if (custFlag != null) {
            return new String(custFlag, this.encoding);
        } else
            return null;
    }

    public String getCustType() throws UnsupportedEncodingException {
        if (this.custType != null) {
            return new String(custType, this.encoding);
        } else {
            return null;
        }
    }

    public String getCustStatus() throws UnsupportedEncodingException {
        if (this.custStatus != null) {
            return new String(custStatus, this.encoding);
        } else
            return null;
    }

    public String getThirdCustId() throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            return new String(thirdCustId, this.encoding);
        } else {
            return null;
        }
    }

    public String getMainAcctId() throws UnsupportedEncodingException {
        if (mainAcctId != null) {
            return new String(mainAcctId, this.encoding);
        } else {
            return null;
        }
    }

    public String getCustName() throws UnsupportedEncodingException {
        if (custName != null) {
            return new String(custName, this.encoding);
        } else
            return null;
    }

    public String getTranDate() throws UnsupportedEncodingException {
        if (tranDate != null) {
            return new String(tranDate, this.encoding);
        } else {
            return null;
        }
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public Double getTotalFreezeAmount() {
        return totalFreezeAmount;
    }

    public void setCustAcctId(String custAcctId) throws UnsupportedEncodingException {
        if (custAcctId != null)
            this.custAcctId = custAcctId.getBytes(this.encoding);
    }

    public void setCustFlag(String custFlag) throws UnsupportedEncodingException {
        if (custFlag != null)
            this.custFlag = custFlag.getBytes(this.encoding);
    }

    public void setCustType(String custType) throws UnsupportedEncodingException {
        if (custType != null)
            this.custType = custType.getBytes(this.encoding);
    }

    public void setCustStatus(String custStatus) throws UnsupportedEncodingException {
        if (custStatus != null)
            this.custStatus = custStatus.getBytes(this.encoding);
    }

    public void setThirdCustId(String thirdCustId) throws UnsupportedEncodingException {
        if (thirdCustId != null)
            this.thirdCustId = thirdCustId.getBytes(this.encoding);
    }

    public void setMainAcctId(String mainAcctId) throws UnsupportedEncodingException {
        if (mainAcctId != null)
            this.mainAcctId = mainAcctId.getBytes(this.encoding);
    }

    public void setCustName(String custName) throws UnsupportedEncodingException {
        if (custName != null)
            this.custName = custName.getBytes(this.encoding);
    }

    public void setTranDate(String tranDate) throws UnsupportedEncodingException {
        if (tranDate != null)
            this.tranDate = tranDate.getBytes(this.encoding);
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setTotalFreezeAmount(Double totalFreezeAmount) {
        this.totalFreezeAmount = totalFreezeAmount;
    }

}
