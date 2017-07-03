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
import com.changcai.bank.pingan.ebao.protocol.struct.req.MemberRegistInReqDto;

/**
 * 
 * @author ryan
 * @version $Id: MemberRegistClientTest.java, v 0.1 2016年9月17日 下午4:27:07 ryan Exp $
 */
public class MemberRegistClientTest extends EbaoHttpClientInvokerTest {

    @Autowired
    MemberRegistInReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        this.setFuncId( MessageType.MEMBER_REGIST_IN.value());
     
    }
    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoin = this.accMap.get("铁蛋");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setFuncFlag("1");
        body.setSupAcctId(infosup.getAccNo());
        body.setCustAcctId(infoin.getAccNo());
        body.setCustName(infoin.getAccName());
        body.setAcctName(infoin.getAccName());
        body.setThirdCustId(infoin.getThirdCustId());
        body.setIdType(infoin.getIdType());
        body.setIdCode(infoin.getIdNo());
        body.setRelatedAcctId(infoin.getRelatedAcctId());
        body.setAcctFlag("3");//
        body.setTranType("1");
        body.setBankName("平安银行");

        body.setReserve("保留字reserve");

        return body;
    }

}
