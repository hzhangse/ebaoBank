package com.changcai.bank.pingan.ebao.tcp.client;

import java.io.UnsupportedEncodingException;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import com.changcai.bank.pingan.ebao.EbaoTcpClientInvokerTest;
import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.service.EbaoTcpClientInvoker;

@ContextConfiguration(classes = { JavaConfig.class })
public class ConvertReqMsgTest extends EbaoTcpClientInvokerTest{

	@Override
	protected BaseMessageBody createReqBody()
			throws UnsupportedEncodingException {
		return null;
	}
	 @Test
	  public void testAnalyseReq() throws Throwable {
	      //  String request ="A0010301018892                0000000179000000PA001012016092817203500320160928000458727999999                                                                                                    000001RSA-SHA1    00000000000132401                20160928172035999999                                          000000057PA00100320160928000458727889211014971274008&00320160928000458727&20160928&20160928&1&&";
	           String reqMsgStr ="A0010301018892                0000000159000000PA001012016122611435520161226114355712381999999                                                                                                    000001RSA-SHA1    00000000000132501                20161226114355999999                                          000000037PA00120161226114355712381889211015091833000&&20161226&20161226&2&&"; 
	           B2BMessage result = ebaoTcpClientInvoker.convertReqMsg(reqMsgStr);
	        if (result != null)
	            LOGGER.error(" result:" + result.toJSONString());
	    }
}
