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
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryPayRecordSetReqDto;

/**
 * 
 * @author ryan
 * @version $Id: QueryBank.java, v 0.1 2016年9月21日 下午6:19:05 ryan Exp $
 */
public class QueryPayClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    private QueryPayRecordSetReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_MEMBER_TRANS.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoout = this.accMap.get("张生");
        //   AccountInfo infoin=  this.accMap.get("铁蛋");
        //AccountInfo infosup = this.accMap.get("买豆粕监管账户");
      
        return body;
    }

}
