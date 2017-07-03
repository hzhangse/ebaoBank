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
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMemberSignInfoReqDto;

/**
 * 查时间段会员开销户信息【1016】-基本
 * 接口说明：
 * 查询某个时间段会员签解约的信息。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */

public class QueryMemberSignInfoClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    QueryMemberSignInfoReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_MEMBER_SIGN_INFO.value();
    }
    
    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoout = this.accMap.get("张生");
        //   AccountInfo infoin=  this.accMap.get("铁蛋");
//        AccountInfo infosup = this.accMap.get("买豆粕监管账户");
//        body.setBeginDate("20160601");
//        body.setEndDate("20161201");
//        body.setPageNum("1");
        body.setBeginDate("20160801");
        body.setEndDate("20160923");
        body.setPageNum("1");
//        body.setOrigThirdLogNo("00320160922458280");
        body.setReserve("买豆粕监管账户");
        return body;
    }

}
