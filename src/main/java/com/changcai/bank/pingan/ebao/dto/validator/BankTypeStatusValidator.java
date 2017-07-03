/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.dto.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.changcai.bank.pingan.ebao.annotation.BankTypeStatus;


/**
 * 
 * @author ryan
 * @version $Id: aaa.java, v 0.1 2016年9月6日 下午3:57:51 ryan Exp $
 */
public  class BankTypeStatusValidator implements ConstraintValidator<BankTypeStatus, String> {
	
	final String[] ALL_STATUS = {"1", "2"}; //1：本行 2：他行

	
	protected String[] getStatus() {
		return ALL_STATUS;
	}
	public void initialize(BankTypeStatus status) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (Arrays.asList(getStatus()).contains(value))
			return true;
		return false;
	}
}