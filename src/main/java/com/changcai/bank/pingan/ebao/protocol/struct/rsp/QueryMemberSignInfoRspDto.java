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
 * 查时间段会员开销户信息【1016】-基本- 返回结果列表
 * 接口说明：
 *  查询某个时间段会员签解约的信息。
 * @author gaoxiaofei
 * @version $Id: QueryMemberSignInfoRspDto.java, v 0.1 2016年9月28日 下午6:14:08 gaoxiaofei Exp $
 */
@Component
@Scope("prototype")
public class QueryMemberSignInfoRspDto extends ResponseMessageBody {

    // 总记录数
    @NotNull
    @Size(max = 8)
    @Order(1)
    private byte[] totalCount;

    // 起始记录号
    @NotNull
    @Size(max = 8)
    @Order(2)
    private byte[] beginNum;

    // 是否结束包 0：否  1：是
    @NotNull
    @Size(max = 1)
    @Order(3)
    private byte[] lastPage;

    // 本次返回流水笔数 重复次数（一次最多返回20条记录）
    @NotNull
    @Size(max = 4)
    @Order(4)
    private byte[] recordNum;

    // 信息数组
    @NotNull
    @Valid
    @Order(5)
    private List<QueryMemberSignInfoSubRspDto> recordList;

    // 保留域
    @Size(max = 120)
    @Order(6)
    private byte[] reserve;

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

    public List<QueryMemberSignInfoSubRspDto> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<QueryMemberSignInfoSubRspDto> recordList) {
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
