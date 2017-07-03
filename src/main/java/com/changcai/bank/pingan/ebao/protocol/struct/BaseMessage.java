/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author ryan 消息基类
 */
public abstract class BaseMessage extends ValidateDto implements Externalizable {

    private static final Logger LOGGER   = LoggerFactory.getLogger(BaseMessage.class);

    //报文字段的排序Map
    @JSONField(serialize = false)
    private Map<Integer, Field> sortedAttrMap;
    
    @JSONField(serialize = false)
    protected String            encoding = "GBK";

    //消息长度
    @JSONField(serialize = false)
    protected int               msgLength;

    //消息开始位置
    @JSONField(serialize = false)
    protected int               startPos;

    /**
     * 获取消息的字节数组
     * 
     * @param src
     * @return
     */
    protected final byte[] getMsgBytes(byte[] src) {
        verifyMsgLength(src);
        byte[] dest = new byte[this.msgLength];
        System.arraycopy(src, this.startPos, dest, 0, this.msgLength);
        return dest;
    }

    /**
     * 验证消息长度
     * 
     * @param src
     */
    protected void verifyMsgLength(byte[] src) {
    }

    /**
     * 获取需要序列化消息的字段set
     * 
     * @return
     */
    private Set<Field> getMessageFieldSet() {
        Set<Field> fieldSet = new HashSet<Field>();
        Class<?> clazz = this.getClass();
        for (; clazz != BaseMessage.class; clazz = clazz.getSuperclass()) {
            fieldSet.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        return fieldSet;
    }

   
    protected final Map<Integer, Field> getSortedAttrMap() {
        if (sortedAttrMap == null) {
            Set<Field> fields = this.getMessageFieldSet();
            Map<Integer, Field> sortedMap = new TreeMap<Integer, Field>();
            for (Field field : fields) {
                Order order = field.getAnnotation(Order.class);
                if (order != null) {
                    sortedMap.put(order.value(), field);
                }
            }
            sortedAttrMap = sortedMap;
        }
        Assert.notNull(this,"message must not contain any field !");
        return sortedAttrMap;
    }

    /**
     * 设置msgbody的字段连接符
     * 
     * @param out
     * @throws IOException
     */
    protected abstract void addJoinChar(ObjectOutput out) throws IOException;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Map<Integer, Field> sortedMap = this.getSortedAttrMap();
        for (Field field : sortedMap.values()) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value != null) {
                    if (value instanceof String) {
                        out.writeBytes((String) value);
                    } else if (value instanceof byte[]) {
                        out.write((byte[]) value);
                        value = new String((byte[]) value, this.encoding);
                    } else if (value instanceof Double) {
                        out.writeBytes(((Double) value).toString());
                    } else {
                        Assert.isNull(value, " field: " + field.getName() + " value: " + value
                                             + " is not set");
                    }
                }

                LOGGER.debug("write obj:" + this.toString() + " field: " + field.getName()
                             + " value: " + value);
                addJoinChar(out);
                field.setAccessible(false);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                Assert.isNull(e, e.getMessage());
            }
        }
    }

    /**
     * Getter method for property <tt>msgLength</tt>.
     * 
     * @return property value of msgLength
     */
    public int getMsgLength() {
        return msgLength;
    }

    /**
     * Setter method for property <tt>msgLength</tt>.
     * 
     * @param msgLength value to be assigned to property msgLength
     */
    public void setMsgLength(int msgLength) {
        this.msgLength = msgLength;
    }

    /**
     * Getter method for property <tt>startPos</tt>.
     * 
     * @return property value of startPos
     */
    public int getStartPos() {
        return startPos;
    }

    /**
     * Setter method for property <tt>startPos</tt>.
     * 
     * @param startPos value to be assigned to property startPos
     */
    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * 写入当前msg对象到byte数组,并返回
     * 
     * @param obj
     * @return
     * @throws IOException
     */
    public byte[] writeMessage() throws IOException {
        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        MessageObjOutput out = new MessageObjOutput(baops, Charset.forName(this.getEncoding()));
        out.writeObject(this);
        byte[] bytes = baops.toByteArray();
        out.close();
        return bytes;
    }
    
    /**
     * used to json string 
     * 
     * @return
     */
    public String toJSONString(){
        String jsonString = JSON.toJSONString(this);
        return jsonString;
    }
}
