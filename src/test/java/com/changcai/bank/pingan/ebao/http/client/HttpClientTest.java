package com.changcai.bank.pingan.ebao.http.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientTest {
    protected  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Test
	public void TestPost() throws Exception {
		String res = null;

		PostMethod postMethod = new PostMethod("http://127.0.0.1:8080/ebao/callBack.do");
		String sendSRC = "A0010301018545                0000000140000000PA001012016090415262120160904152621231332999999                                                                                                    000000            00000000000133001                20160904152621999999                                          000000018PA0012016090415262123133285451&20151125&保留域&";
		postMethod.setRequestEntity(new StringRequestEntity(sendSRC,
				"text/html", "GBK"));
		postMethod.setRequestHeader("Content-type", "text/xml; charset=GBK");

		HttpClient httpClient = new HttpClient();
		int resultint = httpClient.executeMethod(postMethod);

		res = new String(postMethod.getResponseBody(),"GBK");
		LOGGER.info("http response:" + res);
		postMethod.releaseConnection();

	}
}
