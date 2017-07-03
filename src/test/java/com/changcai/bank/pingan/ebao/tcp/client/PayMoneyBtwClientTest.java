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

/**
 * 子账户间支付【1332】-基本
 * 接口说明：
 * 会员A直接支付给会员B。钱从会员A的子账户支付到会员B的子账户。
 */
public class PayMoneyBtwClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    PayMoneyReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.PAY_MONEY_BTW.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        AccountInfo infoout = this.accMap.get("铁蛋");
//        AccountInfo infoin = this.accMap.get("测试二");
        AccountInfo infoin = this.accMap.get("张生");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setFuncFlag("1");
//        body.setSupAcctId(infosup.getAccNo());
        body.setOutCustAcctId(infoout.getAccNo());
        body.setOutThirdCustId(infoout.getThirdCustId());
        
        body.setInCustAcctId(infoin.getAccNo());
        body.setInThirdCustId(infoin.getThirdCustId());
        body.setTranAmount(134d);
        body.setHandFee(5d);
        body.setPaySerialNo(UUID.randomUUID().toString().substring(0,29));
        body.setThirdHtId(UUID.randomUUID().toString().substring(0,29));

        LOGGER.warn("setPaySerialNo: "+body.getPaySerialNo());
        LOGGER.warn("setThirdHtId: "+body.getThirdHtId());
        body.setReserve("");

        return body;
    }

}
