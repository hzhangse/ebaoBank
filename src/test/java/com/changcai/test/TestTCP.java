package com.changcai.test;
import java.lang.reflect.Method;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import com.changcai.bank.pingan.ebao.service.EbaoService;

public class TestTCP {

	public static void main(String[] args) throws Exception {
		String funcId = "1325";
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				JavaConfig.class);
		EbaoService ebaoService = applicationContext.getBean(EbaoService.class);
		for (int k = 0; k < 10000; k++) {
			Thread.sleep(1000);
			Class<? extends BaseMessageBody> reqClass = MessageType
					.getMessageType(funcId).getReqClass();
			Class<? extends BaseMessageBody> rspClass = MessageType
					.getMessageType(funcId).getRspClass();
			Method methodTotal = rspClass.getMethod("getTotalCount");
			RequestMessageBody request = (RequestMessageBody) applicationContext
					.getBean(reqClass);
			Method method1 = reqClass.getMethod("setBeginDate", String.class);
			Method method2 = reqClass.getMethod("setEndDate", String.class);
			Method method3 = reqClass.getMethod("setPageNum", String.class);
			method1.invoke(request, "20160601");
			method2.invoke(request, "20161201");
			method3.invoke(request, "1");

			B2BMessage result = ebaoService.invokeEbaoService(request, funcId);
			BaseMessageBody rsp = result.getBody();

			int totalRecord = Integer.valueOf((String) methodTotal.invoke(rsp));
			int pageSize = 20;
			int totalPageNum = (totalRecord + pageSize - 1) / pageSize;
			if (result != null)
				System.out.println(" result:" + result.toJSONString());
			for (int i = 2; i <= totalPageNum; i++) {
				method3.invoke(request, String.valueOf(i));
				result = ebaoService.invokeEbaoService(request, funcId);
				if (result != null)
					System.out.println(" page :" + i + " result:"
							+ result.toJSONString());
			}
		}
		applicationContext.close();
	}

}
