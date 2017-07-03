/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.dto.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.changcai.bank.pingan.ebao.annotation.CpFlagStatus;


/**
 * 
 * 
 * @author ryan
 * @version $Id: CpFlagStatusValidator.java, v 0.1 2016年9月8日 下午1:48:00 ryan Exp $
 */
public  class CpFlagStatusValidator implements ConstraintValidator<CpFlagStatus, String> {
	
	final String[] ALL_STATUS = {"1", "2"}; //1：企业 2：个人

	
	protected String[] getStatus() {
		return ALL_STATUS;
	}
	public void initialize(CpFlagStatus status) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (Arrays.asList(getStatus()).contains(value))
			return true;
		return false;
	}
}