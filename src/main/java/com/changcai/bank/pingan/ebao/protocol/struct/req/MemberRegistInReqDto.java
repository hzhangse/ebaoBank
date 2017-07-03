/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.req;

import com.changcai.bank.pingan.ebao.protocol.struct.RequestMessageBody;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;

/**
 * 会员签解约维护【1303】
 * 银行经过验证后，发起签约。
 * @author ryan
 * @version $Id: MemberRegistInReqDto.java, v 0.1 2016年9月8日 下午2:56:00 ryan Exp $
 */
@Component
@Scope("prototype")
public class MemberRegistInReqDto extends RequestMessageBody {

    /**  */
    private static final long serialVersionUID = -7875614522736283420L;

    //功能标志   1:指定，2：修改，3：删除
    @NotNull
    @Size(max = 1)
    @Order(1)
    private byte[]            funcFlag;

    // 资金汇总账号
    @NotNull
    @Length(max = 32)
    @Order(2)
    @Value("${SupAcctId}")
    private String            supAcctId;

    //会员子账号
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[]            custAcctId;

    //会员名称  
    @NotNull
    @Size(max = 120)
    @Order(4)
    private byte[]            custName;

    //会员代码  
    @NotNull
    @Size(max = 32)
    @Order(5)
    private byte[]            thirdCustId;

    //会员证件类型  
    @NotNull
    @Size(max = 2)
    @Order(6)
    private byte[]            idType;

    //会员证件号码 
    @NotNull
    @Size(max = 20)
    @Order(7)
    private byte[]            idCode;

    //出/入金账号  
    @NotNull
    @Size(max = 32)
    @Order(8)
    private byte[]            relatedAcctId;

    //账号性质  --1：出金账号   2：入金账号  3：出金账号&入金账号（默认）
    @NotNull
    @Size(max = 1)
    @Order(9)
    private byte[]            acctFlag;

    //转账方式  ----1：本行（默认）   2：同城      3：异地汇款
    @NotNull
    @Size(max = 1)
    @Order(10)
    private byte[]            tranType;

    //账号名称  
    @NotNull
    @Size(max = 120)
    @Order(11)
    private byte[]            acctName;

    //联行号  --本行为分行号
    @Size(max = 12)
    @Order(12)
    private byte[]            bankCode;

    //开户行名称   
    @Size(max = 120)
    @Order(13)
    private byte[]            bankName;

    //原出入金账号----若FuncFlag为1或者3时为空
    @Size(max = 32)
    @Order(14)
    private byte[]            oldRelatedAcctId;

    //保留域   
    @Size(max = 120)
    @Order(15)
    private byte[]            reserve;

  

    public String getSupAcctId() {
        return this.supAcctId;
    }

    public void setSupAcctId(String supAcctId) {
        this.supAcctId = supAcctId;
    }

 


//    /**
//     * Setter method for property <tt>funcFlag</tt>.
//     * 
//     * @param funcFlag value to be assigned to property funcFlag
//     * @throws UnsupportedEncodingException 
//     */
//    public void setFuncFlag(String funcFlag) throws UnsupportedEncodingException {
//        if (funcFlag != null)
//            this.funcFlag = funcFlag.getBytes(this.encoding);
//    }
//    /**
//     * Setter method for property <tt>custName</tt>.
//     * 
//     * @param custName value to be assigned to property custName
//     * @throws UnsupportedEncodingException 
//     */
//    public void setCustName(String custName) throws UnsupportedEncodingException {
//        if (custName != null) {
//            this.custName = custName.getBytes(this.encoding);
//        }
//    }

    /**
     * Getter method for property <tt>acctName</tt>.
     * 
     * @return property value of acctName
     * @throws UnsupportedEncodingException 
     */
    public String getAcctName() throws UnsupportedEncodingException {
        if (acctName != null)
            return new String(acctName, this.encoding);
        else
            return null;
    }


    /**
     * Getter method for property <tt>bankName</tt>.
     * 
     * @return property value of bankName
     * @throws UnsupportedEncodingException 
     */
    public String getBankName() throws UnsupportedEncodingException {
        if (bankName != null)
            return new String(bankName, this.encoding);
        else
            return null;
    }

   

   
    
