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
 * 应答包：监管系统－>交易网
 * 查会员出入金账号的银行余额【1020】- 返回结果列表
 * 接口说明：
 * 查询会员绑定账号的余额，方便会员在平台查询确认自己有多少钱可以进行入金。
 * @author gaoxiaofei
 * @version $Id: QueryBankAccountBalanceRspDto.java, v 0.1 2016年9月28日 下午6:14:08 gaoxiaofei Exp $
 */
@Component
@Scope("prototype")
public class QueryBankAccountBalanceRspDto extends ResponseMessageBody {

    // 银行可用余额
    @NotNull
    @Digits(integer = 16, fraction = 2)
    @Order(1)
    private Double balance;

    // 保留域
    @Size(max = 120)
    @Order(2)
    private byte[] reserve;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
