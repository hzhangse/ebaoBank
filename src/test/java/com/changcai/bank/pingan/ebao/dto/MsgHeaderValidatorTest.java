package com.changcai.bank.pingan.ebao.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.B2BMessage;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.protocol.struct.BizzMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.NetMsgHeader;
import com.changcai.bank.pingan.ebao.protocol.struct.MessageObjInput;
import com.changcai.bank.pingan.ebao.protocol.struct.MessageObjOutput;
import com.changcai.bank.pingan.ebao.protocol.struct.req.MemberRegistInReqDto;
import com.changcai.bank.pingan.ebao.protocol.struct.req.SignInOutReqDto;

@ContextConfiguration(classes = { JavaConfig.class })
public class MsgHeaderValidatorTest extends AbstractTestNGSpringContextTests {

    @Autowired
    Validator validator;

    //  @Test
    public void SignInOutReqDtoTest() throws FileNotFoundException, IOException,
                                     ClassNotFoundException {
        SignInOutReqDto body = new SignInOutReqDto();
        body.setValidator(validator);
        body.setFuncFlag("1");
        body.setReserve("保留域");
        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        MessageObjOutput out = new MessageObjOutput(baops, Charset.forName("GBK"));
        out.writeObject(body);
        byte[] bytes = baops.toByteArray();
        out.close();

        bytes = new byte[1023];
        ByteArrayInputStream baips = new ByteArrayInputStream(bytes);
        SignInOutReqDto body1 = new SignInOutReqDto();
        MessageObjInput in = new MessageObjInput(baips, body1);
        body1 = (SignInOutReqDto) in.readObject();
        in.close();
        Assert.assertTrue(body.getReserve().equalsIgnoreCase(body1.getReserve()));
    }

    // @Test
    public void bizzMsgStringTest() throws IOException, ClassNotFoundException {

        BizzMsgHeader header = new BizzMsgHeader();
        header.setValidator(validator);
       
        //header.setLength(StringUtils.repeat(StringUtils.SPACE, 8));
        header.toString();

        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        MessageObjOutput out = new MessageObjOutput(baops, Charset.forName("GBK"));
        out.writeObject(header);
        byte[] bytes = baops.toByteArray();
        out.close();

        Assert.assertTrue(header.toString().equalsIgnoreCase(new String(bytes, "GBK")));
        ByteArrayInputStream baips = new ByteArrayInputStream(bytes);
        BizzMsgHeader body1 = new BizzMsgHeader();
        body1.setValidator(validator);
        MessageObjInput in = new MessageObjInput(baips, body1);
        body1 = (BizzMsgHeader) in.readObject();

        in.close();
      //  Assert.assertTrue(header.getThirdLogNo().equalsIgnoreCase(body1.getThirdLogNo()));
    }

    //  @Test
    public void CommunMsgStringTest() throws IOException, ClassNotFoundException {
        NetMsgHeader header = new NetMsgHeader();
//        header.setCompanyCode(StringUtils.repeat(StringUtils.SPACE, 20));
//        header.setRecMsgLength(StringUtils.repeat(StringUtils.SPACE, 10));
//        header.setThirdLogNo(StringUtils.repeat(StringUtils.SPACE, 20));

        header.toString();

        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        MessageObjOutput out = new MessageObjOutput(baops, Charset.forName("GBK"));
        out.writeObject(header);
        byte[] bytes = baops.toByteArray();
        out.close();

        Assert.assertTrue(header.toString().equalsIgnoreCase(new String(bytes, "GBK")));
        ByteArrayInputStream baips = new ByteArrayInputStream(bytes);
        NetMsgHeader body1 = new NetMsgHeader();
        body1.setValidator(validator);
        MessageObjInput in = new MessageObjInput(baips, body1);
        body1 = (NetMsgHeader) in.readObject();

        in.close();
        Assert.assertTrue(header.toString().equalsIgnoreCase(body1.toString()));
    }

    @Test
    public void B2BMessageTest() throws IOException, ClassNotFoundException {
        B2BMessage msg = new B2BMessage();

        NetMsgHeader netHeader = new NetMsgHeader();
//        netHeader.setCompanyCode(StringUtils.repeat(StringUtils.SPACE, 20));
//        netHeader.setRecMsgLength(StringUtils.repeat(StringUtils.SPACE, 10));
//        netHeader.setThirdLogNo(StringUtils.repeat(StringUtils.SPACE, 20));

        BizzMsgHeader bizzHeader = new BizzMsgHeader();
    //    bizzHeader.setTranFunc("1303");
      //  bizzHeader.setThirdLogNo(StringUtils.repeat(StringUtils.SPACE, 20));
     //   bizzHeader.setCompanyCode(StringUtils.repeat(StringUtils.SPACE, 4));
    //    bizzHeader.setLength(StringUtils.repeat(StringUtils.SPACE, 8));

        SignInOutReqDto body = new SignInOutReqDto();
        body.setFuncFlag("01");
        body.setReserve("保留域");

        msg.setBizzHeader(bizzHeader);
        msg.setBody(body);
        msg.setNetHeader(netHeader);
        msg.setValidator(validator);

        ByteArrayOutputStream baops = new ByteArrayOutputStream();
        MessageObjOutput out = new MessageObjOutput(baops, Charset.forName("GBK"));
        out.writeObject(msg);
        byte[] bytes = baops.toByteArray();
        out.close();

        Assert.assertTrue(msg.toString().equalsIgnoreCase(new String(bytes, "GBK")));

        ByteArrayInputStream baips = new ByteArrayInputStream(bytes);
        B2BMessage msg1 = new B2BMessage();
        //    SignInOutReqDto body1 = new SignInOutReqDto();
        //  msg1.setValidator(validator);
        //  msg1.setBody(body1);
        MessageObjInput in = new MessageObjInput(baips, msg1);
        msg1 = (B2BMessage) in.readObject();
//        if (msg1.getBizzHeader().getTranFunc()
//            .equalsIgnoreCase(MessageType.MEMBER_REGIST_IN.value()))
        {
            BaseMessageBody body1 = new MemberRegistInReqDto();
            msg1.setBody(body1);
            body1.readExternal(in);
        }

        in.close();
        Assert.assertTrue(msg.toString().equalsIgnoreCase(msg1.toString()));

    }

    public static void main(String[] args) {
        MsgHeaderValidatorTest test = new MsgHeaderValidatorTest();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            JavaConfig.class);
        test.validator = context.getBean(Validator.class);
        try {
            test.B2BMessageTest();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        context.close();
    }
}