   public String getCustAcctId() throws UnsupportedEncodingException {
        if (custAcctId != null) {
            return new String(custAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setCustAcctId(String  custAcctId) throws UnsupportedEncodingException {
        if (custAcctId != null) {
            this.custAcctId = custAcctId.getBytes(this.encoding);
        }
    }

   public String getThirdCustId() throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            return new String(thirdCustId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setThirdCustId(String  thirdCustId) throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            this.thirdCustId = thirdCustId.getBytes(this.encoding);
        }
    }

   public String getIdType() throws UnsupportedEncodingException {
        if (idType != null) {
            return new String(idType, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setIdType(String  idType) throws UnsupportedEncodingException {
        if (idType != null) {
            this.idType = idType.getBytes(this.encoding);
        }
    }

   public String getIdCode() throws UnsupportedEncodingException {
        if (idCode != null) {
            return new String(idCode, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setIdCode(String  idCode) throws UnsupportedEncodingException {
        if (idCode != null) {
            this.idCode = idCode.getBytes(this.encoding);
        }
    }

   public String getRelatedAcctId() throws UnsupportedEncodingException {
        if (relatedAcctId != null) {
            return new String(relatedAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setRelatedAcctId(String  relatedAcctId) throws UnsupportedEncodingException {
        if (relatedAcctId != null) {
            this.relatedAcctId = relatedAcctId.getBytes(this.encoding);
        }
    }

   public String getAcctFlag() throws UnsupportedEncodingException {
        if (acctFlag != null) {
            return new String(acctFlag, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setAcctFlag(String  acctFlag) throws UnsupportedEncodingException {
        if (acctFlag != null) {
            this.acctFlag = acctFlag.getBytes(this.encoding);
        }
    }

   public String getTranType() throws UnsupportedEncodingException {
        if (tranType != null) {
            return new String(tranType, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setTranType(String  tranType) throws UnsupportedEncodingException {
        if (tranType != null) {
            this.tranType = tranType.getBytes(this.encoding);
        }
    }

   public String getBankCode() throws UnsupportedEncodingException {
        if (bankCode != null) {
            return new String(bankCode, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setBankCode(String  bankCode) throws UnsupportedEncodingException {
        if (bankCode != null) {
            this.bankCode = bankCode.getBytes(this.encoding);
        }
    }

   public String getOldRelatedAcctId() throws UnsupportedEncodingException {
        if (oldRelatedAcctId != null) {
            return new String(oldRelatedAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setOldRelatedAcctId(String  oldRelatedAcctId) throws UnsupportedEncodingException {
        if (oldRelatedAcctId != null) {
            this.oldRelatedAcctId = oldRelatedAcctId.getBytes(this.encoding);
        }
    }

    public void setFuncFlag(String  funcFlag) throws UnsupportedEncodingException {
        if (funcFlag != null) {
            this.funcFlag = funcFlag.getBytes(this.encoding);
        }
    }
    public String getFuncFlag() throws UnsupportedEncodingException {
        if (funcFlag != null)
            return new String(funcFlag, this.encoding);
        else
            return null;
    }

    public String getCustName() throws UnsupportedEncodingException {
        if (custName != null)
            return new String(custName, this.encoding);
        else
            return null;
    }
    
    public void setCustName(String  custName) throws UnsupportedEncodingException {
        if (custName != null) {
            this.custName = custName.getBytes(this.encoding);
        }
    }

    public void setAcctName(String  acctName) throws UnsupportedEncodingException {
        if (acctName != null) {
            this.acctName = acctName.getBytes(this.encoding);
        }
    }

    public void setBankName(String  bankName) throws UnsupportedEncodingException {
        if (bankName != null) {
            this.bankName = bankName.getBytes(this.encoding);
        }
    }
    
    public String getReserve() throws UnsupportedEncodingException {
        if (reserve != null)
            return new String(reserve, this.encoding);
        else
            return null;
    }

    public void setReserve(String  reserve) throws UnsupportedEncodingException {
        if (reserve != null) {
            this.reserve = reserve.getBytes(this.encoding);
        }
    }
}
