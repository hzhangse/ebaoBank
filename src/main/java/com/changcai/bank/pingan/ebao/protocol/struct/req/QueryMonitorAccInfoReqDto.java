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
 * 监管账户信息查询【1021】
 * 接口说明：
 * 查询平台监管账户的基本信息。可用于日常的资金及用户数的核对。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryMonitorAccInfoReqDto extends RequestMessageBody {

    // 资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(1)
    @Value("${SupAcctId}")
    private String acctId;

    // 交易网代码
    @NotNull
    @Size(max = 4)
    @Order(2)
    private byte[] tranWebCode;

    // 保留域
    @Size(max = 120)
    @Order(3)
    private byte[] reserve;

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getTranWebCode() throws UnsupportedEncodingException {
        if (tranWebCode != null) {
            return new String(tranWebCode, this.encoding);
        } else {
            return null;
        }
    }

    public void setTranWebCode(String tranWebCode) throws UnsupportedEncodingException {
        if (tranWebCode != null) {
            this.tranWebCode = tranWebCode.getBytes(this.encoding);
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
