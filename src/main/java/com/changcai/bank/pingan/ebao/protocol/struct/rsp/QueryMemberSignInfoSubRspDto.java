package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 查时间段会员开销户信息【1016】-基本- 返回结果子列表
 * 接口说明：
 *  查询某个时间段会员签解约的信息。
 * @author gaoxiaofei
 * @version $Id: QueryMemberSignInfoSubRspDto.java, v 0.1 2016年9月28日 下午6:14:08 gaoxiaofei Exp $
 */
@Component
@Scope("prototype")
public class QueryMemberSignInfoSubRspDto extends ResponseMessageBody {

    // 银行前置流水号
    @NotNull
    @Size(max = 14)
    @Order(1)
    private byte[] frontLogNo;

    // 交易状态 （1：开户 2：销户 3：待确认）
    @NotNull
    @Size(max = 1)
    @Order(2)
    private byte[] userStatus;

    // 子账户
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] custAcctId;

    // 子账户性质 （1：虚拟账号）
    @NotNull
    @Size(max = 1)
    @Order(4)
    private byte[] custFlag;

    // 会员名称
    @NotNull
    @Size(max = 120)
    @Order(5)
    private byte[] custName;

    // 交易网会员代码
    @NotNull
    @Size(max = 32)
    @Order(6)
    private byte[] thirdCustId;

    // 交易日期
    @NotNull
    @Size(max = 8)
    @Order(7)
    private byte[] tranDate;

    // 操作柜员号
    @NotNull
    @Size(max = 12)
    @Order(8)
    private byte[] counterId;

    public String getFrontLogNo() throws UnsupportedEncodingException {
        if (frontLogNo != null) {
            return new String(frontLogNo, this.encoding);
        } else {
            return null;
        }
    }

    public void setFrontLogNo(String frontLogNo) throws UnsupportedEncodingException {
        if (frontLogNo != null) {
            this.frontLogNo = frontLogNo.getBytes(this.encoding);
        }
    }

    public String getUserStatus() throws UnsupportedEncodingException {
        if (userStatus != null) {
            return new String(userStatus, this.encoding);
        } else {
            return null;
        }
    }

    public void setUserStatus(String userStatus) throws UnsupportedEncodingException {
        if (userStatus != null) {
            this.userStatus = userStatus.getBytes(this.encoding);
        }
    }

    public String getCustAcctId() throws UnsupportedEncodingException {
        if (custAcctId != null) {
            return new String(custAcctId, this.encoding);
        } else {
            return null;
        }
    }

    public void setCustAcctId(String custAcctId) throws UnsupportedEncodingException {
        if (custAcctId != null) {
            this.custAcctId = custAcctId.getBytes(this.encoding);
        }
    }

    public String getCustFlag() throws UnsupportedEncodingException {
        if (custFlag != null) {
            return new String(custFlag, this.encoding);
        } else {
            return null;
        }
    }

    public void setCustFlag(String custFlag) throws UnsupportedEncodingException {
        if (custFlag != null) {
            this.custFlag = custFlag.getBytes(this.encoding);
        }
    }

    public String getCustName() throws UnsupportedEncodingException {
        if (custName != null) {
            return new String(custName, this.encoding);
        } else {
            return null;
        }
    }

    public void setCustName(String custName) throws UnsupportedEncodingException {
        if (custName != null) {
            this.custName = custName.getBytes(this.encoding);
        }
    }

    public String getThirdCustId() throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            return new String(thirdCustId, this.encoding);
        } else {
            return null;
        }
    }

    public void setThirdCustId(String thirdCustId) throws UnsupportedEncodingException {
        if (thirdCustId != null) {
            this.thirdCustId = thirdCustId.getBytes(this.encoding);
        }
    }

    public String getTranDate() throws UnsupportedEncodingException {
        if (tranDate != null) {
            return new String(tranDate, this.encoding);
        } else {
            return null;
        }
    }

    public void setTranDate(String tranDate) throws UnsupportedEncodingException {
        if (tranDate != null) {
            this.tranDate = tranDate.getBytes(this.encoding);
        }
    }

    public String getCounterId() throws UnsupportedEncodingException {
        if (counterId != null) {
            return new String(counterId, this.encoding);
        } else {
            return null;
        }
    }

    public void setCounterId(String counterId) throws UnsupportedEncodingException {
        if (counterId != null) {
            this.counterId = counterId.getBytes(this.encoding);
        }
    }
}
