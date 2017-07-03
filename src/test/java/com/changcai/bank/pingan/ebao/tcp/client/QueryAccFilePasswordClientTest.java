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
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryAccFilePasswordReqDto;

/**
 * 查询对账文件密码【1349】
 * 接口说明：
 * 该接口用于查询对账文件密码。
 * @author ryan
 * @version $Id: QueryMemberTrans.java, v 0.1 2016年9月20日 下午6:14:08 ryan Exp $
 */

public class QueryAccFilePasswordClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    QueryAccFilePasswordReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_ACC_FILE_PASSWORD.value();
    }
    
    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
//        AccountInfo infoout = this.accMap.get("张生");
       AccountInfo infosup = this.accMap.get("买豆粕监管账户");
//        body.setSupAcctId(infosup.getAccNo());
        body.setFuncFlag("20");
//       body.setTranDate("20160901");
       body.setTranDate("20160928");
       // body.setOrigThirdLogNo("00320160922458280");
        body.setReserve("买豆粕监管账户");
        return body;
    }

}
