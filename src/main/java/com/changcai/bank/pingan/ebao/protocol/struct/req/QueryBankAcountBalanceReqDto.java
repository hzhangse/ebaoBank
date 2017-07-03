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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;

/**
 * 查会员出入金账号的银行余额【1020】
 * 接口说明：
 * 查询会员绑定账号的余额，方便会员在平台查询确认自己有多少钱可以进行入金。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryBankAcountBalanceReqDto extends RequestMessageBody {

    //资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    protected String supAcctId;

    //子账户
    @NotNull
    @Size(max = 32)
    @Order(2)
    private byte[] custAcctId;

    //交易网会员代码
    @Size(max = 32)
    @Order(3)
    private byte[] thirdCustId;


    //会员名称
    @NotNull
    @Size(max = 120)
    @Order(4)
    private byte[] custName;

    //出入金账号
    @NotNull
    @Size(max = 32)
    @Order(5)
    private byte[] acctNo;

    // 保留域
    @Size(max = 120)
    @Order(6)
    protected byte[] reserve;

    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
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

    public String getAcctNo() throws UnsupportedEncodingException {
        if (acctNo != null) {
            return new String(acctNo, this.encoding);
        } else {
            return null;
        }
    }

    public void setAcctNo(String acctNo) throws UnsupportedEncodingException {
        if (acctNo != null) {
            this.acctNo = acctNo.getBytes(this.encoding);
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
