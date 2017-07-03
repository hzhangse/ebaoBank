/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.changcai.bank.pingan.ebao.EbaoClientInvokerTest;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.req.QueryMemberTransReqDto;

/**
 * 
 * @author ryan
 * @version $Id: EbaoServiceTest.java, v 0.1 2016年9月22日 上午10:24:21 ryan Exp $
 */
public class EbaoServiceTest extends EbaoClientInvokerTest {
	@Autowired
	EbaoService ebaoService;
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	QueryMemberTransReqDto body;

	@BeforeClass
	public void setUp() throws Exception {
		super.setUp();
		funcId = MessageType.QUERY_MONEY_RECORDSET.value();
	}

	@Override
	protected BaseMessageBody createReqBody()
			throws UnsupportedEncodingException {
		body.setBeginDate("20160601");
		body.setEndDate("20161201");
		body.setPageNum("1");

		body.setReserve("保留字reserve");
		return body;
	}

	@Test(invocationCount = 1, threadPoolSize = 30)
	public void testRequest() throws Exception {
		BaseMessageBody request = this.createReqBody();
		B2BMessage result = ebaoService.invokeEbaoService(request, funcId);
		if (result != null)
			LOGGER.error(" result:" + result.toJSONString());
	}

	@Test(invocationCount = 10, threadPoolSize = 30)
	public void testRequestByPage() throws Exception {
		// BaseMessageBody request = this.createReqBody();

		Class<? extends BaseMessageBody> reqClass = MessageType.getMessageType(
				funcId).getReqClass();
		Class<? extends BaseMessageBody> rspClass = MessageType.getMessageType(
				funcId).getRspClass();
		Method methodTotal = rspClass.getMethod("getTotalCount");
		RequestMessageBody request = (RequestMessageBody) this.applicationContext
				.getBean(reqClass);
		Method method1 = reqClass.getMethod("setBeginDate", String.class);
		Method method2 = reqClass.getMethod("setEndDate", String.class);
		Method method3 = reqClass.getMethod("setPageNum", String.class);
		method1.invoke(request, "20160601");
		method2.invoke(request, "20161201");
		method3.invoke(request, "1");
		// Method[] methodArr = rspClass.getDeclaredMethods();
		// for (Method method:methodArr){
		// System.out.println(method.getName());
		// }

		B2BMessage result = ebaoService.invokeEbaoService(request, funcId);
		BaseMessageBody rsp = result.getBody();

		// int totalRecord = Integer.valueOf((String)methodTotal.invoke(rsp));
		// int pageSize =20;
		// int totalPageNum = (totalRecord + pageSize - 1) / pageSize;
		// if (result != null)
		// LOGGER.error(" result:" + result.toJSONString());
		// for (int i=2;i<=totalPageNum;i++){
		// method3.invoke(request,String.valueOf(i));
		// result = ebaoService.invokeEbaoService(request, funcId);
		// if (result != null)
		// LOGGER.info(" page :"+i+" result:" + result.toJSONString());
		// }
		//

	}

	@Test(invocationCount = 1, threadPoolSize = 30)
	public void testRequestStr() throws Exception {
		// String request
		// ="A0010301018892                0000000179000000PA001012016092817203500320160928000458727999999                                                                                                    000001RSA-SHA1    00000000000132401                20160928172035999999                                          000000057PA00100320160928000458727889211014971274008&00320160928000458727&20160928&20160928&1&&";
	//	String request = "A0010301018892                0000000159000000PA001012016122914045320161229140453933736999999                                                                                                    000001RSA-SHA1    00000000000132501                20161229140453999999                                          000000037PA00120161229140453933736889211015091833000&&20161229&20161229&2&&";
		String request ="A0010301018892                0000000179000000PA001012017011111381220170111113812536481999999                                                                                                    000001RSA-SHA1    00000000000132501                20170111113812999999                                          000000057PA00120170111113812536481889211015091833000&00320170111000261174&20170111&20170111&1&&";
		B2BMessage result = ebaoService.invokeEbaoService(request);
		if (result != null) {
			LOGGER.error(" result:" + result.toJSONString());
		}
	}

	@Test(invocationCount = 2, threadPoolSize = 30)
	public void testRequestJson() throws Exception {
		String request = "{\"beginDate\":\"20161010\",\"endDate\":\"20161010\",\"origThirdLogNo\":\"00320161010000258987\",\"pageNum\":\"1\",\"supAcctId\":\"11015091833000\"}";
		Class reqClass = MessageType.getMessageType("1324").getReqClass();
		BaseMessageBody reqObj = (BaseMessageBody) JSON.parseObject(request,
				reqClass);

		B2BMessage result = ebaoService.invokeEbaoService(reqObj, "1324");
		if (result != null)
			LOGGER.error(" result:" + result.toJSONString());

	}
}