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
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMonitorAccInfoReqDto;

/**
 * 监管账户信息查询【1021】
 * 接口说明：
 * 查询平台监管账户的基本信息。可用于日常的资金及用户数的核对。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */

public class QueryMonitorAccInfoClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    QueryMonitorAccInfoReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_MONITOR_ACC_INFO.value();
    }
    
    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
     //   AccountInfo infoout = this.accMap.get("张生");
       AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setAcctId(infosup.getAccNo());
        body.setTranWebCode("8892");
       // body.setOrigThirdLogNo("00320160922458280");
        body.setReserve("买豆粕监管账户");
        return body;
    }

}
