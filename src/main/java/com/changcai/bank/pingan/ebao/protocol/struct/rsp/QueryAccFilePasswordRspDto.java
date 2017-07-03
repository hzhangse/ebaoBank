package com.changcai.bank.pingan.ebao.protocol.struct.rsp;

import com.changcai.bank.pingan.ebao.protocol.struct.ResponseMessageBody;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.UnsupportedEncodingException;

/**
 * 查询对账文件密码【1349】- 返回结果列表
 * 接口说明：
 * 该接口用于查询对账文件密码。
 * @author gaoxiaofei
 * @version $Id: QueryAccFilePasswordRspDto.java, v 0.1 2016年9月28日 下午6:14:08 gaoxiaofei Exp $
 */
@Component
@Scope("prototype")
public class QueryAccFilePasswordRspDto extends ResponseMessageBody {

    // 文件名称
    @NotNull
    @Size(max = 32)
    @Order(1)
    private byte[] fileName;

    // 文件密码
    @NotNull
    @Size(max = 32)
    @Order(2)
    private byte[] filePSW;

    // 文件存在标志 0：无文件 1：有文件
    @NotNull
    @Size(max = 32)
    @Order(3)
    private byte[] fileExist;

    // 保留域
    @Size(max = 120)
    @Order(4)
    private byte[] reserve;

    public String getFileName() throws UnsupportedEncodingException {
        if (fileName != null) {
            return new String(fileName, this.encoding);
        } else {
            return null;
        }
    }

    public void setFileName(String fileName) throws UnsupportedEncodingException {
        if (fileName != null) {
            this.fileName = fileName.getBytes(this.encoding);
        }
    }

    public String getFilePSW() throws UnsupportedEncodingException {
        if (filePSW != null) {
            return new String(filePSW, this.encoding);
        } else {
            return null;
        }
    }

    public void setFilePSW(String filePSW) throws UnsupportedEncodingException {
        if (filePSW != null) {
            this.filePSW = filePSW.getBytes(this.encoding);
        }
    }

    public String getFileExist() throws UnsupportedEncodingException {
        if (fileExist != null) {
            return new String(fileExist, this.encoding);
        } else {
            return null;
        }
    }

    public void setFileExist(String fileExist) throws UnsupportedEncodingException {
        if (fileExist != null) {
            this.fileExist = fileExist.getBytes(this.encoding);
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
