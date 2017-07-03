package com.changcai.bank.pingan.ebao.tcp.client;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.MemberRegistInReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import java.io.UnsupportedEncodingException;

/**
 * 会员签解约维护【1303】
 * 银行经过验证后，发起签约。
 * @author gaoxiaofei
 * @version $Id: MemberRegistInReqDto.java, v 0.1 2016年9月8日 下午2:56:00 ryan Exp $
 */
public class MemberRegistInClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    MemberRegistInReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.MEMBER_REGIST_IN.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
//        AccountInfo infoin = this.accMap.get("张生");
        AccountInfo infoin=  this.accMap.get("测试二");
        AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setFuncFlag("3");
//        body.setSupAcctId(infosup.getAccNo());
        body.setCustAcctId(infoin.getAccNo());
        body.setCustName(infoin.getAccName());
        body.setThirdCustId(infoin.getThirdCustId());
        body.setIdType(infoin.getIdType());
        body.setIdCode(infoin.getIdNo());
        body.setRelatedAcctId(infoin.getRelatedAcctId());
        body.setAcctFlag("3");
        body.setTranType("1");
        body.setAcctName(infoin.getAccName());
        body.setBankName("平安银行");
//        body.setBankName("");
//        body.setOldRelatedAcctId("");
        body.setReserve(infoin.getAccName() + "解签");
        return body;
    }
}
