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
 * 查询对账文件密码【1349】
 * 接口说明：
 * 该接口用于查询对账文件密码。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryAccFilePasswordReqDto extends RequestMessageBody {

    // 资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    private String supAcctId;

    // 对账文件类型
    @NotNull
    @Size(max = 2)
    @Order(2)
    private byte[] funcFlag;

    // 查询日期
    @NotNull
    @Size(max = 8)
    @Order(3)
    private byte[] tranDate;

    // 保留域
    @Size(max = 120)
    @Order(4)
    private byte[] reserve;

    public String getSupAcctId() {
        return supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
    }

    public String getFuncFlag() throws UnsupportedEncodingException {
        if (funcFlag != null) {
            return new String(funcFlag, this.encoding);
        } else {
            return null;
        }
    }

    public void setFuncFlag(String funcFlag) throws UnsupportedEncodingException {
        if (funcFlag != null) {
            this.funcFlag = funcFlag.getBytes(this.encoding);
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
