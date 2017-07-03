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
 * 出金（银行发起）【1312】- 返回结果列表
 * 接口说明：
 * 资金从平台账户转出到会员账户，同时减少会员子账户的余额。即会员从平台提现资金。
 * 会员签解约维护【1303】- 返回结果列表
 * 接口说明：
 * 银行经过验证后，发起签约。
 * @author ryan
 */
@Component
@Scope("prototype")
public class BuzzRspDto extends ResponseMessageBody {

    //交易网流水号	
    @NotNull
    @Size(max = 20)
    @Order(1)
    private byte[] thirdLogNo;

    //保留域	 
    @Size(max = 120)
    @Order(2)
    private byte[] reserve;

    public String getThirdLogNo() throws UnsupportedEncodingException {
        if (thirdLogNo != null) {
            return new String(thirdLogNo, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setThirdLogNo(String thirdLogNo) throws UnsupportedEncodingException {
        if (thirdLogNo != null) {
            this.thirdLogNo = thirdLogNo.getBytes(this.encoding);
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
