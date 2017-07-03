/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.util.Map;

import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 消息头基类
 * @author ryan
 * @version $Id: BaseMessageHeader.java, v 0.1 2016年9月8日 下午5:28:59 ryan Exp $
 */
public class BaseMessageHeader extends BaseMessage {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMessageHeader.class);



    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        byte[] content = this.getMsgBytes(((MessageObjInput) in).getInputBytes());
        Map<Integer, Field> sortedMap = this.getSortedAttrMap();
        int beginIndex = 0;
        int endIndex = 0;
        for (Field field : sortedMap.values()) {
            try {
                field.setAccessible(true);
                Assert.isTrue(field.getAnnotation(Size.class) != null, "size of field does not set");
                int length = field.getAnnotation(Size.class).max();
                endIndex = endIndex + length;
                byte[] dest = new byte[length];
                System.arraycopy(content, beginIndex, dest, 0, length);
                String value = new String(dest,encoding);
                field.set(this, dest);
                LOGGER.debug(this+" set field:" +field.getName() +" value:"+value );
                beginIndex = endIndex;
                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOGGER.error(e.getMessage());
                Assert.isNull(e, e.getMessage());
            }
        }
    }

    @Override
    protected void addJoinChar(ObjectOutput out) throws IOException {
    }

   
}
