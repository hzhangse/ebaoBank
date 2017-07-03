package com.changcai.bank.pingan.ebao.http.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoHttpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.OutComeMoneyReqDto;

public class OutComeMoneyClientTest extends EbaoHttpClientInvokerTest {

    @Autowired
    OutComeMoneyReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.OUTCOME_MONEY_FROMBANK.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoout = this.accMap.get("张生");
        //   AccountInfo infoin=  this.accMap.get("铁蛋");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");

        body.setThirdCustId(infoout.getThirdCustId());
        body.setIdType(infoout.getIdType());
        body.setIdCode(infoout.getIdNo());
        body.setCustAcctId(infoout.getAccNo());
        body.setCustName(infoout.getAccName());
        body.setOutAcctId(infoout.getRelatedAcctId());
        body.setOutAcctIdName(infoout.getAccName());
        body.setTranAmount(100d);
        body.setReserve(infoout.getAccName() + "出金");
        return body;
    }
}
