/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ryan
 * @version $Id: ValidateDto.java, v 0.1 2016年9月7日 下午1:48:24 ryan Exp $
 */

public class ValidateDto implements IValidateDto {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ValidateDto.class);
    
    @JSONField(serialize = false)
    transient private Validator validator;

    @Override
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    @Override
    public void validateDto() {
        if (this.getValidator() != null) {
            Set<ConstraintViolation<ValidateDto>> violations = this.getValidator().validate(this);
            StringBuilder violationMsg = new StringBuilder();
            for (ConstraintViolation<? extends ValidateDto> violation : violations) {
                violationMsg.append(violation.getPropertyPath() + ":" + violation.getMessage()
                                    + StringUtils.LF);
            }
           if (!violations.isEmpty()){
               LOGGER.error(violationMsg.toString());
           }
            Assert.isTrue(violations.isEmpty(), violationMsg.toString());
        }
    }

}
