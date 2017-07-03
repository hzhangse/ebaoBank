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
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMemberTransReqDto;

/**
 * 查询时间段会员交易流水信息【1324】-基本
 * 接口说明：
 * 查询时间段交易流水，可以提供平台进行每日对账使用。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */
public class QueryMemberTransClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    private QueryMemberTransReqDto body;

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
//        body.setBeginDate("20160601");
//        body.setEndDate("20161201");
//        body.setPageNum("1");
        body.setBeginDate("20161007");
        body.setEndDate("20161020");
        body.setPageNum("1");
        
        body.setOrigThirdLogNo("20161009161119033354");
        body.setReserve("");
        return body;
    }

}
