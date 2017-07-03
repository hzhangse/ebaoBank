/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.constant;

/**
 * 证件类型说明
 * @author ryan
 * @version $Id: IdType.java, v 0.1 2016年9月14日 下午3:26:15 ryan Exp $
 */
public enum IdType {

   ID_TYPE_51( "51","法人代码证"),
   ID_TYPE_52("52","组织机构代码证"),
   ID_TYPE_53("53","政府机构/公共机构批文"),
   ID_TYPE_54("54","外交部外事办批文(使)") ,
   ID_TYPE_55( "55","外交部外事办批文(领)"),
   ID_TYPE_56( "56","外交部外事办批文(办)"),
   ID_TYPE_60( "60","香港商业登记证"),
   ID_TYPE_65( "65","事业单位登记证"),
   ID_TYPE_66( "66","社会团体登记证"), 
   ID_TYPE_67( "67","商业登记证(离岸)"), 
   ID_TYPE_68( "68","营业执照"), 
   ID_TYPE_69( "69","对公临时证件"),
   ID_TYPE_70( "70","其他证明文件(公司)"),
   ID_TYPE_71( "71","公司户重复有效证件"),
   ID_TYPE_73( "73","统一社会信用代码"),
   ID_TYPE_80( "80","金融机构"), 
   ID_TYPE_1( "1","身份证"),
   ID_TYPE_2( "2","军人军官证"),
   ID_TYPE_3( "3","港澳台居民通行证"),
   ID_TYPE_4( "4","中国护照"),
   ID_TYPE_8( "8","武警警官证"),
   ID_TYPE_9 ("9","临时身份证"),
   ID_TYPE_11( "11","户口簿"),
   ID_TYPE_12 ("12","中国居民其他证"),
   ID_TYPE_13 ("13","军人士兵证"),
   ID_TYPE_14 ("14","军人文职干部证"),
   ID_TYPE_15 ("15","军人其他证件"),
   ID_TYPE_16 ("16","武警士兵证"),
   ID_TYPE_17 ("17","武警文职干部证"),
   ID_TYPE_18 ("18","武警其他证件"),
   ID_TYPE_19 ("19","外国护照"),
   ID_TYPE_20( "20","外国公民其他证件");
  
   private String code;
   private String desc;
   
  private IdType(String code, String desc) {
       this.code = code;
       this.desc = desc;
   }
public String getCode() {
    return code;
}
public void setCode(String code) {
    this.code = code;
}
public String getDesc() {
    return desc;
}
public void setDesc(String desc) {
    this.desc = desc;
}
}
