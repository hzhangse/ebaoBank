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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
public class QueryMoneyRecordSetRspDto extends ResponseMessageBody {
    private static final Logger                LOGGER = LoggerFactory
                                                          .getLogger(QueryMoneyRecordSetRspDto.class);
    //   总记录数   , C(8)    必输  
    @NotNull
    @Size(max = 8)
    @Order(1)
    private byte[]                             totalCount;

    //   起始记录号    
    @NotNull
    @Size(max = 8)
    @Order(2)
    private byte[]                             beginNum;

    // 是否结束包  ---0：否  1：是  
    @NotNull
    @Size(max = 1)
    @Order(3)
    private byte[]                             lastPage;

    //   本次返回流水笔数     --必输  重复次数（一次最多返回20条记录）
    @NotNull
    @Size(max = 4)
    @Order(4)
    private byte[]                             recordNum;

    //信息数组
    @NotNull
    @Valid
    @Order(5)
    private List<QueryMoneySubRecordSetRspDto> recordList;

    //   保留域  
    @Size(max = 120)
    @Order(6)
    private byte[]                             reserve;

    public List<QueryMoneySubRecordSetRspDto> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<QueryMoneySubRecordSetRspDto> recordList) {
        this.recordList = recordList;
    }

    public String getTotalCount() throws UnsupportedEncodingException {
        if (totalCount != null) {
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
        if (lastPage != null) {
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
