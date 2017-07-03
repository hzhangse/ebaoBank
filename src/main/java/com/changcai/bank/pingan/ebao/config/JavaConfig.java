/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.changcai.bank.pingan.ebao.aop.EbaoTimeCostPrintAspectCondition;

/**
 * 
 * @author ryan
 * @version $Id: aaa.java, v 0.1 2016年9月6日 下午3:57:51 ryan Exp $
 */

@Configuration
@ComponentScan("com.changcai.bank")
@EnableAspectJAutoProxy
public class JavaConfig {

    public final static String profileDev  = "dev";

    public final static String profileProd = "prod";

    public final static String propName    = "bankConfig";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        String propFile = propName + ".properties";
        configurer.setLocation(new ClassPathResource(propFile));
        configurer.setIgnoreResourceNotFound(true);
        configurer.setFileEncoding("UTF-8");
        return configurer;
    }

    //    @Bean
    //    @Profile(profileDev)
    //    public static PropertySourcesPlaceholderConfigurer propertyConfigureDev() {
    //        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    //        String propFile = propName + "-" + profileDev + ".properties";
    //        configurer.setLocation(new ClassPathResource(propFile));
    //        configurer.setIgnoreResourceNotFound(true);
    //        configurer.setFileEncoding("UTF-8");
    //        return configurer;
    //    }

    //    @Bean
    //    @Conditional(EbaoTimeCostPrintAspectCondition.class)  
    //    public static PropertySourcesPlaceholderConfigurer propertyConfigureProd() {
    //        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    //        String propFile = propName + "-" + profileProd + ".properties";
    //        configurer.setLocation(new ClassPathResource(propFile));
    //        configurer.setIgnoreResourceNotFound(true);
    //        configurer.setFileEncoding("UTF-8");
    //        return configurer;
    //    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename("classpath:EbaoValidationMessages");
        bean.setDefaultEncoding("UTF-8");
        return bean;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

}
