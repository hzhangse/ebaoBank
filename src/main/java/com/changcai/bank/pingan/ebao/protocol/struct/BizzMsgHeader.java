/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.changcai.bank.pingan.ebao.constant.ServType;

/**
 * 业务报文头 接口报文包括：通讯报文头+业务报文头＋业务报文体。业务报文体见各交易定义 业务报文头统一如下，定长122位：
 * 
 * @author ryan
 *
 */

@Component
@Scope("prototype")
public class BizzMsgHeader extends BaseMessageHeader {
    public static final int headerLength = 122;

    public BizzMsgHeader() {
        this.msgLength = headerLength;
        this.startPos = NetMsgHeader.headerLength;
    }

    // 交易类型,见具体接口交易码
    @NotNull(message = "交易类型不能为空")
    @Size(min = 4, max = 4, message = "交易类型长度需要是4位")
    @Order(1)
    private byte[] tranFunc;

    // 服务类型,报文的功能标识码（01:请求02:应答）
    @NotNull
    @Size(min = 2, max = 2)
    @Order(2)
    private byte[] servType;

    // MAC码
    @NotNull
    @Size(min = 16, max = 16)
    @Order(3)
    private byte[] macCode;

    // 交易日期时间
    @NotNull
    @Size(min = 14, max = 14)
    @Order(4)
    private byte[] tradeDateTime;

    // 应答码
    @NotNull
    @Size(min = 6, max = 6)
    @Order(5)
    private byte[] rspCode;

    // 应答码描述
    @NotNull
    @Size(min = 42, max = 42)
    @Order(6)
    private byte[] rspMsg;

    // 后续包标志,后续包标志：0结束包,1还有后续包；
    @NotNull
    @Size(min = 1, max = 1)
    @Order(7)
    private byte[] conFlag;

    // 报文体长度,不包括报文头长度
    @NotNull(message = " 报文体长度不能为空")
    @Size(min = 8, max = 8, message = "报文体长度为8")
    @Order(8)
    private byte[] recMsgLength;

    // 操作员号
    @NotNull
    @Size(min = 5, max = 5)
    @Order(9)
    private byte[] counterId;

    // 请求方系统流水号,本交易的流水号,至少保证当天唯一性
    @NotNull(message = " 请求方系统流水号不能为空")
    @Size(min = 20, max = 20, message = "请求方系统流水号参数长度必须为20位")
    @Order(10)
    private byte[] thirdLogNo;

    // 交易网代码
    @NotNull(message = " 交易网代码不能为空")
    @Size(min = 4, max = 4, message = "交易网代码参数需要在4和4之间")
    @Order(11)
    private byte[] companyCode;

    @Value("${CompanyCode}")
    private String companyCodeStr;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        servType = ServType.REQ.getCode().getBytes(this.encoding);
        rspCode = StringUtils.repeat("9", 6).getBytes(this.encoding);
        conFlag = "0".getBytes(this.encoding);
        counterId = "PA001".getBytes(this.encoding);
        rspMsg = StringUtils.rightPad(StringUtils.SPACE, 42).getBytes(this.encoding);
        macCode = StringUtils.rightPad(StringUtils.SPACE, 16).getBytes(this.encoding);
        byte[] bcompanyCode = companyCodeStr.getBytes(this.encoding);
        companyCode = StringUtils.rightPad(StringUtils.SPACE, 4).getBytes(this.encoding);
        System.arraycopy(bcompanyCode, 0, companyCode, 0, bcompanyCode.length);
    }

    public String getTranFunc() throws UnsupportedEncodingException {
        if (tranFunc != null)
            return new String(tranFunc, this.encoding);
        else
            return null;
    }

    public void setTranFunc(byte[] tranFunc) {
        this.tranFunc = tranFunc;
    }

    public String getServType() throws UnsupportedEncodingException {
        if (servType != null)
            return new String(servType, this.encoding);
        else
            return null;
    }

    public void setServType(byte[] servType) {
        this.servType = servType;
    }

    public String getMacCode() throws UnsupportedEncodingException {
        if (macCode != null)
            return new String(macCode, this.encoding);
        else
            return null;
    }

    public void setMacCode(byte[] macCode) {
        this.macCode = macCode;
    }

    public String getTradeDateTime() throws UnsupportedEncodingException {
        if (tradeDateTime != null)
            return new String(tradeDateTime, this.encoding);
        else
            return null;
    }

    public void setTradeDateTime(byte[] tradeDateTime) {
        this.tradeDateTime = tradeDateTime;
    }

    public String getRspCode() throws UnsupportedEncodingException {
        if (rspCode != null)
            return new String(rspCode, this.encoding);
        else
            return null;
    }

    public void setRspCode(byte[] rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() throws UnsupportedEncodingException {
        if (rspMsg != null)
            return new String(rspMsg, this.encoding);
        else
            return null;
    }

    public void setRspMsg(byte[] rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getConFlag() throws UnsupportedEncodingException {
        if (conFlag != null)
            return new String(conFlag, this.encoding);
        else
            return null;
    }

    public void setConFlag(byte[] conFlag) {
        this.conFlag = conFlag;
    }

    public String getRecMsgLength() throws UnsupportedEncodingException {
        if (recMsgLength != null)
            return new String(recMsgLength, this.encoding);
        else
            return null;
    }

    public void setRecMsgLength(byte[] recMsgLength) {
        this.recMsgLength = recMsgLength;
    }

    public String getCounterId() throws UnsupportedEncodingException {
        if (counterId != null)
            return new String(counterId, this.encoding);
        else
            return null;
    }

    public void setCounterId(byte[] counterId) {
        this.counterId = counterId;
    }

    public String getThirdLogNo() throws UnsupportedEncodingException {
        if (thirdLogNo != null)
            return new String(thirdLogNo, this.encoding);
        else
            return null;
    }

    public void setThirdLogNo(byte[] thirdLogNo) {
        this.thirdLogNo = thirdLogNo;
    }

    public String getCompanyCode() throws UnsupportedEncodingException {
        if (companyCode != null)
            return new String(companyCode, this.encoding);
        else
            return null;
    }

    public void setCompanyCode(byte[] companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCodeStr() {
        return companyCodeStr;
    }

    public void setCompanyCodeStr(String companyCodeStr) {
        this.companyCodeStr = companyCodeStr;
    }

}
