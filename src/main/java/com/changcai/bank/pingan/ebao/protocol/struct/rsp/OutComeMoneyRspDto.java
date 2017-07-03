/**
 * ChangCai.com Inc.
 * Coyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * @author ryan
 * 出金（交易网发起）【1318】
 * 资金从平台账户转出到会员账户，同时减少会员子账户的余额。即会员从平台提现资金。
 * 应答包：监管系统－>交易网
 */
@Component
@Scope("prototype")
public class OutComeMoneyRspDto extends ResponseMessageBody {

    //前置流水号
    @NotNull
    @Size(max = 14)
    @Order(1)
    private byte[] frontLogNo;

    //转账手续费   -- 不包括转账手续费      
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(2)
    private Double handFee;

    //支付转账手续费的子账户--预留字段，无实际作用
    @Size(max = 32)
    @Order(3)
    private byte[] freeOutCustId;

    //保留域 
    @Size(max = 20)
    @Order(4)
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

    public String getFreeOutCustId() throws UnsupportedEncodingException {
        if (freeOutCustId != null) {
            return new String(freeOutCustId, this.encoding);
        } else {
            return null;
        }
    }

    public void setFreeOutCustId(String freeOutCustId) throws UnsupportedEncodingException {
        if (freeOutCustId != null) {
            this.freeOutCustId = freeOutCustId.getBytes(this.encoding);
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

    public Double getHandFee() {
        return this.handFee;
    }

    public void setHandFee(Double handFee) {
        this.handFee = handFee;
    }
}
