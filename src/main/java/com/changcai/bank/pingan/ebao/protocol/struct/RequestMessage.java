package com.changcai.bank.pingan.ebao.protocol.struct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 请求消息
 * @author gaoxiaofei
 * @version $Id: RequestMessage.java, v 0.1 2016年10月8日 下午2:56:00 ryan Exp $
 */
@Component
@Scope("prototype")
public class RequestMessage extends B2BMessage implements IRequest{
}
