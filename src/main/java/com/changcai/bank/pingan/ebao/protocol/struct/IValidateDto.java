/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import javax.validation.Validator;

/**
 * Dto 验证接口
 * 
 * @author ryan
 * @version $Id: IValidateDto.java, v 0.1 2016年9月8日 上午11:06:10 ryan Exp $
 */
public interface IValidateDto  {
    void setValidator(Validator validator);

    Validator getValidator();
    
    /**
     *   use to validate dto
     */
    void validateDto();
}
