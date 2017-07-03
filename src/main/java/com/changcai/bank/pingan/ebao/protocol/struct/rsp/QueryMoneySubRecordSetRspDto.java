/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 查询时间段会员出入金流水信息【1325】
 * 查询时间段出入金流水，可以提供平台进行每日对账使用。
 *若交易网流水号为空，则返回全部，此时返回的都是成功的记录。
 *若交易网流水号不为空，则查询单笔交易，此时返回该笔交易的任何状态。在进行单笔查询时，若返回ERR020，则说明银行无此记录。
 * @author ryan
 * @version $Id: QueryMoneyRecordSetReqDto.java, v 0.1 2016年9月15日 下午6:00:59 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryMoneySubRecordSetRspDto extends ResponseMessageBody {
    
    private static final Logger LOGGER = LoggerFactory
                                           .getLogger(QueryMoneySubRecordSetRspDto.class);
    //   交易网流水号  C(20)   必输  
    @NotNull
    @Size(max = 20)
    @Order(1)
    private byte[]              thirdLogNo;

    //   银行前置流水号 ,C(14)   必输
    @NotNull
    @Size(max = 14)
    @Order(2)
    private byte[]              frontLogNo;

    //   记账标志, C(1)    必输  1：入金 2：出金     
    @NotNull
    @Size(max = 1)
    @Order(3)
    private byte[]              tranFlag;

    //交易状态  --C(1)    必输  
    //   0：成功  
    //   1：失败（交易网流水号不为空时才返回）
    //   2：异常（交易网流水号不为空时才返回）
    //   3：冲正（交易网流水号不为空时才返回）
    @NotNull
    @Size(max = 1)
    @Order(4)
    private byte[]              tranStatus;

    // 交易金额  
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(5)
    private Double              tranAmount;

    //   子账户 
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[]              custAcctId;

    //   会员代码 
    @NotNull
    @Size(max = 32)
    @Order(7)
    private byte[]              thirdCustId;

    //   交易日期 
    @NotNull
    @Size(max = 8)
    @Order(8)
    private byte[]              tranDate;

    //   会计日期    ,即银行主机记账日期
    @NotNull
    @Size(max = 8)
    @Order(9)
    private byte[]              acctDate;

    public Double getTranAmount() throws UnsupportedEncodingException {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getThirdLogNo() throws UnsupportedEncodingException {
        if (thirdLogNo != null) {
            return new String(thirdLogNo, this.encoding);
        } else {
            return null;
        }

    }

    public void setThirdLogNo(String thirdLogNo) throws UnsupportedEncodingException {
        if (thirdLogNo != null) {
            this.thirdLogNo = thirdLogNo.getBytes(this.encoding);
        }
    }

    public String getFrontLogNo() throws UnsupportedEncodingException {
        if (frontLogNo != null) {
            return new String(frontLogNo, this.encoding);
        } else {
            return null;
        }

    }

    public void setFrontLogNo(String frontLogNo) throws UnsupportedEncodingException {
        if (frontLogNo != null) {
            this.frontLogNo = frontLogNo.getBytes(this.encoding);
        }
    }

    public String getTranFlag() throws UnsupportedEncodingException {
        if (tranFlag != null) {
            return new String(tranFlag, this.encoding);
        } else {
            return null;
        }

    }

    public void setTranFlag(String tranFlag) throws UnsupportedEncodingException {
        if (tranFlag != null) {
            this.tranFlag = tranFlag.getBytes(this.encoding);
        }
    }

    public String getTranStatus() throws UnsupportedEncodingException {
        if (tranStatus != null) {
            return new String(tranStatus, this.encoding);
        } else {
            return null;
        }

    }

    public void setTranStatus(String tranStatus) throws UnsupportedEncodingException {
        if (tranStatus != null) {
            this.tranStatus = tranStatus.getBytes(this.encoding);
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

    public String getTranDate() throws UnsupportedEncodingException {
        if (tranDate != null) {
            return new String(tranDate, this.encoding);
        } else {
            return null;
        }

    }

    public void setTranDate(String tranDate) throws UnsupportedEncodingException {
        if (tranDate != null) {
            this.tranDate = tranDate.getBytes(this.encoding);
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

}
