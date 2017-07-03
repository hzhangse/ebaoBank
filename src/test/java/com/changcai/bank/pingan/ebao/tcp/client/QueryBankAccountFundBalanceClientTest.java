package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryBankAccountFundBalanceReqDto;

/**
 * 查银行端会员资金台帐余额【1010】-基本
 * 接口说明：
 * 查询银行端会员子账户余额，可用于每日日终核对两边账户余额是否一致。
 * @author ryan
 * @version $Id: QueryBank.java, v 0.1 2016年9月21日 下午6:19:05 ryan Exp $
 */
public class QueryBankAccountFundBalanceClientTest extends EbaoTcpClientInvokerTest {
    
    @Autowired
    QueryBankAccountFundBalanceReqDto body;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        funcId = MessageType.QUERY_BANK_ACCOUNT_FUND_BALANCE.value();
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
      //  AccountInfo infoout = this.accMap.get("张生");
        AccountInfo infoout=  this.accMap.get("陈春辉");
//        AccountInfo infoout=  this.accMap.get("铁蛋");
        //AccountInfo infosup = this.accMap.get("买豆粕监管账户");
        body.setThirdCustId(infoout.getThirdCustId());
        body.setCustAcctId(infoout.getAccNo());
        body.setSelectFlag("1");
        body.setPageNum("1");
        body.setReserve("买豆粕监管账户");
        return body;
    }

}
