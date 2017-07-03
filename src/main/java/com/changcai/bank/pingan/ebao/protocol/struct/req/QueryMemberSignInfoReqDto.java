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
 * 查时间段会员开销户信息【1016】-基本
 * 接口说明：
 *  查询某个时间段会员签解约的信息。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryMemberSignInfoReqDto extends RequestMessageBody {

    //资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    private String supAcctId;

//    //交易网流水号
//    @Size(max = 20)
//    @Order(2)
//    protected byte[] origThirdLogNo;

    //开始日期
    @NotNull
    @Size(max = 8)
    @Order(2)
    private byte[] beginDate;

    //结束日期
    @NotNull
    @Size(max = 8)
    @Order(3)
    private byte[] endDate;

    //第几页
    @NotNull
    @Size(max = 6)
    @Order(4)
    private byte[] pageNum;

    /**
     * 保留域 C(120)
     *  起始值为1，每次最多返回20条记录，第二页返回的记录数为第21至40条记录，
     *  第三页为41至60条记录，顺序均按照建立时间的先后
     */
    @Size(max = 120)
    @Order(5)
    private byte[] reserve;

    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
    }

   
    public String getBeginDate() throws UnsupportedEncodingException {
        if (beginDate != null) {
            return new String(beginDate, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setBeginDate(String beginDate) throws UnsupportedEncodingException {
        if (beginDate != null) {
            this.beginDate = beginDate.getBytes(this.encoding);
        }
    }

    public String getEndDate() throws UnsupportedEncodingException {
        if (endDate != null) {
            return new String(endDate, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setEndDate(String endDate) throws UnsupportedEncodingException {
        if (endDate != null) {
            this.endDate = endDate.getBytes(this.encoding);
        }
    }

    public String getPageNum() throws UnsupportedEncodingException {
        if (pageNum != null) {
            return new String(pageNum, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setPageNum(String pageNum) throws UnsupportedEncodingException {
        if (pageNum != null) {
            this.pageNum = pageNum.getBytes(this.encoding);
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
