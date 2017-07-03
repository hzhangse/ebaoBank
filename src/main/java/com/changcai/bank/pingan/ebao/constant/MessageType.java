/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.constant;

import java.lang.reflect.Field;

import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.InComeMoney1310ReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.InComeMoney1316ReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.LockAccountReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.MemberRegistInReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.OutComeMoney1312ReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.OutComeMoneyReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.PayMoneyReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryAccFilePasswordReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryBankAccountFundBalanceReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryBankAcountBalanceReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMemberSignInfoReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMemberTransReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMoneyRecordSetReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMonitorAccInfoReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryPayRecordSetReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.SignInOutReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.BankRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.BuzzRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.OutComeMoneyRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryAccFilePasswordRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryBankAccountBalanceRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryBankAccountFundBalanceRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryMemberSignInfoRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryMemberTransRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryMoneyRecordSetRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryMonitorAccInfoRspDto;
import com.changcai.bank.pingan.ebao.protocol.struct.rsp.QueryPayRecordSetRspDto;

/**
 *
 *
 * @author ryan
 * @version $Id: MessageType.java, v 0.1 2016年9月8日 上午11:06:29 ryan Exp $
 */
public enum MessageType {

    // 查银行端会员资金台帐余额【1010】
    QUERY_BANK_ACCOUNT_FUND_BALANCE("1010", QueryBankAccountFundBalanceReqDto.class, QueryBankAccountFundBalanceRspDto.class),
    // 查会员出入金账号的银行余额【1020】
    QUERY_BANK_ACCOUNT_INCOME_MONEY_BALANCE("1020", QueryBankAcountBalanceReqDto.class, QueryBankAccountBalanceRspDto.class),
    // 入金（银行发起）【1310】
    INCOME_MONEY_FROM_BANK("1310", InComeMoney1310ReqDto.class, BuzzRspDto.class),
    // 出金（银行发起）【1312】 *OutComeMoneyReqDto 不匹配，修改为新增的OutComeMoney1312ReqDto
    OUTCOME_MONEY_FROMBANK("1312", OutComeMoney1312ReqDto.class, BuzzRspDto.class),
    // 入金（交易网发起）【1316】
    INCOME_MONEY("1316", InComeMoney1316ReqDto.class, BankRspDto.class),
    // 出金（交易网发起）【1318】
    OUTCOME_MONEY("1318", OutComeMoneyReqDto.class, OutComeMoneyRspDto.class),
    // 查询时间段会员交易流水信息【1324】
    QUERY_MEMBER_TRANS("1324", QueryMemberTransReqDto.class, QueryMemberTransRspDto.class),
    // 查询时间段会员出入金流水信息【1325】
    QUERY_MONEY_RECORDSET("1325", QueryMoneyRecordSetReqDto.class, QueryMoneyRecordSetRspDto.class),
    // 查询支付指令状态【1327】
    QUERY_PAY_STATUS("1327", QueryPayRecordSetReqDto.class, QueryPayRecordSetRspDto.class),
    // 查时间段会员开销户信息【1016】
    QUERY_MEMBER_SIGN_INFO("1016", QueryMemberSignInfoReqDto.class, QueryMemberSignInfoRspDto.class),
    // 监管账户信息查询【1021】
    QUERY_MONITOR_ACC_INFO("1021", QueryMonitorAccInfoReqDto.class, QueryMonitorAccInfoRspDto.class),
    // 查询对账文件密码【1349】
    QUERY_ACC_FILE_PASSWORD("1349", QueryAccFilePasswordReqDto.class, QueryAccFilePasswordRspDto.class),
    // 子账户直接支付【1329】-不建议使用
    PAY_MONEY_DIRECT("1329", PayMoneyReqDto.class, BankRspDto.class),
    // 子账户间支付【1332】
    PAY_MONEY_BTW("1332", PayMoneyReqDto.class, BankRspDto.class),
    // 签到、签退【1330】 *SignInOutRspDto 弃用，改用BankRspDto
    SIGN_IN_OUT("1330", SignInOutReqDto.class, BankRspDto.class),
    // 会员签解约维护【1303】
    MEMBER_REGIST_IN("1303", MemberRegistInReqDto.class, BuzzRspDto.class),
    // 子账户冻结解冻【1029】
    LOCK_UNLOCK_ACCOUNT("1029", LockAccountReqDto.class, BankRspDto.class);

	private String  value;

	private Class<? extends RequestMessageBody> reqClass;

	private Class<? extends ResponseMessageBody> rspClass;

	private MessageType(String value,Class<? extends RequestMessageBody> reqClass,Class<? extends ResponseMessageBody> rspClass) {
		this.value = value;
		this.reqClass = reqClass;
		this.rspClass = rspClass;
	}

	public String value() {
		return this.value;
	}

	/**
	 *
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
        return this.value;
    }

	public  static MessageType getMessageType(String funcId) {
		MessageType result = null;
		Field[] flds = MessageType.class.getFields();
		for (Field fld : flds) {
			MessageType fldValue = Enum.valueOf(MessageType.class, fld.getName());
			String messageId = Enum.valueOf(MessageType.class, fld.getName()).value();
			if (funcId.equalsIgnoreCase(messageId)) {
				result = fldValue;
				break;
			}
		}
		return result;
	}

	 public String getValue() {
        return value;

    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<? extends RequestMessageBody> getReqClass() {
        return reqClass;
    }

    public void setReqClass(Class<? extends RequestMessageBody> reqClass) {
        this.reqClass = reqClass;
    }

    public Class<? extends BaseMessageBody> getRspClass() {
        return rspClass;
    }

    public void setRspClass(Class<? extends ResponseMessageBody> rspClass) {
        this.rspClass = rspClass;
    }

    public static void  main(String[] args){
	     MessageType fld=   getMessageType("1010");
	     System.out.println(fld.name());
	 }
}
