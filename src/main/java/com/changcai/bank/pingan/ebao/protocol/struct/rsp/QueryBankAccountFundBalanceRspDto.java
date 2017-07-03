/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 应答包：监管系统－>交易网
 * 查银行端会员资金台帐余额【1010】-基本
 * 接口说明：
 * 查询银行端会员子账户余额，可用于每日日终核对两边账户余额是否一致。
 * @author ryan
 * @version $Id: QueryBankAccountFundBalanceReqDto.java, v 0.1 2016年9月20日 上午11:08:16 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryBankAccountFundBalanceRspDto extends ResponseMessageBody {

    //总记录数    TotalCount  C(8)    必输  
    @NotNull
    @Size(max = 8)
    @Order(1)
    private byte[]                                     totalCount;

    //  起始记录号   BeginNum    C(8)    必输  
    @NotNull
    @Size(max = 8)
    @Order(2)
    private byte[]                                     beginNum;

    //是否结束包   LastPage    C(1)    必输  0：否  1：是
    @NotNull
    @Size(max = 1)
    @Order(3)
    private byte[]                                     lastPage;

    //本次返回流水笔数    RecordNum   C(4)    必输  重复次数（一次最多返回20条记录）
    @NotNull
    @Size(max = 4)
    @Order(4)
    private byte[]                                     recordNum;

    //信息数组
    //信息数组
    @NotNull
    @Valid
    @Order(5)
    private List<QueryBankAccountFundBalanceSubRspDto> recordList;

    //保留域 Reserve C(120)  可选  
    @NotNull
    @Size(max = 120)
    @Order(6)
    private byte[]                                     reserve;

    public String getTotalCount() throws UnsupportedEncodingException {
        if (this.totalCount != null) {
            return new String(totalCount, this.encoding);
        } else {
            return null;
        }
    }

    public void setTotalCount(String totalCount) throws UnsupportedEncodingException {
        if (totalCount != null) {
            this.totalCount = totalCount.getBytes(this.encoding);
        }
    }

    public String getBeginNum() throws UnsupportedEncodingException {
        if (beginNum != null) {
            return new String(beginNum, this.encoding);
        } else {
            return null;
        }
    }

    public void setBeginNum(String beginNum) throws UnsupportedEncodingException {
        if (beginNum != null) {
            this.beginNum = beginNum.getBytes(this.encoding);
        }
    }

    public String getLastPage() throws UnsupportedEncodingException {
        if (this.lastPage != null) {
            return new String(lastPage, this.encoding);
        } else {
            return null;
        }
    }

    public void setLastPage(String lastPage) throws UnsupportedEncodingException {
        if (lastPage != null) {
            this.lastPage = lastPage.getBytes(this.encoding);
        }
    }

    public String getRecordNum() throws UnsupportedEncodingException {
        if (recordNum != null) {
            return new String(recordNum, this.encoding);
        } else {
            return null;
        }
    }

    public void setRecordNum(String recordNum) throws UnsupportedEncodingException {
        if (recordNum != null) {
            this.recordNum = recordNum.getBytes(this.encoding);
        }
    }

    public List<QueryBankAccountFundBalanceSubRspDto> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<QueryBankAccountFundBalanceSubRspDto> recordList) {
        this.recordList = recordList;
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
