/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.changcai.bank.pingan.ebao.constant.ServType;

/**
 * 
 * 
 * @author ryan
 * @version $Id: B2BMessage.java, v 0.1 2016年9月9日 上午10:00:04 ryan Exp $
 */

//@Component
//@Scope("prototype")
public class B2BMessage extends BaseMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMessage.class);

    @Valid
    @Autowired
    private NetMsgHeader        netHeader;

    @Valid
    @Autowired
    private BizzMsgHeader       bizzHeader;

    @Valid
    private BaseMessageBody     body;

    @PostConstruct
    public void initValue() throws UnsupportedEncodingException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        String tradeTime = df.format(new Date());

        /* 生成随机数:当前精确到秒的时间再加6位的数字随机序列 */
        Random random = new Random();
        int ird = random.nextInt(999999);
        String srd = String.format("%06d", ird);
        String thirdLogNo = tradeTime + srd;

        this.setRspCode(StringUtils.repeat("9", 6));
        this.setServType(ServType.REQ.getCode());
        this.setThirdLogNo(thirdLogNo);
        this.setTradeDateTime(tradeTime);

    }

    public NetMsgHeader getNetHeader() {
        return netHeader;
    }

    public void setNetHeader(NetMsgHeader netHeader) {
        this.netHeader = netHeader;
    }

    public BizzMsgHeader getBizzHeader() {
        return bizzHeader;
    }

    public void setBizzHeader(BizzMsgHeader bizzHeader) {
        this.bizzHeader = bizzHeader;
    }

    public String getRspCode() {
        String rspCode = null;
        try {
            if (this.netHeader.getRspCode() != null)
                rspCode = this.netHeader.getRspCode();
            if (this.bizzHeader.getRspCode() != null)
                rspCode = this.bizzHeader.getRspCode();
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage());
        }
        return rspCode;
    }

    public BaseMessageBody getBody() {
        return body;
    }

    /**
     * 在设置msg的body同时,判断当前报文头里是否有消息长度,有的话调用body.setMsgLength,用于反序列化时
     * 解析body字节流时校验
     * 
     * @param body
     * @throws UnsupportedEncodingException
     */
    public void setBody(BaseMessageBody body) throws UnsupportedEncodingException {
        if (body != null) {
            this.body = body;
            if (this.bizzHeader.getRecMsgLength() != null && this.body.getMsgLength() < 0) {
                String lengStr = this.bizzHeader.getRecMsgLength();
                int i = Integer.parseInt(lengStr);
                this.body.setMsgLength(i);
            }
        }
    }

    public void setServType(String servType) throws UnsupportedEncodingException {
        this.netHeader.setServType(servType.getBytes(this.encoding));
        this.bizzHeader.setServType(servType.getBytes(this.encoding));
    }

    public String getTranFunc() throws UnsupportedEncodingException {
        return this.bizzHeader.getTranFunc();

    }

    public void setTradeDateTime(String tradeDateTime) throws UnsupportedEncodingException {
        if (tradeDateTime == null) {
            String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            this.netHeader.setTradeDateTime(date.getBytes(this.encoding));
            this.bizzHeader.setTradeDateTime(date.getBytes(this.encoding));
        }
        this.netHeader.setTradeDateTime(tradeDateTime.getBytes(this.encoding));
        this.bizzHeader.setTradeDateTime(tradeDateTime.getBytes(this.encoding));
    }

    public void setThirdLogNo(String thirdLogNo) throws UnsupportedEncodingException {
        this.netHeader.setThirdLogNo(thirdLogNo.getBytes(this.encoding));
        this.bizzHeader.setThirdLogNo(thirdLogNo.getBytes(this.encoding));
    }

    public void setRspCode(String rspCode) throws UnsupportedEncodingException {
    	if (this.netHeader==null){
    		this.netHeader = new NetMsgHeader();
    	}
    	if (this.bizzHeader==null){
    		this.bizzHeader = new BizzMsgHeader();
    	}
        this.netHeader.setRspCode(rspCode.getBytes(this.encoding));
        this.bizzHeader.setRspCode(rspCode.getBytes(this.encoding));
    }

    public void setRspMsg(String rspMsg) throws UnsupportedEncodingException {
        byte[] brspMsg = rspMsg.getBytes(this.encoding);
        byte[] netRspMsg = StringUtils.rightPad(StringUtils.SPACE, 100).getBytes(this.encoding);
        byte[] bizzRspMsg = StringUtils.rightPad(StringUtils.SPACE, 42).getBytes(this.encoding);
        System.arraycopy(brspMsg, 0, netRspMsg, 0,
            brspMsg.length > netRspMsg.length ? netRspMsg.length : brspMsg.length);
        System.arraycopy(brspMsg, 0, bizzRspMsg, 0,
            brspMsg.length > bizzRspMsg.length ? bizzRspMsg.length : brspMsg.length);
        this.netHeader.setRspMsg(netRspMsg);
        this.bizzHeader.setRspMsg(bizzRspMsg);
    }

    public void setConFlag(String conFlag) throws UnsupportedEncodingException {
        this.netHeader.setConFlag(conFlag.getBytes(this.encoding));
        this.bizzHeader.setConFlag(conFlag.getBytes(this.encoding));
    }

    public void setRecMsgLength(int bodyLength) throws UnsupportedEncodingException {
        int netMsgLength = BizzMsgHeader.headerLength + bodyLength;
        this.body.setMsgLength(bodyLength);
        String hLength = String.format("%08d", bodyLength);
        String hNetMsgLength = String.format("%010d", netMsgLength);
        this.netHeader.setRecMsgLength(hNetMsgLength.getBytes(this.encoding));
        this.bizzHeader.setRecMsgLength(hLength.getBytes(this.encoding));
    }

    public void setTranFunc(String tranFunc) throws UnsupportedEncodingException {
        this.bizzHeader.setTranFunc(tranFunc.getBytes(this.encoding));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        if (this.netHeader != null) {
            this.netHeader.readExternal(in);
        }
        if (this.bizzHeader != null) {
            this.bizzHeader.readExternal(in);
        }
        if (this.body != null) {
            this.body.readExternal(in);
        }
    }

    @Override
    protected void addJoinChar(ObjectOutput out) throws IOException {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Assert.isTrue(this.netHeader != null, "message netHeader is null");
        Assert.isTrue(this.bizzHeader != null, "message bizzHeader is null");
        //       Assert.isTrue(this.body != null, "message body is null");
        this.netHeader.writeExternal(out);
        this.bizzHeader.writeExternal(out);
        if (this.body != null)
            this.body.writeExternal(out);
    }

}
