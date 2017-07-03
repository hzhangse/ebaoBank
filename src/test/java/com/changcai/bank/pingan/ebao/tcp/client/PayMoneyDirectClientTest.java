package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.PayMoneyReqDto;

public class PayMoneyDirectClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    PayMoneyReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.PAY_MONEY_DIRECT.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoout = this.accMap.get("张生");
        AccountInfo infoin = this.accMap.get("测试二");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setFuncFlag("1");
        body.setSupAcctId(infosup.getAccNo());
        body.setOutCustAcctId(infoout.getAccNo());
        body.setOutThirdCustId(infoout.getThirdCustId());
        body.setInCustAcctId(infoin.getAccNo());
        body.setInThirdCustId(infoin.getThirdCustId());
        body.setTranAmount(3000.50);
        body.setHandFee(500.00);
        body.setPaySerialNo(UUID.randomUUID().toString().substring(0,29));
        body.setThirdHtId(UUID.randomUUID().toString().substring(0,29));

        body.setReserve("直接付钱");
        return body;
    }
}
