/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import com.changcai.bank.pingan.ebao.config.JavaConfig;
import com.changcai.bank.pingan.ebao.constant.IdType;
import com.changcai.bank.pingan.ebao.protocol.struct.AccountInfo;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;

/**
 * 
 * @author ryan
 * @version $Id: EboBaseTes.java, v 0.1 2016年9月19日 上午10:58:59 ryan Exp $
 */
//@ActiveProfiles(resolver = SpringActiveProfileResolver.class)
@ContextConfiguration(classes = { JavaConfig.class })
//@ActiveProfiles(JavaConfig.profileDev)
public abstract class EbaoClientInvokerTest extends AbstractTestNGSpringContextTests {
    protected final Logger             LOGGER = LoggerFactory.getLogger(this.getClass());

    protected Map<String, AccountInfo> accMap = new HashMap<String, AccountInfo>();

    protected String                   funcId;

  

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    protected void createTestAccInfo() {
        AccountInfo acc1 = new AccountInfo();
        acc1.setAccName("张生");
        acc1.setRelatedAcctId("11014970134006");
        acc1.setThirdCustId("13813813813");
        acc1.setAccNo("888800000189550");
        acc1.setAccType("易宝网银");
        acc1.setIdNo("310654199301098976");
        acc1.setIdType(IdType.ID_TYPE_1.getCode());

        AccountInfo acc2 = new AccountInfo();
        acc2.setAccName("铁蛋");
        acc2.setRelatedAcctId("11014970135005");
        acc2.setAccNo("888800000189560");
        acc2.setAccType("易宝网银");
        acc2.setIdNo("628654199101098971");
        acc2.setIdType(IdType.ID_TYPE_1.getCode());
        acc2.setThirdCustId("13813813811");

        AccountInfo acc3 = new AccountInfo();
        acc3.setAccName("测试二");
        acc3.setRelatedAcctId("11014890514005");
        acc3.setAccNo("888800000189541");
        acc3.setAccType("易宝网银");
        acc3.setIdNo("789222662");
        acc3.setIdType(IdType.ID_TYPE_52.getCode());
        acc3.setThirdCustId("18516339472");
        //        

        AccountInfo acc4 = new AccountInfo();
        acc4.setAccName("测试一");
        acc4.setRelatedAcctId("11014890513006");
        acc4.setAccNo("888800000189531");
        acc4.setAccType("易宝网银");
        acc4.setIdNo("757601035");
        acc4.setIdType(IdType.ID_TYPE_52.getCode());
        acc4.setThirdCustId("18516339471");
        
        AccountInfo acc5 = new AccountInfo();
        acc5.setAccName("联调测试一");
        acc5.setRelatedAcctId("11014888541007");
        acc5.setAccNo("888800000190462");
        acc5.setAccType("易宝网银");
        acc5.setIdNo("100018347");
        acc5.setIdType(IdType.ID_TYPE_52.getCode());
        acc5.setThirdCustId("13703712002");
        
      

//        AccountInfo acc0 = new AccountInfo();
//        acc0.setAccName("买豆粕监管账户");
//        acc0.setAccNo("11014971274008");
//        acc0.setAccType("企业网银");
//        acc0.setIdNo("67485617-1");
//        acc0.setIdType(IdType.ID_TYPE_52.getCode());
        
        AccountInfo acc6 = new AccountInfo();
        acc6.setAccName("买豆粕监管账户");
        acc6.setAccNo("11015091833000");
        acc6.setAccType("企业网银");
        acc6.setIdNo("MA1K33WT-2");
        acc6.setIdType(IdType.ID_TYPE_52.getCode());
        acc6.setRelatedAcctId("11015092061008");
       
//        {"acctFlag":"3","acctName":"陆顺能",
//        	"bankCode":"","bankName":"平安银行",
//        	"custAcctId":"888800150432372",
//        	"custName":"陆顺能","funcFlag":"1",
//        	"idCode":"310103199011285039","idType":"1"
//        		,"oldRelatedAcctId":"","relatedAcctId":"11017769852000"
//        			,"reserve":"","supAcctId":"11015091833000",""
//        					+ "thirdCustId":"18117165268","tranType":"1"}

        AccountInfo acc7 = new AccountInfo();
        acc7.setAccName("陆顺能");
        acc7.setRelatedAcctId("11017769852000");
        acc7.setAccNo("888800150432372");
        acc7.setAccType("易宝网银");
        acc7.setIdNo("310103199011285039");
        acc7.setIdType(IdType.ID_TYPE_1.getCode());
        acc7.setThirdCustId("18117165268");
     //   {"acctFlag":"3","acctName":"陈春辉","bankCode":"","bankName":"平安银行","custAcctId":"888800150462181","custName":"陈春辉","funcFlag":"1","idCode":"321281199102136351","idType":"1","oldRelatedAcctId":"","relatedAcctId":"11017761808005","reserve":"","supAcctId":"11015091833000","thirdCustId":"18516603218","tranType":"1"}
    //    {"acctFlag":"3","acctName":"陈春晖","bankCode":"","bankName":"平安银行","custAcctId":"888800000189560","custName":"陈春晖","funcFlag":"1","idCode":"628654199101098971","idType":"1","oldRelatedAcctId":"","relatedAcctId":"11014970135005","reserve":"保留字reserve","supAcctId":"11014971274008","thirdCustId":"18516603218","tranType":"1"}
        AccountInfo acc8 = new AccountInfo();
        acc8.setAccName("陈春辉");
        acc8.setRelatedAcctId("11017761808005");
        acc8.setAccNo("888800150462181");
        acc8.setAccType("易宝网银");
        acc8.setIdNo("321281199102136351");
        acc8.setIdType(IdType.ID_TYPE_1.getCode());
        acc8.setThirdCustId("18516603218");
       // this.accMap.put(acc0.getAccName(), acc0);
        this.accMap.put(acc1.getAccName(), acc1);
        this.accMap.put(acc2.getAccName(), acc2);
        this.accMap.put(acc3.getAccName(), acc3);
        this.accMap.put(acc4.getAccName(), acc4);
      //  this.accMap.put(acc5.getAccName(), acc5);
        this.accMap.put(acc6.getAccName(), acc6);
        this.accMap.put(acc7.getAccName(), acc7);
        this.accMap.put(acc8.getAccName(), acc8);
    }

    @BeforeClass
    public void setUp() throws Exception {
        createTestAccInfo();
    }

    protected abstract BaseMessageBody createReqBody() throws UnsupportedEncodingException ;


}
