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
 * 查询支付指令状态【1327】
 * 接口说明
 * 用于查询【1328】接口提交的申请支付的指令状态。
 * 4：处理中是中间状态，若支付最终成功则变为2：已复核，若最终失败则为1：待复核。
 * @author ryan
 * @version $Id: QueryMoneyRecordSetReqDto.java, v 0.1 2016年9月15日 下午6:00:59 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryPayRecordSetReqDto extends RequestMessageBody {

    //资金汇总账号   C(32)
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    protected String supAcctId;

    // 支付指令号    PaySerialNo C(32)   必输
    @NotNull
    @Size(max = 32)
    @Order(2)
    protected byte[] paySerialNo;

    /**
     * 保留域 C(120)可选
     */
    @Size(max = 120)
    @Order(3)
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

    public String getPaySerialNo() throws UnsupportedEncodingException {
        if (paySerialNo != null)
            return new String(paySerialNo, this.encoding);
        else
            return null;
    }

    public void setPaySerialNo(String paySerialNo) throws UnsupportedEncodingException {
        if (paySerialNo != null)
            this.paySerialNo = paySerialNo.getBytes(this.encoding);
    }

}
