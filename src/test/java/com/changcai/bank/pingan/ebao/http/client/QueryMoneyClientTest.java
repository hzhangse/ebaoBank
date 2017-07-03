package com.changcai.bank.pingan.ebao.http.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoHttpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMoneyRecordSetReqDto;

public class QueryMoneyClientTest extends EbaoHttpClientInvokerTest {

    @Autowired
    QueryMoneyRecordSetReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_MONEY_RECORDSET.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        body.setBeginDate("20160901");
        body.setEndDate("20161026");
        body.setPageNum("1");
        body.setReserve("保留字reserve");
        return body;
    }

}
