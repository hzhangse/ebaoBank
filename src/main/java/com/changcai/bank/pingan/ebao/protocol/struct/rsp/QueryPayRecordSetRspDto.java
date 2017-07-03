/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 查询支付指令状态【1327】
 * 应答包：监管系统－>交易网
 * 接口说明
 * 用于查询【1328】接口提交的申请支付的指令状态。
 * 4：处理中是中间状态，若支付最终成功则变为2：已复核，若最终失败则为1：待复核。
 * @author ryan
 * @version $Id: QueryMoneyRecordSetReqDto.java, v 0.1 2016年9月15日 下午6:00:59 ryan Exp $
 */
@Component
@Scope("prototype")
public class QueryPayRecordSetRspDto extends ResponseMessageBody {
    public enum Status {
        Status_1("待复核", "1"), Status_2("已复核", "2"), Status_3("已撤销", "3"), Status_4("处理中", "4");
        private String desc;
        private String code;

        Status(String desc, String code) {
            this.desc = desc;
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }

    //  指令状态   Status  C(1)    必输  1：待复核 2：已复核 3：已撤销4：处理中
    @NotNull
    @Size(max = 1)
    @Order(1)
    private byte[] status;

    //  支付金额    TranAmount  9(15)   必输  
    @Digits(integer=13, fraction=2) 
    @NotNull
    @Order(2)
    private Double tranAmount;
    
    //  转出子账户   OutCustAcctId   C(32)   必输  即付款方
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] outCustAcctId;
    
    //  转出会员代码  OutThirdCustId  C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(4)
    private byte[] outThirdCustId;
    
    //  转入子账户   InCustAcctId    C(32)   必输  即收款方
    @NotNull
    @Size(max = 32)
    @Order(5)
    private byte[] inCustAcctId;
    
    //  转入会员代码  InThirdCustId   C(32)   必输  
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] inThirdCustId;
    
    //  支付订单号   ThirdHtId   C(30)   必输  
    @NotNull
    @Size(max = 30)
    @Order(7)
    private byte[] thirdHtId;
    
    //  指令变动日期  ChangeDate  C(8)    可选  若已复核或撤销，则返回指令变动的日期，若未变动则返回为空。
    @Size(max = 8)
    @Order(8)
    private byte[] ChangeDate;
    
    //  保留域 Reserve C(120)  可选  
    @Size(max = 120)
    @Order(9)
    private byte[] reserve;

    public Double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

   public String getStatus() throws UnsupportedEncodingException {
        if (status != null) {
            return new String(status, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setStatus(String status) throws UnsupportedEncodingException {
        if (status != null) {
            this.status = status.getBytes(this.encoding);
        }
    }

   public String getOutCustAcctId() throws UnsupportedEncodingException {
        if (outCustAcctId != null) {
            return new String(outCustAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setOutCustAcctId(String outCustAcctId) throws UnsupportedEncodingException {
        if (outCustAcctId != null) {
            this.outCustAcctId = outCustAcctId.getBytes(this.encoding);
        }
    }

   public String getOutThirdCustId() throws UnsupportedEncodingException {
        if (outThirdCustId != null) {
            return new String(outThirdCustId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setOutThirdCustId(String outThirdCustId) throws UnsupportedEncodingException {
        if (outThirdCustId != null) {
            this.outThirdCustId = outThirdCustId.getBytes(this.encoding);
        }
    }

   public String getInCustAcctId() throws UnsupportedEncodingException {
        if (inCustAcctId != null) {
            return new String(inCustAcctId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setInCustAcctId(String inCustAcctId) throws UnsupportedEncodingException {
        if (inCustAcctId != null) {
            this.inCustAcctId = inCustAcctId.getBytes(this.encoding);
        }
    }

   public String getInThirdCustId() throws UnsupportedEncodingException {
        if (inThirdCustId != null) {
            return new String(inThirdCustId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setInThirdCustId(String inThirdCustId) throws UnsupportedEncodingException {
        if (inThirdCustId != null) {
            this.inThirdCustId = inThirdCustId.getBytes(this.encoding);
        }
    }

   public String getThirdHtId() throws UnsupportedEncodingException {
        if (thirdHtId != null) {
            return new String(thirdHtId, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setThirdHtId(String thirdHtId) throws UnsupportedEncodingException {
        if (thirdHtId != null) {
            this.thirdHtId = thirdHtId.getBytes(this.encoding);
        }
    }

   public String getChangeDate() throws UnsupportedEncodingException {
        if (ChangeDate != null) {
            return new String(ChangeDate, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setChangeDate(String changeDate) throws UnsupportedEncodingException {
        if (changeDate != null) {
            ChangeDate = changeDate.getBytes(this.encoding);
        }
    }

   public String getReserve() throws UnsupportedEncodingException {
        if (reserve != null) {
            return new String(reserve, this.encoding);
        } else {
            return null;
        }
    
    }

    public void setReserve(String reserve) throws UnsupportedEncodingException {
        if (reserve != null) {
            this.reserve = reserve.getBytes(this.encoding);
        }
    }
}
