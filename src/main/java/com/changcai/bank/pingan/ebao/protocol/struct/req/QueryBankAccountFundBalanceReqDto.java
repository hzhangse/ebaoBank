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
 * 查银行端会员资金台帐余额【1010】-基本
 * 接口说明：
 * 查询银行端会员子账户余额，可用于每日日终核对两边账户余额是否一致。
 * @author ryan
 * @version $Id: QueryBankAccountFundBalanceReqDto.java, v 0.1 2016年9月20日 上午11:08:16 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryBankAccountFundBalanceReqDto extends RequestMessageBody {

    //资金汇总账号   C(32)
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    protected String supAcctId;

    //交易网会员代码 ThirdCustId C(32)   可选  
    @Size(max = 32)
    @Order(2)
    private byte[]   thirdCustId;

    //子账户 CustAcctId  C(32)   可选  
    @Size(max = 32)
    @Order(3)
    private byte[]   custAcctId;

    //查询标志    SelectFlag  C(1)    必输  1：全部 2：普通会员子账户 3：功能子账户（利息、手续费、清收子账户）
    @NotNull
    @Size(max = 1)
    @Order(4)
    private byte[]   selectFlag;

    //第几页 PageNum C(6)    必输  起始值为1，每次最多返回20条记录，第二页返回的记录数为第21至40条记录，第三页为41至60条记录，顺序均按照建立时间的先后
    @NotNull
    @Size(max = 6)
    @Order(5)
    private byte[]   pageNum;

    // 保留域 Reserve C(120)      
    @Size(max = 120)
    @Order(6)
    protected byte[] reserve;

    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
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

    public String getCustAcctId() throws UnsupportedEncodingException {
        if (custAcctId != null) {
            return new String(custAcctId, this.encoding);
        } else {
            return null;
        }
    }

    public void setCustAcctId(String custAcctId) throws UnsupportedEncodingException {
        if (custAcctId != null)
            this.custAcctId = custAcctId.getBytes(this.encoding);
    }

    public String getSelectFlag() throws UnsupportedEncodingException {
        if (selectFlag != null) {
            return new String(selectFlag, this.encoding);
        } else
            return null;
    }

    public void setSelectFlag(String selectFlag) throws UnsupportedEncodingException {
        if (selectFlag != null) {
            this.selectFlag = selectFlag.getBytes(this.encoding);
        }
    }

    public String getPageNum() throws UnsupportedEncodingException {
        if (pageNum != null) {
            return new String(pageNum, this.encoding);
        }
        return null;
    }

    public void setPageNum(String pageNum) throws UnsupportedEncodingException {
        if (pageNum != null) {
            this.pageNum = pageNum.getBytes(this.encoding);
        }
    }

}
