/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.req;

import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;


/**
 * base request body dto for query action
 * 
 * @author ryan
 * @version $Id: AbsQueryReqDto.java, v 0.1 2016年9月21日 下午3:26:34 ryan Exp $
 */
public class BaseQueryReqDto extends RequestMessageBody {

    //资金汇总账号   C(32)
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    protected String supAcctId;

    //交易网流水号   C(20)
    @Size(max = 20)
    @Order(2)
    protected byte[] origThirdLogNo;

    //开始日期  C(8)
    @NotNull
    @Size(max = 8)
    @Order(3)
    protected byte[] beginDate;

    //结束日期    C(8)
    @NotNull
    @Size(max = 8)
    @Order(4)
    protected byte[] endDate;

    //第几页 C(6)
    @NotNull
    @Size(max = 6)
    @Order(5)
    protected byte[] pageNum;

    /**
     * 保留域 C(120)
     *  起始值为1，每次最多返回20条记录，第二页返回的记录数为第21至40条记录，
     *  第三页为41至60条记录，顺序均按照建立时间的先后
     */
    @Size(max = 120)
    @Order(6)
    protected byte[] reserve;

    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
    }

    public String getOrigThirdLogNo() throws UnsupportedEncodingException {
        if (origThirdLogNo != null) {
            return new String(origThirdLogNo, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setOrigThirdLogNo(String origThirdLogNo) throws UnsupportedEncodingException {
        if (origThirdLogNo != null) {
            this.origThirdLogNo = origThirdLogNo.getBytes(this.encoding);
        }
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
