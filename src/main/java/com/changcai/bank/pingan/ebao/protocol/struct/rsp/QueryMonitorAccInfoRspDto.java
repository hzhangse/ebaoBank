package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 监管账户信息查询【1021】- 返回结果列表
 * 接口说明：
 * 查询平台监管账户的基本信息。可用于日常的资金及用户数的核对。
 * @author gaoxiaofei
 * @version $Id: QueryMonitorAccInfoRspDto.java, v 0.1 2016年9月28日 下午6:14:08 gaoxiaofei Exp $
 */
@Component
@Scope("prototype")
public class QueryMonitorAccInfoRspDto extends ResponseMessageBody {

    // 汇总账号总数
    @NotNull
    @Size(max = 4)
    @Order(1)
    private byte[] recordNum;

    // 所属交易网代码
    @NotNull
    @Size(max = 4)
    @Order(2)
    private byte[] tranWebCode;

    // 交易网名称
    @NotNull
    @Size(max = 120)
    @Order(3)
    private byte[] tranWebName;

    // 资金汇总账号
    @NotNull
    @Size(max = 32)
    @Order(4)
    private byte[] acctId;

    // 户名
    @NotNull
    @Size(max = 120)
    @Order(5)
    private byte[] acctName;

    // 证件类型
    @NotNull
    @Size(max = 2)
    @Order(6)
    private byte[] idType;

    // 证件号码
    @NotNull
    @Size(max = 20)
    @Order(7)
    private byte[] idCode;

    // 网银用户名
    @NotNull
    @Size(max = 30)
    @Order(8)
    private byte[] webName;

    // 网银客户号
    @NotNull
    @Size(max = 30)
    @Order(9)
    private byte[] webCustId;

    // 手机号码1
    @Size(max = 12)
    @Order(10)
    private byte[] mobilePhone1;

    // 手机号码2
    @Size(max = 12)
    @Order(11)
    private byte[] mobilePhone2;

    // 邮箱1
    @Size(max = 120)
    @Order(12)
    private byte[] emailAddr1;

    // 邮箱2
    @Size(max = 120)
    @Order(13)
    private byte[] emailAddr2;

    // 服务类型 5：存管服务
    @Size(max = 1)
    @Order(14)
    private byte[] funcFlag;

    // 开立子账户方式 3：都可以
    @Size(max = 1)
    @Order(15)
    private byte[] openCustFlag;

    // 币种 	默认为RMB
    @NotNull
    @Size(max = 3)
    @Order(16)
    private byte[] ccyCode;

    // 当前余额 当前存款的可用余额，与主机一致
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(17)
    private Double curBalance;

    // 子账户资金合计 普通子账户的总金额合计
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(18)
    private Double totalAmount;

    // 可转出资金合计 手续费和利息子帐户的余额合计
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(19)
    private Double tranOutAmount;

    // 可用余额总计 普通子会员的可用余额合计
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(20)
    private Double totalBalance;

    // 冻结金额总计 普通子会员的冻结金额总计
    @NotNull
    @Digits(integer = 13, fraction = 2)
    @Order(21)
    private Double totalFreeze;

    // 子账户总数 包括已销户的子账户
    @NotNull
    @Size(max = 8)
    @Order(22)
    private byte[] custTotal;

    // 有效子账户数 不包括已销户的子账户
    @NotNull
    @Size(max = 8)
    @Order(23)
    private byte[] custAvailTotal;

    // 建立日期
    @NotNull
    @Size(max = 8)
    @Order(24)
    private byte[] tranDate;

