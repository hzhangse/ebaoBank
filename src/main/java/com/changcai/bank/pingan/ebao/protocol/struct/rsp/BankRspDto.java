/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 入金（银行发起）【1310】- 返回结果列表
 * 接口说明：
 * 资金从会员账户转入到平台的账户，同时增加会员子账户的余额。即会员充值资金到平台。
 * 入金（交易网发起）【1316】- 返回结果列表
 * 接口说明：
 * 资金从会员账户转入到平台的账户，同时增加会员子账户的余额。即会员充值资金到平台。
 * 子账户直接支付【1329】- 返回结果列表
 * 接口说明：
 * 会员A直接支付给会员B。钱从会员A的子账户支付到会员B的银行卡。
 * 子账户间支付【1332】-基本
 * 接口说明：
 * 会员A直接支付给会员B。钱从会员A的子账户支付到会员B的子账户。
 * 子账户冻结解冻【1029】
 * 接口说明：
 * 冻结：将会员子账户的可用资金冻结，即会员子账户金额从可用金额变为冻结金额。
 * 解冻：将会员子账户的已冻结资金解冻，即会员子账户金额从冻结金额变为可用金额。
 * 签到、签退【1330】-入参
 * 接口说明：
 * 签到后，受理平台发起的出入金，签退后，不受理任何出入金请求。
 * @author ryan
 * @version $Id: BankRspDto.java, v 0.1 2016年9月17日 上午9:59:27 ryan Exp $
 */
@Component
@Scope("prototype")
public class BankRspDto extends ResponseMessageBody {

    //  前置流水号   FrontLogNo  C(14)   必输
    @NotNull
    @Size(max = 14)
    @Order(1)
    private byte[] frontLogNo;
    
    // 保留域 Reserve C(120)  可选
    @Size(max = 120)
    @Order(2)
    private byte[] reserve;

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
