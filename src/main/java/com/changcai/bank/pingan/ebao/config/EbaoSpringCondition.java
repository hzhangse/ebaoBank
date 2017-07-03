/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public abstract class EbaoSpringCondition implements Condition {
    protected final static Logger LOGGER = LoggerFactory.getLogger(EbaoSpringCondition.class);

    protected String              conditionField;

    protected Boolean             isMatch;
    protected static Properties   prop;

    public static Properties getProp(ConditionContext context) {
        if (prop == null) {
            try {
                prop = new Properties();
                //                if (context.getEnvironment().getActiveProfiles() != null
                //                    && context.getEnvironment().getActiveProfiles().length > 0) 
                //String actValue = context.getEnvironment().getActiveProfiles()[0];
                String configFile = "classpath:" + JavaConfig.propName + ".properties";
                prop.load(context.getResourceLoader().getResource(configFile).getInputStream());

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return prop;
    }

    protected Boolean isMatched(ConditionContext context) {
        if (isMatch == null) {
            prop = getProp(context);
            isMatch = Boolean.valueOf(prop.getProperty(conditionField, "true"));
        }
        return isMatch;
    }

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return isMatched(context);
    }

    public void setConditionField(String conditionField) {
        this.conditionField = conditionField;
    }

}
