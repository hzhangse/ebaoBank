package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.OutComeMoneyReqDto;

/**
 * 出金（交易网发起）【1318】
 * 资金从平台账户转出到会员账户，同时减少会员子账户的余额。即会员从平台提现资金。
 */
public class OutComeMoneyClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    OutComeMoneyReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.OUTCOME_MONEY.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
//        AccountInfo infoout = this.accMap.get("张生");
        AccountInfo infoout=  this.accMap.get("测试二");
        //   AccountInfo infoin=  this.accMap.get("铁蛋");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");

        body.setThirdCustId(infoout.getThirdCustId());
        body.setIdType(infoout.getIdType());
        body.setIdCode(infoout.getIdNo());
        body.setCustAcctId(infoout.getAccNo());
        body.setCustName(infoout.getAccName());
        body.setOutAcctId(infoout.getRelatedAcctId());
        body.setOutAcctIdName(infoout.getAccName());
        body.setTranAmount(11680d);
        body.setReserve(infoout.getAccName() + "出金");
        return body;
    }
}
