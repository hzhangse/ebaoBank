/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;

/**
 * 
 * @author ryan
 * @version $Id: RspBodyFactoryBean.java, v 0.1 2016年9月17日 下午10:00:44 ryan Exp $
 */
@Component(RspBodyFactoryBean.beanName)
public class RspBodyFactoryBean implements FactoryBean<BaseMessageBody> ,ApplicationContextAware{

    public static final String beanName = "rspBody";
    private String             funcId;
    private ApplicationContext context;
    @Override
    public BaseMessageBody getObject() throws Exception {
        MessageType messageType = MessageType.getMessageType(funcId);
        return messageType == null? null: context.getBean(messageType.getRspClass());
    }

    @Override
    public Class<?> getObjectType() {
        return BaseMessageBody.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            JavaConfig.class);
        RspBodyFactoryBean factory = context.getBean(RspBodyFactoryBean.class);
        factory.setFuncId(MessageType.OUTCOME_MONEY.value());
        BaseMessageBody body = (BaseMessageBody) context.getBean(beanName);
        context.close();
    }

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		 context = applicationContext;
	}
}
