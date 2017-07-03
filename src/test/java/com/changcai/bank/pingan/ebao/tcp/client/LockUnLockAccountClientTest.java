package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.LockAccountReqDto;

/**
 * 子账户冻结解冻【1029】
 * 接口说明：
 * 冻结：将会员子账户的可用资金冻结，即会员子账户金额从可用金额变为冻结金额。
 * 解冻：将会员子账户的已冻结资金解冻，即会员子账户金额从冻结金额变为可用金额。
 * @author ryan
 * @version $Id: PayMoneyDirectReqDto.java, v 0.1 2016年9月16日 下午8:30:42 ryan Exp $
 */
public class LockUnLockAccountClientTest extends EbaoTcpClientInvokerTest {

    @Autowired
    LockAccountReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        this.setFuncId( MessageType.LOCK_UNLOCK_ACCOUNT.value());
     
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
//        AccountInfo infoout=  this.accMap.get("张生");
        AccountInfo infoout=  this.accMap.get("铁蛋");
        AccountInfo infosup=  this.accMap.get("买豆粕监管账户");
        body.setFuncFlag("2");
        body.setSupAcctId(infosup.getAccNo());
        body.setCustAcctId(infoout.getAccNo());
        body.setThirdCustId(infoout.getThirdCustId());
        body.setTranAmount(120d);
        body.setThirdHtId(UUID.randomUUID().toString().substring(0,29));
        body.setReserve("保留字reserve");
        return body;
    }


}
