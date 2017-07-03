/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 *   自定义消息ObjectOutput ,主要用来对对象-消息之间转化,转化规则参考message 对象的writeExternal()方法
 *  @see BaseMessage#writeExternal(java.io.ObjectInput)
 *  
 * @author ryan
 * @version $Id: MessageObjectOutPut.java, v 0.1 2016年9月8日 下午7:21:41 ryan Exp $
 */
public class MessageObjOutput implements ObjectOutput {
    private OutputStream out;
    private Charset      charset;

    public MessageObjOutput(OutputStream out) {
        this.out = out;
    }

    public MessageObjOutput(OutputStream out, Charset charset) {
        this.out = out;
        this.charset = charset;
    }

    /** 
     * @see java.io.DataOutput#writeBoolean(boolean)
     */
    @Override
    public void writeBoolean(boolean v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeByte(int)
     */
    @Override
    public void writeByte(int v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeShort(int)
     */
    @Override
    public void writeShort(int v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeChar(int)
     */
    @Override
    public void writeChar(int v) throws IOException {
        out.write(v);
    }

    /** 
     * @see java.io.DataOutput#writeInt(int)
     */
    @Override
    public void writeInt(int v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeLong(long)
     */
    @Override
    public void writeLong(long v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeFloat(float)
     */
    @Override
    public void writeFloat(float v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeDouble(double)
     */
    @Override
    public void writeDouble(double v) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeBytes(java.lang.String)
     */
    @Override
    public void writeBytes(String s) throws IOException {
        if (this.charset != null)
            out.write(s.getBytes(charset));
        else
            out.write(s.getBytes());
    }

    /** 
     * @see java.io.DataOutput#writeChars(java.lang.String)
     */
    @Override
    public void writeChars(String s) throws IOException {
    }

    /** 
     * @see java.io.DataOutput#writeUTF(java.lang.String)
     */
    @Override
    public void writeUTF(String s) throws IOException {
    }

    /** 
     * @see java.io.ObjectOutput#writeObject(java.lang.Object)
     */
    @Override
    public void writeObject(Object obj) throws IOException {
        if (obj instanceof BaseMessage) {
            ((BaseMessage) obj).validateDto();
            ((BaseMessage) obj).writeExternal(this);
        }
    }

    /** 
     * @see java.io.ObjectOutput#write(int)
     */
    @Override
    public void write(int b) throws IOException {
    }

    /** 
     * @see java.io.ObjectOutput#write(byte[])
     */
    @Override
    public void write(byte[] b) throws IOException {
            out.write(b);
    }

    /** 
     * @see java.io.ObjectOutput#write(byte[], int, int)
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
    }

    /** 
     * @see java.io.ObjectOutput#flush()
     */
    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    /** 
     * @see java.io.ObjectOutput#close()
     */
    @Override
    public void close() throws IOException {
        this.out.close();
        this.out = null;
    }

}
