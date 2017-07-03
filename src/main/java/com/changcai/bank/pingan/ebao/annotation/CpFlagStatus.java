/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.changcai.bank.pingan.ebao.dto.validator.CpFlagStatusValidator;

/**
 * 
 * @author ryan
 *
 */
@Constraint(validatedBy = { CpFlagStatusValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CpFlagStatus {
	String message() default "非法状态值,应该是 '1', '2', 其中之一";

	Class<?>[] groups() default {};
	
	 Class<? extends Payload>[] payload() default {}; 
}