/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author ryan 消息报文基类
 */
public class BaseMessageBody extends BaseMessage {

    private ContentArrCls       contentArrCls         = new ContentArrCls();

    private static final Logger LOGGER                = LoggerFactory
                                                          .getLogger(BaseMessageBody.class);

    protected final String      joinChar              = "&";

    private final static String FIELD_NAME_RECORD_NUM = "recordNum";

    public BaseMessageBody() {
        this.msgLength = -1;
        this.startPos = NetMsgHeader.headerLength + BizzMsgHeader.headerLength;
    }

    @Override
    protected void verifyMsgLength(byte[] src) {
        int headerLength = NetMsgHeader.headerLength + BizzMsgHeader.headerLength;
        int bodyLength = src.length - headerLength;
        Assert.isTrue(this.msgLength == bodyLength, "verify msg body Length faild !");
    }

    /**
     * 
     * 
     * @param index
     * @param field
     * @throws Exception
     */
    protected void setFieldValue(int index, Field field) throws Exception {
        setFieldValue(index, field, this);
    }

    /**
     * 
     * 
     * @param index
     * @param field
     * @param obj
     * @throws Exception
     */
    protected void setFieldValue(int index, Field field, Object obj) throws Exception {
        this.contentArrCls.setIndex(index);
        field.setAccessible(true);
        Class<?> type = field.getType();
        Object value = null;
        if (type != List.class) {
            if (type == Double.class) {
                Constructor<?> con = type.getConstructor(String.class);
                value = con.newInstance(this.contentArrCls.getContentValue());
            } else if (type == byte[].class) {
                value = this.contentArrCls.getContentValue().getBytes(this.encoding);
            } else if (type == String.class) {
                value = this.contentArrCls.getContentValue();
            } else {
                Assert.isNull(this.contentArrCls.getContentValue(), obj + " field:" + field.getName() + " value:"
                                                      + this.contentArrCls.getContentValue() + " type:" + type
                                                      + " need be set !");
            }
            field.set(obj, value);
            LOGGER
                .debug(obj + " set field:" + field.getName() + " value:" + this.contentArrCls.getContentValue());
        } else if (type == List.class) {
            this.setListFieldValue(field, index);
        }

        field.setAccessible(false);
    }

    private boolean hasListTypeField(BaseMessageBody body) {
        Map<Integer, Field> sortedMap = this.getSortedAttrMap();
        boolean hasListElement = false;
        for (Field field : sortedMap.values()) {
            Type type = field.getType();
            if (type == List.class) {
                hasListElement = true;
                break;
            }
        }
        return hasListElement;
    }

    private int getRecordSetSize(BaseMessageBody body) throws Exception {
        int size = 0;
        Map<Integer, Field> sortedMap = this.getSortedAttrMap();
        for (Field field : sortedMap.values()) {
            if (field.getName().equalsIgnoreCase(FIELD_NAME_RECORD_NUM)) {
                field.setAccessible(true);
                if (field.getType() == byte[].class) {
                    size = Integer.parseInt(new String((byte[]) field.get(body), this.encoding));
                }

                field.setAccessible(false);
                if (size == 0) {
                    size = 1;
                }
                break;
            }
        }
        return size;
    }

    /**
     * 设置List 类型的对象值
     * 
     * @param field   List 类型字段实例
     * @param contentArr  消息对应的String数组
     * @param index  消息对应的String数组的当前索引数
     * @throws Exception
     */
    private int setListFieldValue(Field field, int index) throws Exception {
        int beginIndex = index;
        int size = getRecordSetSize(this);
        //获取list里面元素的类型
        ParameterizedType pt = (ParameterizedType) field.getGenericType();
        Class recordType = (Class) pt.getActualTypeArguments()[0];

        //创建记录元素实例
        BaseMessage subRecord = (BaseMessage) recordType.newInstance();

        //获取记录元素对应字段排序map
        Map<Integer, Field> subSortedMap = subRecord.getSortedAttrMap();

        //获取messagebody对应字段排序map
        Map<Integer, Field> sortedMap = this.getSortedAttrMap();

        //计算messagebody 字段长度, 减一是因为list类型字段不包含在返回字符串中,故不计算在内
        int totalFieldSize = size * subSortedMap.values().size() + sortedMap.size() - 1;
        Assert.isTrue(this.contentArrCls.getContentArr().length== totalFieldSize,
            "invalid content when reading input stream");

        //初始化记录List
        List<BaseMessage> recordList = new ArrayList<BaseMessage>();
        field.set(this, recordList);

        for (int i = 0; i < size; i++) {
            int indexSub = 0;
            for (Field subfield : subSortedMap.values()) {
                setFieldValue(index, subfield, subRecord);
                indexSub++;
                if (indexSub < subSortedMap.values().size()) {
                    index++;
                }
            }
            recordList.add(subRecord);
            if (size - i > 1) {
                subRecord = (BaseMessage) recordType.newInstance();
                index++;
            }
        }
        if (index == beginIndex + size * subSortedMap.values().size())
            return index;
        else
            return -1;
    }

    /**
     * 读取报文字节码并转化成消息报文body对象
     * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte[] contentByteArr = this.getMsgBytes(((MessageObjInput) in).getInputBytes());
        String content = new String(contentByteArr, encoding);
        if (content.endsWith(joinChar)) {
            content = content.substring(0, content.length() - 1);
        }
        this.contentArrCls.setContentArr(StringUtils.splitPreserveAllTokens(content, joinChar));
        Map<Integer, Field> sortedMap = this.getSortedAttrMap();
        boolean hasListField = hasListTypeField(this);
        if (!hasListField) {
            Assert.isTrue(this.contentArrCls.getContentArr().length == sortedMap.size(),
                "invalid content when reading msg body");
        }
        int index = 0;
        for (Field field : sortedMap.values()) {
            try {
                setFieldValue(index, field);
                index = this.contentArrCls.getIndex();
                index++;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                Assert.isNull(e, e.getMessage());
            }
        }
    }

    @Override
    protected void addJoinChar(ObjectOutput out) throws IOException {
        out.writeChar('&');
    }

 
}
