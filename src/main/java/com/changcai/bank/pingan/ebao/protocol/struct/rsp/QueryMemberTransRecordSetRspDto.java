package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

@Component
@Scope("prototype")
public class QueryMemberTransRecordSetRspDto extends ResponseMessageBody {

    //交易网流水号  ThirdLogNo  C(20)   必输  
    @NotNull
    @Size(max = 20)
    @Order(1)
    private byte[] thirdLogNo;

    // 银行前置流水号 FrontLogNo  C(14)   必输  
    @NotNull
    @Size(max = 14)
    @Order(2)
    private byte[] frontLogNo;

    //记账标志    TranFlag    C(1)    必输 
    @NotNull
    @Size(max = 2)
    @Order(3)
    private byte[] tranFlag;

    /**
     * 交易状态    TranStatus  C(1)    必输 
     * 0：成功  
     * 1 : 失败（交易网流水号不为空时才返回）
     * 2：异常（交易网流水号不为空时才返回，异常是中间状态，需等待一段时间（5-10分钟）后重新查询结果）
     */
    @NotNull
    @Size(max = 1)
    @Order(4)
    private byte[] tranStatus;

    //交易金额    TranAmount  9(15)   必输
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(5)
    private Double tranAmount;

    // 转出子账户   OutCustAcctId   C(32)   必输  即付款方
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] outCustAcctId;

    //转出会员代码  OutThirdCustId  C(32)   必输 
    @NotNull
    @Size(max = 32)
    @Order(7)
    private byte[] outThirdCustId;

    //转入子账户   InCustAcctId    C(32)   可选  即收款方
    @Size(max = 32)
    @Order(8)
    private byte[] inCustAcctId;

    //转入会员代码  InThirdCustId   C(32)   可选  
    @Size(max = 32)
    @Order(9)
    private byte[] inThirdCustId;

    //交易日期    TranDate    C(8)    必输  
    @NotNull
    @Size(max = 8)
    @Order(10)
    private byte[] tranDate;

    public String getThirdLogNo() throws UnsupportedEncodingException {
        if (thirdLogNo != null)
            return new String(thirdLogNo, this.encoding);
        else
            return null;
    }

    public void setThirdLogNo(String thirdLogNo) throws UnsupportedEncodingException {
        if (thirdLogNo != null)
            this.thirdLogNo = thirdLogNo.getBytes(this.encoding);
    }

    public String getFrontLogNo() throws UnsupportedEncodingException {
        if (frontLogNo != null)
            return new String(frontLogNo, this.encoding);
        else
            return null;
    }

    public void setFrontLogNo(String frontLogNo) throws UnsupportedEncodingException {
        if (frontLogNo != null)
            this.frontLogNo = frontLogNo.getBytes(this.encoding);
    }

    public String getTranFlag() throws UnsupportedEncodingException {
        if (tranFlag != null)
            return new String(tranFlag, this.encoding);
        else
            return null;
    }

    public void setTranFlag(String tranFlag) throws UnsupportedEncodingException {
        if (tranFlag != null)
            this.tranFlag = tranFlag.getBytes(this.encoding);
    }

    public String getTranStatus() throws UnsupportedEncodingException {
        if (tranStatus != null)
            return new String(tranStatus, this.encoding);
        else
            return null;
    }

    public void setTranStatus(String tranStatus) throws UnsupportedEncodingException {
        if (tranStatus != null)
            this.tranStatus = tranStatus.getBytes(this.encoding);
    }

    public String getOutCustAcctId() throws UnsupportedEncodingException {
        if (outCustAcctId != null)
            return new String(outCustAcctId, this.encoding);
        else
            return null;
    }

    public void setOutCustAcctId(String outCustAcctId) throws UnsupportedEncodingException {
        if (outCustAcctId != null)
            this.outCustAcctId = outCustAcctId.getBytes(this.encoding);
    }

    public String getOutThirdCustId() throws UnsupportedEncodingException {
        if (outThirdCustId != null)
            return new String(outThirdCustId, this.encoding);
        else
            return null;
    }

    public void setOutThirdCustId(String outThirdCustId) throws UnsupportedEncodingException {
        if (outThirdCustId != null)
            this.outThirdCustId = outThirdCustId.getBytes(this.encoding);
    }

    public String getInCustAcctId() throws UnsupportedEncodingException {
        if (inCustAcctId != null)
            return new String(inCustAcctId, this.encoding);
        else
            return null;
    }

    public void setInCustAcctId(String inCustAcctId) throws UnsupportedEncodingException {
        if (inCustAcctId != null)
            this.inCustAcctId = inCustAcctId.getBytes(this.encoding);
    }

    public String getInThirdCustId() throws UnsupportedEncodingException {
        if (inThirdCustId != null)
            return new String(inThirdCustId, this.encoding);
        else
            return null;
    }

    public void setInThirdCustId(String inThirdCustId) throws UnsupportedEncodingException {
        if (inThirdCustId != null)
            this.inThirdCustId = inThirdCustId.getBytes(this.encoding);
    }

    public String getTranDate() throws UnsupportedEncodingException {
        if (tranDate != null)
            return new String(tranDate, this.encoding);
        else
            return null;
    }

    public void setTranDate(String tranDate) throws UnsupportedEncodingException {
        if (tranDate != null)
            this.tranDate = tranDate.getBytes(this.encoding);
    }

    public Double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

}
