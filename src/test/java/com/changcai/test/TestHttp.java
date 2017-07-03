package com.changcai.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class TestHttp {
	static Properties prop;

	public static Properties getProp() {
		if (prop == null) {
			try {
				prop = new Properties();
				String configFile = "bankConfig.properties";
				InputStream input = Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream(configFile);
				prop.load(input);

			} catch (IOException e) {

			}
		}
		return prop;
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 1; i++) {
			try {
				//Thread.sleep(1500);
				String res = null;
				PostMethod postMethod = new PostMethod(getProp().getProperty(
						"b2b.client.serverIPAddress"));
				String sendSRC = "A0010301018892                0000000205      99999012016111121312016111168045528      999999                                                                                                    000000000000000000000000000001310010               20161111213120999999                                          0000000839999916111168045528      889211015091833000&888800150432372&1.00&11017769852000&陆顺能&RMB&20161111&18117165268&";
				postMethod.setRequestEntity(new StringRequestEntity(sendSRC,
						"text/html", "GBK"));
				postMethod.setRequestHeader("Content-type",
						"text/xml; charset=GBK");

				HttpClient httpClient = new HttpClient();
				int resultint = httpClient.executeMethod(postMethod);

				res = new String(postMethod.getResponseBody(), "GBK");
				System.out.println(res);
				postMethod.releaseConnection();
			} catch (Exception ex) {
				// ex.printStackTrace();
			}
		}
	}

}