    // 保留域
    @Size(max = 120)
    @Order(25)
    private byte[] reserve;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        this.ccyCode = "RMB".getBytes(this.encoding);
    }

    public String getRecordNum() throws UnsupportedEncodingException {
        if (recordNum != null) {
            return new String(recordNum, this.encoding);
        } else {
            return null;
        }
    }

    public void setRecordNum(String recordNum) throws UnsupportedEncodingException {
        if (recordNum != null) {
            this.recordNum = recordNum.getBytes(this.encoding);
        }
    }

    public String getTranWebCode() throws UnsupportedEncodingException {
        if (tranWebCode != null) {
            return new String(tranWebCode, this.encoding);
        } else {
            return null;
        }
    }

    public void setTranWebCode(String tranWebCode) throws UnsupportedEncodingException {
        if (tranWebCode != null) {
            this.tranWebCode = tranWebCode.getBytes(this.encoding);
        }
    }

    public String getTranWebName() throws UnsupportedEncodingException {
        if (tranWebName != null) {
            return new String(tranWebName, this.encoding);
        } else {
            return null;
        }
    }

    public void setTranWebName(String tranWebName) throws UnsupportedEncodingException {
        if (tranWebName != null) {
            this.tranWebName = tranWebName.getBytes(this.encoding);
        }
    }

    public String getAcctId() throws UnsupportedEncodingException {
        if (acctId != null) {
            return new String(acctId, this.encoding);
        } else {
            return null;
        }
    }

    public void setAcctId(String acctId) throws UnsupportedEncodingException {
        if (acctId != null) {
            this.acctId = acctId.getBytes(this.encoding);
        }
    }

    public String getAcctName() throws UnsupportedEncodingException {
        if (acctName != null) {
            return new String(acctName, this.encoding);
        } else {
            return null;
        }
    }

    public void setAcctName(String acctName) throws UnsupportedEncodingException {
        if (acctName != null) {
            this.acctName = acctName.getBytes(this.encoding);
        }
    }

    public String getIdType() throws UnsupportedEncodingException {
        if (idType != null) {
            return new String(idType, this.encoding);
        } else {
            return null;
        }
    }

    public void setIdType(String idType) throws UnsupportedEncodingException {
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

    public void setIdCode(String idCode) throws UnsupportedEncodingException {
        if (idCode != null) {
            this.idCode = idCode.getBytes(this.encoding);
        }
    }

    public String getWebName() throws UnsupportedEncodingException {
        if (webName != null) {
            return new String(webName, this.encoding);
        } else {
            return null;
        }
    }

    public void setWebName(String webName) throws UnsupportedEncodingException {
        if (webName != null) {
            this.webName = webName.getBytes(this.encoding);
        }
    }

    public String getWebCustId() throws UnsupportedEncodingException {
        if (webCustId != null) {
            return new String(webCustId, this.encoding);
        } else {
            return null;
        }
    }

    public void setWebCustId(String webCustId) throws UnsupportedEncodingException {
        if (webCustId != null) {
            this.webCustId = webCustId.getBytes(this.encoding);
        }
    }

    public String getMobilePhone1() throws UnsupportedEncodingException {
        if (mobilePhone1 != null) {
            return new String(mobilePhone1, this.encoding);
        } else {
            return null;
        }
    }

    public void setMobilePhone1(String mobilePhone1) throws UnsupportedEncodingException {
        if (mobilePhone1 != null) {
            this.mobilePhone1 = mobilePhone1.getBytes(this.encoding);
        }
    }

    public String getMobilePhone2() throws UnsupportedEncodingException {
        if (mobilePhone2 != null) {
            return new String(mobilePhone2, this.encoding);
        } else {
            return null;
        }
    }

    public void setMobilePhone2(String mobilePhone2) throws UnsupportedEncodingException {
        if (mobilePhone2 != null) {
            this.mobilePhone2 = mobilePhone2.getBytes(this.encoding);
        }
    }

    public String getEmailAddr1() throws UnsupportedEncodingException {
        if (emailAddr1 != null) {
            return new String(emailAddr1, this.encoding);
        } else {
            return null;
        }
    }

    public void setEmailAddr1(String emailAddr1) throws UnsupportedEncodingException {
        if (emailAddr1 != null) {
            this.emailAddr1 = emailAddr1.getBytes(this.encoding);
        }
    }

    public String getEmailAddr2() throws UnsupportedEncodingException {
        if (emailAddr2 != null) {
            return new String(emailAddr2, this.encoding);
        } else {
            return null;
        }
    }

    public void setEmailAddr2(String emailAddr2) throws UnsupportedEncodingException {
        if (emailAddr2 != null) {
            this.emailAddr2 = emailAddr2.getBytes(this.encoding);
        }
    }

    public String getFuncFlag() throws UnsupportedEncodingException {
        if (funcFlag != null) {
            return new String(funcFlag, this.encoding);
        } else {
            return null;
        }
    }

    public void setFuncFlag(String funcFlag) throws UnsupportedEncodingException {
        if (funcFlag != null) {
            this.funcFlag = funcFlag.getBytes(this.encoding);
        }
    }

    public String getOpenCustFlag() throws UnsupportedEncodingException {
        if (openCustFlag != null) {
            return new String(openCustFlag, this.encoding);
        } else {
            return null;
        }
    }

    public void setOpenCustFlag(String openCustFlag) throws UnsupportedEncodingException {
        if (openCustFlag != null) {
            this.openCustFlag = openCustFlag.getBytes(this.encoding);
        }
    }

    public String getCcyCode() throws UnsupportedEncodingException {
        if (ccyCode != null) {
            return new String(ccyCode, this.encoding);
        } else {
            return null;
        }
    }

    public void setCcyCode(String ccyCode) throws UnsupportedEncodingException {
        if (ccyCode != null) {
            this.ccyCode = ccyCode.getBytes(this.encoding);
        }
    }

    public Double getCurBalance() {
        return curBalance;
    }

    public void setCurBalance(Double curBalance) {
        this.curBalance = curBalance;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTranOutAmount() {
        return tranOutAmount;
    }

    public void setTranOutAmount(Double tranOutAmount) {
        this.tranOutAmount = tranOutAmount;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Double getTotalFreeze() {
        return totalFreeze;
    }

    public void setTotalFreeze(Double totalFreeze) {
        this.totalFreeze = totalFreeze;
    }

    public String getCustTotal() throws UnsupportedEncodingException {
        if (custTotal != null) {
            return new String(custTotal, this.encoding);
        } else {
            return null;
        }
    }

    public void setCustTotal(String custTotal) throws UnsupportedEncodingException {
        if (custTotal != null) {
            this.custTotal = custTotal.getBytes(this.encoding);
        }
    }

    public String getCustAvailTotal() throws UnsupportedEncodingException {
        if (custAvailTotal != null) {
            return new String(custAvailTotal, this.encoding);
        } else {
            return null;
        }
    }

    public void setCustAvailTotal(String custAvailTotal) throws UnsupportedEncodingException {
        if (custAvailTotal != null) {
            this.custAvailTotal = custAvailTotal.getBytes(this.encoding);
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
