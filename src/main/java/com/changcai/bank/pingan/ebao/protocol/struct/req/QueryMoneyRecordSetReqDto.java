/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.req;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 查询时间段会员出入金流水信息【1325】
 * 查询时间段出入金流水，可以提供平台进行每日对账使用。
 *若交易网流水号为空，则返回全部，此时返回的都是成功的记录。
 *若交易网流水号不为空，则查询单笔交易，此时返回该笔交易的任何状态。在进行单笔查询时，若返回ERR020，则说明银行无此记录。
 * @author ryan
 * @version $Id: QueryMoneyRecordSetReqDto.java, v 0.1 2016年9月15日 下午6:00:59 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryMoneyRecordSetReqDto extends BaseQueryReqDto  {

  
}
