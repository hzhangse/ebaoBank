/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.InComeMoney1316ReqDto;

/**
 * 入金（交易网发起）【1316】
 * 接口说明：
 * 资金从会员账户转入到平台的账户，同时增加会员子账户的余额。即会员充值资金到平台。
 * @author ryan
 * @version $Id: MemberRegistClientTest.java, v 0.1 2016年9月17日 下午4:27:07 ryan Exp $
 */
@ContextConfiguration(classes = { JavaConfig.class })
public class InComeMoney1316ClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    InComeMoney1316ReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.INCOME_MONEY.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
       AccountInfo infoin = this.accMap.get("陈春辉");
        //AccountInfo infoin=  this.accMap.get("铁蛋");
//        AccountInfo infoin=  this.accMap.get("测试二");
        //AccountInfo infoin=  this.accMap.get("测试一");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");

        body.setSupAcctId(infosup.getAccNo());
        body.setCustAcctId(infoin.getAccNo());
        body.setTranAmount(1d);
        body.setIdCode(infoin.getIdNo());
        body.setIdType(infoin.getIdType());
        body.setThirdCustId(infoin.getThirdCustId());
        body.setInAcctId(infoin.getRelatedAcctId());
        body.setInAcctIdName(infoin.getAccName());
        body.setCcyCode("RMB");
        body.setReserve(infoin.getThirdCustId());

        return body;
    }

}
