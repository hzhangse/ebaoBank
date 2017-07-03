package com.changcai.bank.pingan.ebao.http.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientTest1 {
    protected  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Test
	public void Test1303Post() throws Exception {
		
		for (int i=0;i<10000;i++){
			try {
			Thread.sleep(100);
		String res = null;
		PostMethod postMethod = new PostMethod("http://106.14.38.24:30005");
		String sendSRC = 
		        "A0010301018892                0000000203000000PA001012016092417293220160924172932697886999999                                                                                                    000001RSA-SHA1    00000000000131001                20160924172932999999                                          000000081PA00120160924172932697886889211014971274008&888800000189550&50.0&11014970134006&张生&RMB&20160920&13813813813&";
		postMethod.setRequestEntity(new StringRequestEntity(sendSRC,
				"text/html", "GBK"));
		postMethod.setRequestHeader("Content-type", "text/xml; charset=GBK");

		HttpClient httpClient = new HttpClient();
		int resultint = httpClient.executeMethod(postMethod);

		res = new String(postMethod.getResponseBody(),"GBK");
		LOGGER.info("http response:" + res);
		postMethod.releaseConnection();
			}catch (Exception ex){
			//ex.printStackTrace();
			}
		}
	}
}
