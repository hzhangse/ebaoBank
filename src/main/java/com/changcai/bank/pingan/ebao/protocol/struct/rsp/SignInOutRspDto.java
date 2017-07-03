/**
 * 
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ryan 签到、签退交易接口入参
 */
@Component
@Scope("prototype")
public class SignInOutRspDto  extends ResponseMessageBody implements Serializable {

	private static final long serialVersionUID = 1487970293547872109L;

	// 请求功能--1：签到 2：签退
	@NotNull(message="请求功能参数不能为空")
	@Length(min=1,max = 1,message="请求功能参数长度为1位")
	private String funcFlag;

	// 交易日期 -- 交易网签到或签退时的交易日期
	@NotNull(message="交易日期参数不能为空")
	@Length(min=8,max = 8,message="交易日期参数长度为8位")
	private String txDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

	// 保留域
	@Length(max = 120,message="保留域参数长度最大不超过120位")
	private String reserve;

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	public void setTxDate(String txDate) {
		this.txDate = txDate;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public String getTxDate() {
		return txDate;
	}

	public String getReserve() {
		return reserve;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(funcFlag);
		builder.append("& ");
		builder.append(txDate);
		builder.append("& ");
		builder.append(reserve);
		return builder.toString();
	}
	
	
}
