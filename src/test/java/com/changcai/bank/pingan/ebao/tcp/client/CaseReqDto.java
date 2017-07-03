package com.changcai.bank.pingan.ebao.tcp.client;

import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.req.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author gaoxiaofei
 * @since 2016/10/09
 * 初始化reqDto
 */
@Component
@Scope("prototype")
public class CaseReqDto {

    public void initQueryBankAccountFundBalanceReqDto(AccountInfo accountInfo, QueryBankAccountFundBalanceReqDto queryBankAccountFundBalanceReqDto) throws UnsupportedEncodingException {
        System.out.println("queryBankAccountFundBalanceReqDto: "+queryBankAccountFundBalanceReqDto);
        queryBankAccountFundBalanceReqDto.setThirdCustId(accountInfo.getThirdCustId());
        queryBankAccountFundBalanceReqDto.setCustAcctId(accountInfo.getAccNo());
        queryBankAccountFundBalanceReqDto.setSelectFlag("1");
        queryBankAccountFundBalanceReqDto.setPageNum("1");
        queryBankAccountFundBalanceReqDto.setReserve("");
    }

    public void initOutComeMoneyReqDto(AccountInfo accountInfo, Double tranAmount, OutComeMoneyReqDto outComeMoneyReqDto) throws UnsupportedEncodingException {
        outComeMoneyReqDto.setThirdCustId(accountInfo.getThirdCustId());
        outComeMoneyReqDto.setIdType(accountInfo.getIdType());
        outComeMoneyReqDto.setIdCode(accountInfo.getIdNo());
        outComeMoneyReqDto.setCustAcctId(accountInfo.getAccNo());
        outComeMoneyReqDto.setCustName(accountInfo.getAccName());
        outComeMoneyReqDto.setOutAcctId(accountInfo.getRelatedAcctId());
        outComeMoneyReqDto.setOutAcctIdName(accountInfo.getAccName());
        outComeMoneyReqDto.setTranAmount(tranAmount);
        outComeMoneyReqDto.setReserve("");
    }

    public void initInComeMoney1316ReqDto(AccountInfo accountInfo, Double tranAmount, InComeMoney1316ReqDto inComeMoney1316ReqDto) throws UnsupportedEncodingException {
        inComeMoney1316ReqDto.setCustAcctId(accountInfo.getAccNo());
        inComeMoney1316ReqDto.setTranAmount(tranAmount);
        inComeMoney1316ReqDto.setIdCode(accountInfo.getIdNo());
        inComeMoney1316ReqDto.setIdType(accountInfo.getIdType());
        inComeMoney1316ReqDto.setThirdCustId(accountInfo.getThirdCustId());
        inComeMoney1316ReqDto.setInAcctId(accountInfo.getRelatedAcctId());
        inComeMoney1316ReqDto.setInAcctIdName(accountInfo.getAccName());
        inComeMoney1316ReqDto.setCcyCode("RMB");
        inComeMoney1316ReqDto.setReserve(accountInfo.getThirdCustId());
    }
    public void initQueryMoneyRecordSetReqDto(String beginDate, String endDate, String pageNum, String origThirdLogNo, QueryMoneyRecordSetReqDto queryMoneyRecordSetReqDto) throws UnsupportedEncodingException {
        queryMoneyRecordSetReqDto.setBeginDate(beginDate);
        queryMoneyRecordSetReqDto.setEndDate(endDate);
        queryMoneyRecordSetReqDto.setPageNum(pageNum);
        queryMoneyRecordSetReqDto.setOrigThirdLogNo(origThirdLogNo);
        queryMoneyRecordSetReqDto.setReserve("");
    }

    public void initQueryBankAcountBalanceReqDto(AccountInfo accountInfo, QueryBankAcountBalanceReqDto queryBankAcountBalanceReqDto) throws UnsupportedEncodingException {
        queryBankAcountBalanceReqDto.setThirdCustId(accountInfo.getThirdCustId());
        queryBankAcountBalanceReqDto.setCustAcctId(accountInfo.getAccNo());
        queryBankAcountBalanceReqDto.setCustName(accountInfo.getAccName());
        queryBankAcountBalanceReqDto.setAcctNo(accountInfo.getRelatedAcctId());
        queryBankAcountBalanceReqDto.setReserve("买豆粕监管账户");
    }
    public void initLockAccountReqDto(AccountInfo accountInfo, Double tranAmount, LockAccountReqDto lockAccountReqDto) throws UnsupportedEncodingException {
        lockAccountReqDto.setFuncFlag("1");
        lockAccountReqDto.setCustAcctId(accountInfo.getAccNo());
        lockAccountReqDto.setThirdCustId(accountInfo.getThirdCustId());
        lockAccountReqDto.setTranAmount(tranAmount);
        lockAccountReqDto.setThirdHtId(UUID.randomUUID().toString().substring(0,29));
        lockAccountReqDto.setReserve("保留字reserve");
    }
    public void initPayMoneyReqDto(AccountInfo infoout, AccountInfo infoin, Double tranAmount, Double handFee, PayMoneyReqDto payMoneyReqDto) throws UnsupportedEncodingException {
        payMoneyReqDto.setFuncFlag("1");
        payMoneyReqDto.setOutCustAcctId(infoout.getAccNo());
        payMoneyReqDto.setOutThirdCustId(infoout.getThirdCustId());
        payMoneyReqDto.setInCustAcctId(infoin.getAccNo());
        payMoneyReqDto.setInThirdCustId(infoin.getThirdCustId());
        payMoneyReqDto.setTranAmount(tranAmount);
        payMoneyReqDto.setHandFee(handFee);
        payMoneyReqDto.setPaySerialNo(UUID.randomUUID().toString().substring(0,29));
        payMoneyReqDto.setThirdHtId(UUID.randomUUID().toString().substring(0,29));
        payMoneyReqDto.setReserve("");
    }
    public void initQueryMemberTransReqDto(String origThirdLogNo, QueryMemberTransReqDto queryMemberTransReqDto) throws UnsupportedEncodingException {
        queryMemberTransReqDto.setBeginDate("20161007");
        queryMemberTransReqDto.setEndDate("20161020");
        queryMemberTransReqDto.setPageNum("1");
        queryMemberTransReqDto.setOrigThirdLogNo(origThirdLogNo);
        queryMemberTransReqDto.setReserve("");
    }
}
