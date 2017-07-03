/**
 * 
 */
package com.changcai.bank.pingan.ebao.protocol.struct.req;

import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 签到、签退【1330】-入参
 * 接口说明：
 * 签到后，受理平台发起的出入金，签退后，不受理任何出入金请求。
 * @author ryan
 */
@Component
@Scope("prototype")
public class SignInOutReqDto extends RequestMessageBody {

	private static final long serialVersionUID = 1487970293547872109L;

	// 请求功能--1：签到 2：签退
	@NotNull(message="请求功能参数不能为空")
	@Length(min=1,max = 1,message="请求功能参数长度为1位")
	@Order(1)
	private String funcFlag="1";

	// 交易日期 -- 交易网签到或签退时的交易日期
	@NotNull(message="交易日期参数不能为空")
	@Length(min=8,max = 8,message="交易日期参数长度为8位")
	@Order(2)
	private String txDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

	// 保留域
	@Length(max = 120,message="保留域参数长度最大不超过120位")
	@Order(3)
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
		builder.append("&");
		builder.append(txDate);
		builder.append("&");
		builder.append(reserve);
		builder.append("&");
		return builder.toString();
	}
	
	
}
