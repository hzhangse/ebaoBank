package com.changcai.bank.pingan.ebao.tcp.client;

import com.changcai.bank.pingan.ebao.CaseInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.annotations.BeforeClass;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * @author gaoxiaofei
 * @since 2016/10/09
 * 封装请求
 */
@Component
@Scope("prototype")
public class CaseClient extends CaseInvokerTest {

    @Autowired
    QueryBankAccountFundBalanceReqDto queryBankAccountFundBalanceReqDto; // 1, 8
    @Autowired
    OutComeMoneyReqDto outComeMoneyReqDto; // 2, 9
    @Autowired
    InComeMoney1316ReqDto inComeMoney1316ReqDto; // 4
    @Autowired
    QueryMoneyRecordSetReqDto queryMoneyRecordSetReqDto; // 4
    @Autowired
    QueryBankAcountBalanceReqDto queryBankAcountBalanceReqDto; // 4
    @Autowired
    LockAccountReqDto lockAccountReqDto; // 5
    @Autowired
    PayMoneyReqDto payMoneyReqDto; // 6
    @Autowired
    QueryMemberTransReqDto queryMemberTransReqDto; // 7

    CaseReqDto caseReqDto = new CaseReqDto();

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
    }

    // 查银行端会员资金台帐余额【1010】
    public B2BMessage queryBankAccountFundBalance(String accName) {
        funcId = MessageType.QUERY_BANK_ACCOUNT_FUND_BALANCE.value();
        try {
            caseReqDto.initQueryBankAccountFundBalanceReqDto(this.accMap.get(accName), queryBankAccountFundBalanceReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 出金（交易网发起）【1318】
    public B2BMessage outComeMoney(String accName, Double tranAmount) {
        funcId = MessageType.OUTCOME_MONEY.value();
        try {
            caseReqDto.initOutComeMoneyReqDto(this.accMap.get(accName), tranAmount, outComeMoneyReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 入金（交易网发起）【1316】
    public B2BMessage inComeMoney(String accName, Double tranAmount) {
        funcId = MessageType.INCOME_MONEY.value();
        try {
            caseReqDto.initInComeMoney1316ReqDto(this.accMap.get(accName), tranAmount, inComeMoney1316ReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 查询时间段会员出入金流水信息【1325】
    public B2BMessage queryMoneyRecordSet(String beginDate, String endDate, String pageNum, String origThirdLogNo) {
        funcId = MessageType.QUERY_MONEY_RECORDSET.value();
        try {
            caseReqDto.initQueryMoneyRecordSetReqDto(beginDate, endDate, pageNum, origThirdLogNo, queryMoneyRecordSetReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 查会员出入金账号的银行余额【1020】
    public B2BMessage queryBankAcountBalance(String accName) {
        funcId = MessageType.QUERY_BANK_ACCOUNT_INCOME_MONEY_BALANCE.value();
        try {
            caseReqDto.initQueryBankAcountBalanceReqDto(this.accMap.get(accName), queryBankAcountBalanceReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 子账户冻结解冻【1029】
    public B2BMessage lockAccount(String accName, Double tranAmount) {
        funcId = MessageType.LOCK_UNLOCK_ACCOUNT.value();
        try {
            caseReqDto.initLockAccountReqDto(this.accMap.get(accName), tranAmount, lockAccountReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 子账户间支付【1332】
    public B2BMessage payMoney(String infoout, String infoin, Double tranAmount, Double handFee) {
        funcId = MessageType.PAY_MONEY_BTW.value();
        try {
            caseReqDto.initPayMoneyReqDto(this.accMap.get(infoout), this.accMap.get(infoin), tranAmount, handFee, payMoneyReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    // 查询时间段会员交易流水信息【1324】
    public B2BMessage queryMemberTrans(String origThirdLogNo) {
        funcId = MessageType.QUERY_MEMBER_TRANS.value();
        try {
            caseReqDto.initQueryMemberTransReqDto(origThirdLogNo, queryMemberTransReqDto);
            return super.testRequest();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        throw new RuntimeException("Request failure");
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        Class reqClass = MessageType.getMessageType(funcId).getReqClass();
        for(Field field: CaseClient.class.getDeclaredFields()) {
            if(field.getType().equals(reqClass)) {
                try {
                    return (BaseMessageBody)field.get(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("ReqDto not found");
    }
}
