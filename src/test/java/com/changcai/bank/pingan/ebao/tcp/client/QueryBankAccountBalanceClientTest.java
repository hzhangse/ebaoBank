/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryBankAcountBalanceReqDto;

/**
 * 查会员出入金账号的银行余额【1020】
 * 接口说明：
 * 查询会员绑定账号的余额，方便会员在平台查询确认自己有多少钱可以进行入金。
 * @author ryan
 * @version $Id: QueryBank.java, v 0.1 2016年9月21日 下午6:19:05 ryan Exp $
 */
public class QueryBankAccountBalanceClientTest extends EbaoTcpClientInvokerTest {
    
    @Autowired
    QueryBankAcountBalanceReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_BANK_ACCOUNT_INCOME_MONEY_BALANCE.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoout = this.accMap.get("陈春辉");
//          AccountInfo infoout=  this.accMap.get("测试二");
//        AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setThirdCustId(infoout.getThirdCustId());
        body.setCustAcctId(infoout.getAccNo());
        body.setCustName(infoout.getAccName());
      //  body.setAcctNo(infoout.getRelatedAcctId());
        body.setAcctNo(infoout.getRelatedAcctId());
        body.setReserve("买豆粕监管账户");
        return body;
    }

}
