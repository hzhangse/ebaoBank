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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.changcai.bank.pingan.ebao.constant.ServType;

/**
 * 通信报文头 受银行系统统一的互联网外联标准规范的约束，接口报文须增加222位的通信报文头。 通信报文头统一如下：
 * 
 * @author ryan
 *
 */
@Component
@Scope("prototype")
public class NetMsgHeader extends BaseMessageHeader {

    private static final Logger LOGGER       = LoggerFactory.getLogger(NetMsgHeader.class);
    public static final int     headerLength = 222;

    public NetMsgHeader() {
        this.msgLength = headerLength;
        this.startPos = 0;
    }

    // 报文类别--默认：A001标准报文头
    @NotNull
    @Size(min = 4, max = 4)
    @Order(1)
    private byte[] msgCategory;

    // 目标系统--默认：03 交易资金
    @NotNull
    @Size(min = 2, max = 2)
    @Order(2)
    private byte[] targetSystem;

    // 报文编码--01：GBK
    @NotNull
    @Size(min = 2, max = 2)
    @Order(3)
    private byte[] msgEncoding;

    // 通讯协议--01:tcpip 缺省,02：http
    @NotNull
    @Size(min = 2, max = 2)
    @Order(4)
    private byte[] protocal;

    // 企业银企直连标准代码,（企业上线的时候由银行分配）与业务报文头的交易网代码一致
    @NotNull(message = "企业银企直连标准代码不能为空")
    @Size(min = 20, max = 20, message = "企业银企直连标准代码参数长度必须20位")
    @Order(5)
    private byte[] companyCode;

    @Value("${CompanyCode}")
    private String companyCodeStr;

    // 接收报文长度,报文数据长度=122+业务报文体
    @NotNull(message = "接收报文长度不能为空")
    @Size(min = 10, max = 10, message = "接收报文长度参数长度必须10位")
    @Order(6)
    private byte[] recMsgLength;

    // 交易码,通信报文统一交易码:000000
    @NotNull
    @Size(min = 6, max = 6)
    @Order(7)
    private byte[] tradeCode;

    // 操做员代码
    @NotNull
    @Size(min = 5, max = 5)
    @Order(8)
    private byte[] opCode;

    // 服务类型,01:请求 02:应答
    @NotNull
    @Size(min = 2, max = 2)
    @Order(9)
    private byte[] servType;

    // 交易日期时间,yyyyMMddHHmmss
    @NotNull
    @Size(min = 14, max = 14)
    @Order(10)
    private byte[] tradeDateTime;

    // 请求方系统流水号,唯一标识一笔交易（企业随机生成唯一流水号，可与业务报文头的流水号一致）
    @NotNull(message = "请求方系统流水号不能为空")
    @Size(min = 20, max = 20, message = "请求方系统流水号参数长度必须为20位")
    @Order(11)
    private byte[] thirdLogNo;

    // 返回码,默认：000000
    @NotNull
    @Size(min = 6, max = 6)
    @Order(12)
    private byte[] rspCode;

    // 返回描述
    @NotNull
    @Size(min = 100, max = 100)
    @Order(13)
    private byte[] rspMsg;

    // 后续包标志,0-结束包
    @NotNull
    @Size(min = 1, max = 1)
    @Order(14)
    private byte[] conFlag;

    // 请求次数
    @NotNull
    @Size(min = 3, max = 3)
    @Order(15)
    private byte[] reqTimes;

    // 签名标识,填0，企业不管，由银行客户端完成
    @NotNull
    @Size(min = 1, max = 1)
    @Order(16)
    private byte[] signFlag;

    // 签名数据包格式,填1，企业不管，由银行客户端完成
    @NotNull
    @Size(min = 1, max = 1)
    @Order(17)
    private byte[] signPacketType;

    // 签名算法
    @NotNull
    @Size(min = 12, max = 12)
    @Order(18)
    private byte[] signAlgorithm;

    // 签名数据长度,填0，签名报文数据长度
    @NotNull(message = " 签名报文数据长度不能为空")
    @Size(min = 10, max = 10, message = "签名报文数据长度参数长度必须为10位")
    @Order(19)
    private byte[] signDataLength;

