package com.changcai.bank.pingan.ebao.tcp.client;

import com.changcai.bank.pingan.ebao.CaseInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author gaoxiaofei
 * @since 2016/10/09
 * b2bic 接口按流程联调测试案例编写
 * １．查询原有账户资金总额（１０１０）
 * ２．根据查询资金情况，调用１３１８接口清空测试账户资金
 * ３．*调用１３０３接口解签账户二，调用１０１６查询解约情况，再重新签约并重新查询，确认
 * ４．调用１３１６各入金１００００，调用１３２５查询出入金情况并核对，调用１０２０查询各自账户出入金情况并核对
 * ５．调用１０２９资金冻结Ａ账户
 * ６．调用１３３２做Ａ账户到Ｂ账户之间转账
 * ７．１３２４查询交易流水，并核对
 * ８．调用１０１０查询各自账户情况并核对
 * ９．调用１３１８接口对Ｂ账户出金５０００并调用１０１０查询并核对
 * 此处注明Ａ账户为铁蛋　Ｂ账户为测试二
 */
public class CaseTest extends CaseInvokerTest {

    @Autowired
    CaseClient caseClient;

    @BeforeClass
    public void setUp() throws Exception {
        caseClient.setUp();
    }

    /**
     * １．查询原有账户资金总额（１０１０）
     * ２．根据查询资金情况，调用１３１８接口清空测试账户资金
     */
    @Test(priority = 1)
    public void test12() {
        String[] accNames = {"铁蛋", "测试一"};
        for(String accName: accNames) {
            B2BMessage b2BMessageTemp = caseClient.queryBankAccountFundBalance(accName);
            QueryBankAccountFundBalanceSubRspDto subRspDto = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp.getBody()).getRecordList().get(0);
            Double tranAmount = subRspDto.getTotalBalance();
            if(tranAmount > 0d){
                caseClient.outComeMoney(accName, tranAmount);
                b2BMessageTemp = caseClient.queryBankAccountFundBalance(accName);
                subRspDto = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp.getBody()).getRecordList().get(0);
                Assert.assertEquals(subRspDto.getTotalBalance(), 0d);
            }
        }
    }

    /**
     * ３．*调用１３０３接口解签账户二，调用１０１６查询解约情况，再重新签约并重新查询，确认
     */
    @Test(priority = 2)
    public void test3() {
        String accName = "测试一";
    }

    /**
     * ４．调用１３１６各入金１００００，调用１３２５查询出入金情况并核对，调用１０２０查询各自账户出入金情况并核对
     */
    @Test(priority = 3)
    public void test4() {
        String[] accNames = {"铁蛋", "测试一"};
        Double tranAmount = 10000d;
        for(String accName: accNames) {
            B2BMessage b2BMessageTemp1 = caseClient.queryBankAcountBalance(accName);
            B2BMessage b2BMessageTemp2 = caseClient.inComeMoney(accName, tranAmount);
            try {
                B2BMessage b2BMessageTemp3 = caseClient.queryMoneyRecordSet("20161007", "20161026", "1", b2BMessageTemp2.getNetHeader().getThirdLogNo());
                Assert.assertEquals(((QueryMoneyRecordSetRspDto)b2BMessageTemp3.getBody()).getRecordList().get(0).getTranAmount(), tranAmount);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            B2BMessage b2BMessageTemp4 = caseClient.queryBankAcountBalance(accName);
            BigDecimal bigDecimal1 = new BigDecimal(Double.toString(((QueryBankAccountBalanceRspDto)b2BMessageTemp1.getBody()).getBalance())).subtract(new BigDecimal(Double.toString(tranAmount)));
            BigDecimal bigDecimal2 = new BigDecimal(Double.toString(((QueryBankAccountBalanceRspDto)b2BMessageTemp4.getBody()).getBalance()));
            Assert.assertEquals(bigDecimal1, bigDecimal2);
        }
    }

    /**
     * ５．调用１０２９资金冻结Ａ账户
     */
    @Test(priority = 4)
    public void test5() {
        String accName = "铁蛋";
        Double tranAmount = 11d;
        B2BMessage b2BMessageTemp1 = caseClient.queryBankAccountFundBalance(accName);
        B2BMessage b2BMessageTemp2 = caseClient.lockAccount(accName, tranAmount);
        B2BMessage b2BMessageTemp3 = caseClient.queryBankAccountFundBalance(accName);
        QueryBankAccountFundBalanceSubRspDto subRspDto1 = ((QueryBankAccountFundBalanceRspDto)b2BMessageTemp1.getBody()).getRecordList().get(0);
        QueryBankAccountFundBalanceSubRspDto subRspDto3 = ((QueryBankAccountFundBalanceRspDto)b2BMessageTemp3.getBody()).getRecordList().get(0);
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(subRspDto1.getTotalBalance())).subtract(new BigDecimal(Double.toString(tranAmount)));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(subRspDto3.getTotalBalance()));
        Assert.assertEquals(bigDecimal1, bigDecimal2);
        bigDecimal1 = new BigDecimal(Double.toString(subRspDto1.getTotalFreezeAmount())).add(new BigDecimal(Double.toString(tranAmount)));
        bigDecimal2 = new BigDecimal(Double.toString(subRspDto3.getTotalFreezeAmount()));
        Assert.assertEquals(bigDecimal1, bigDecimal2);
    }

    /**
     * ６．调用１３３２做Ａ账户到Ｂ账户之间转账
     * ７．１３２４查询交易流水，并核对
     * ８．调用１０１０查询各自账户情况并核对
     */
    @Test(priority = 5)
    public void test678() {
        String[] accNames = {"铁蛋", "测试一"};
        Double tranAmount = 23.23d;
        Double handFee = 5d;
        B2BMessage b2BMessageTemp1 = caseClient.queryBankAccountFundBalance(accNames[0]);
        B2BMessage b2BMessageTemp2 = caseClient.queryBankAccountFundBalance(accNames[1]);
        B2BMessage b2BMessageTemp3 = caseClient.payMoney(accNames[0], accNames[1], tranAmount, handFee);
        B2BMessage b2BMessageTemp4 = caseClient.queryBankAccountFundBalance(accNames[0]);
        B2BMessage b2BMessageTemp5 = caseClient.queryBankAccountFundBalance(accNames[1]);
        QueryBankAccountFundBalanceSubRspDto subRspDto1 = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp1.getBody()).getRecordList().get(0);
        QueryBankAccountFundBalanceSubRspDto subRspDto2 = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp2.getBody()).getRecordList().get(0);
        QueryBankAccountFundBalanceSubRspDto subRspDto4 = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp4.getBody()).getRecordList().get(0);
        QueryBankAccountFundBalanceSubRspDto subRspDto5 = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp5.getBody()).getRecordList().get(0);
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(subRspDto1.getTotalAmount())).subtract(new BigDecimal(Double.toString(tranAmount))).subtract(new BigDecimal(Double.toString(handFee)));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(subRspDto4.getTotalAmount()));
        Assert.assertEquals(bigDecimal1, bigDecimal2);
        bigDecimal1 = new BigDecimal(Double.toString(subRspDto2.getTotalAmount())).add(new BigDecimal(Double.toString(tranAmount)));
        bigDecimal2 = new BigDecimal(Double.toString(subRspDto5.getTotalAmount()));
        Assert.assertEquals(bigDecimal1, bigDecimal2);
        try {
            B2BMessage b2BMessageTemp6 = caseClient.queryMemberTrans(b2BMessageTemp3.getNetHeader().getThirdLogNo());
            Assert.assertEquals(((QueryMemberTransRspDto)b2BMessageTemp6.getBody()).getRecordList().get(0).getTranAmount(), tranAmount);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * ９．调用１３１８接口对Ｂ账户出金５０００并调用１０１０查询并核对
     */
    @Test(priority = 6)
    public void test9() {
        String accName = "测试一";
        Double tranAmount = 20d;
        B2BMessage b2BMessageTemp1 = caseClient.queryBankAccountFundBalance(accName);
        B2BMessage b2BMessageTemp2 = caseClient.outComeMoney(accName, tranAmount);
        B2BMessage b2BMessageTemp3 = caseClient.queryBankAccountFundBalance(accName);
        QueryBankAccountFundBalanceSubRspDto subRspDto1 = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp1.getBody()).getRecordList().get(0);
        QueryBankAccountFundBalanceSubRspDto subRspDto2 = ((QueryBankAccountFundBalanceRspDto) b2BMessageTemp3.getBody()).getRecordList().get(0);
        BigDecimal bigDecimal1 = new BigDecimal(Double.toString(subRspDto1.getTotalAmount())).subtract(new BigDecimal(Double.toString(tranAmount)));
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(subRspDto2.getTotalAmount()));
        Assert.assertEquals(bigDecimal1, bigDecimal2);
    }

    @Override
    protected BaseMessageBody createReqBody() throws UnsupportedEncodingException {
        Class reqClass = MessageType.getMessageType(funcId).getReqClass();
        for(Field field: CaseTest.class.getDeclaredFields()) {
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
