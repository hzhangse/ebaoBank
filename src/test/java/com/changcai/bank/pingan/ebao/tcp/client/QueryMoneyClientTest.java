package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMoneyRecordSetReqDto;

/**
 * 查询时间段会员出入金流水信息【1325】
 * 查询时间段出入金流水，可以提供平台进行每日对账使用。
 * 若交易网流水号为空，则返回全部，此时返回的都是成功的记录。
 * 若交易网流水号不为空，则查询单笔交易，此时返回该笔交易的任何状态。在进行单笔查询时，若返回ERR020，则说明银行无此记录。
 **/
public class QueryMoneyClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    QueryMoneyRecordSetReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_MONEY_RECORDSET.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        body.setBeginDate("20170106");
        body.setEndDate("20170106");
        body.setPageNum("1");
        body.setReserve("保留字reserve");
        return body;
    }

}
