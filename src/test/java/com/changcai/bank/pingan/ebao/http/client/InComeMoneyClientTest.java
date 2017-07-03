/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.http.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoHttpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.InComeMoney1310ReqDto;

/**
 * 
 * @author ryan
 * @version $Id: MemberRegistClientTest.java, v 0.1 2016年9月17日 下午4:27:07 ryan Exp $
 */
public class InComeMoneyClientTest extends EbaoHttpClientInvokerTest {

    @Autowired
    InComeMoney1310ReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.INCOME_MONEY_FROM_BANK.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoin = this.accMap.get("张生");
        //    AccountInfo infoin=  this.accMap.get("铁蛋");
        //  AccountInfo infoin=  this.accMap.get("测试二");
        //    AccountInfo infoin=  this.accMap.get("测试一");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");

        body.setSupAcctId(infosup.getAccNo());
        body.setCustAcctId(infoin.getAccNo());
        body.setTranAmount(50d);
        body.setInAcctId(infoin.getRelatedAcctId());
        body.setInAcctIdName(infoin.getAccName());
        body.setCcyCode("RMB");
        body.setAcctDate("20160920");
        body.setReserve(infoin.getThirdCustId());

        return body;
    }

}