    // 附件数目,默认为0
    @NotNull
    @Size(min = 1, max = 1)
    @Order(20)
    private byte[] attachNum;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        msgCategory = "A001".getBytes(this.encoding);
        targetSystem = "03".getBytes(this.encoding);
        msgEncoding = "01".getBytes(this.encoding);
        protocal = "01".getBytes(this.encoding);
        tradeCode = "000000".getBytes(this.encoding);
        opCode = "PA001".getBytes(this.encoding);
        rspCode = "000000".getBytes(this.encoding);
        servType = ServType.REQ.getCode().getBytes(this.encoding);
        signDataLength = StringUtils.repeat("0", 10).getBytes(this.encoding);
        attachNum = "0".getBytes(this.encoding);
        signAlgorithm = StringUtils.rightPad("RSA-SHA1", 12).getBytes(this.encoding);
        reqTimes = "000".getBytes(this.encoding);
        signPacketType = "1".getBytes(this.encoding);
        signFlag = "0".getBytes(this.encoding);
        conFlag = "0".getBytes(this.encoding);
        rspMsg = StringUtils.rightPad(StringUtils.SPACE, 100).getBytes(this.encoding);
        byte[] bcompanyCode = companyCodeStr.getBytes(this.encoding);
        companyCode = StringUtils.rightPad(StringUtils.SPACE, 20).getBytes(this.encoding);
        System.arraycopy(bcompanyCode, 0, companyCode, 0, bcompanyCode.length);
    }

    public String getMsgCategory() throws UnsupportedEncodingException {
        if (msgCategory != null) {
            return new String(msgCategory, this.encoding);
        } else
            return null;
    }

    public void setMsgCategory(byte[] msgCategory) {
        this.msgCategory = msgCategory;
    }

    public String getTargetSystem() throws UnsupportedEncodingException {
        if (targetSystem != null) {
            return new String(targetSystem, this.encoding);
        } else
            return null;
    }

    public void setTargetSystem(byte[] targetSystem) {
        this.targetSystem = targetSystem;
    }

    public String getMsgEncoding() throws UnsupportedEncodingException {
        if (msgEncoding != null) {
            return new String(msgEncoding, this.encoding);
        } else
            return null;
    }

    public void setMsgEncoding(byte[] msgEncoding) {
        this.msgEncoding = msgEncoding;
    }

    public String getProtocal() throws UnsupportedEncodingException {
        if (protocal != null) {
            return new String(protocal, this.encoding);
        } else
            return null;
    }

    public void setProtocal(byte[] protocal) {
        this.protocal = protocal;
    }

    public String getCompanyCode() throws UnsupportedEncodingException {
        if (companyCode != null) {
            return new String(companyCode, this.encoding);
        } else
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

    public String getRecMsgLength() throws UnsupportedEncodingException {
        if (recMsgLength != null) {
            return new String(recMsgLength, this.encoding);
        } else
            return null;
    }

    public void setRecMsgLength(byte[] recMsgLength) {
        this.recMsgLength = recMsgLength;
    }

    public String getTradeCode() throws UnsupportedEncodingException {
        if (tradeCode != null) {
            return new String(tradeCode, this.encoding);
        } else
            return null;
    }

    public void setTradeCode(byte[] tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getOpCode() throws UnsupportedEncodingException {
        if (opCode != null) {
            return new String(opCode, this.encoding);
        } else
            return null;
    }

    public void setOpCode(byte[] opCode) {
        this.opCode = opCode;
    }

    public String getServType() throws UnsupportedEncodingException {
        if (servType != null) {
            return new String(servType, this.encoding);
        } else
            return null;
    }

    public void setServType(byte[] servType) {
        this.servType = servType;
    }

    public String getTradeDateTime() throws UnsupportedEncodingException {
        if (tradeDateTime != null) {
            return new String(tradeDateTime, this.encoding);
        } else
            return null;
    }

    public void setTradeDateTime(byte[] tradeDateTime) {
        this.tradeDateTime = tradeDateTime;
    }

    public String getThirdLogNo() throws UnsupportedEncodingException {
        if (thirdLogNo != null) {
            return new String(thirdLogNo, this.encoding);
        } else
            return null;
    }

    public void setThirdLogNo(byte[] thirdLogNo) {
        this.thirdLogNo = thirdLogNo;
    }

    public String getRspCode() throws UnsupportedEncodingException {
        if (rspCode != null) {
            return new String(rspCode, this.encoding);
        } else
            return null;
    }

    public void setRspCode(byte[] rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() throws UnsupportedEncodingException {
        if (rspMsg != null) {
            return new String(rspMsg, this.encoding);
        } else
            return null;
    }

    public void setRspMsg(byte[] rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getConFlag() throws UnsupportedEncodingException {
        if (conFlag != null) {
            return new String(conFlag, this.encoding);
        } else
            return null;
    }

    public void setConFlag(byte[] conFlag) {
        this.conFlag = conFlag;
    }

    public String getReqTimes() throws UnsupportedEncodingException {
        if (reqTimes != null) {
            return new String(reqTimes, this.encoding);
        } else
            return null;
    }

    public void setReqTimes(byte[] reqTimes) {
        this.reqTimes = reqTimes;
    }

    public String getSignFlag() throws UnsupportedEncodingException {
        if (signFlag != null) {
            return new String(signFlag, this.encoding);
        } else
            return null;
    }

    public void setSignFlag(byte[] signFlag) {
        this.signFlag = signFlag;
    }

    public String getSignPacketType() throws UnsupportedEncodingException {
        if (signPacketType != null) {
            return new String(signPacketType, this.encoding);
        } else
            return null;
    }

    public void setSignPacketType(byte[] signPacketType) {
        this.signPacketType = signPacketType;
    }

    public String getSignAlgorithm() throws UnsupportedEncodingException {
        if (signAlgorithm != null) {
            return new String(signAlgorithm, this.encoding);
        } else
            return null;
    }

    public void setSignAlgorithm(byte[] signAlgorithm) {
        this.signAlgorithm = signAlgorithm;
    }

    public String getSignDataLength() throws UnsupportedEncodingException {
        if (signDataLength != null) {
            return new String(signDataLength, this.encoding);
        } else
            return null;
    }

    public void setSignDataLength(byte[] signDataLength) {
        this.signDataLength = signDataLength;
    }

    public String getAttachNum() throws UnsupportedEncodingException {
        if (attachNum != null) {
            return new String(attachNum, this.encoding);
        } else
            return null;
    }

    public void setAttachNum(byte[] attachNum) {
        this.attachNum = attachNum;
    }

}
