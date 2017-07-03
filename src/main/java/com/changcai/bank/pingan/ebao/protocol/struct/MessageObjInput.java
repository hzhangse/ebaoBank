/**
 * ChangCai.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.nio.charset.Charset;

/**
 * 自定义消息ObjectInput ,主要用来对消息->对象之间转化,转化规则参考message 对象的readExternal()方法
 *  @see BaseMessageHeader#readExternal(java.io.ObjectInput)
 *  @see BaseMessageBody#readExternal(java.io.ObjectInput)
 *  
 * @author ryan
 * @version $Id: MessageInPut.java, v 0.1 2016年9月8日 下午7:48:32 ryan Exp $
 */
public class MessageObjInput implements ObjectInput {

    private InputStream inputStream;
    private Charset     charset;

    private BaseMessage message;
    protected byte[]    inputBytes;

    public MessageObjInput(InputStream input, BaseMessage message) {
        this.inputStream = input;
        this.message = message;
    }

    public MessageObjInput(InputStream input, BaseMessage message, Charset charset) {
        this.inputStream = input;
        this.charset = charset;
        this.message = message;
    }

    /** 
     * @see java.io.DataInput#readFully(byte[])
     */
    @Override
    public void readFully(byte[] b) throws IOException {
    }

    /** 
     * @see java.io.DataInput#readFully(byte[], int, int)
     */
    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
    }

    /** 
     * @see java.io.DataInput#skipBytes(int)
     */
    @Override
    public int skipBytes(int n) throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readBoolean()
     */
    @Override
    public boolean readBoolean() throws IOException {
        return false;
    }

    /** 
     * @see java.io.DataInput#readByte()
     */
    @Override
    public byte readByte() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readUnsignedByte()
     */
    @Override
    public int readUnsignedByte() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readShort()
     */
    @Override
    public short readShort() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readUnsignedShort()
     */
    @Override
    public int readUnsignedShort() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readChar()
     */
    @Override
    public char readChar() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readInt()
     */
    @Override
    public int readInt() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readLong()
     */
    @Override
    public long readLong() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readFloat()
     */
    @Override
    public float readFloat() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readDouble()
     */
    @Override
    public double readDouble() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.DataInput#readLine()
     */
    @Override
    public String readLine() throws IOException {
        return null;
    }

    /** 
     * @see java.io.DataInput#readUTF()
     */
    @Override
    public String readUTF() throws IOException {
        return null;
    }

    /** 
     * @see java.io.ObjectInput#readObject()
     */
    @Override
    public Object readObject() throws ClassNotFoundException, IOException {
        if (message != null) {
            message.readExternal(this);
            message.validateDto();
        }
        return message;
    }

    /** 
     * @see java.io.ObjectInput#read()
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.ObjectInput#read(byte[])
     */
    @Override
    public int read(byte[] b) throws IOException {
        return 0;
    }

    /** 
     * @see java.io.ObjectInput#read(byte[], int, int)
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * @see java.io.ObjectInput#skip(long)
     */
    @Override
    public long skip(long n) throws IOException {
        return 0;
    }

    /** 
     * @see java.io.ObjectInput#available()
     */
    @Override
    public int available() throws IOException {
        return 0;
    }

    /** 
     * @see java.io.ObjectInput#close()
     */
    @Override
    public void close() throws IOException {
        this.inputStream.close();
        this.inputBytes = null;
        this.inputStream = null;
    }

    /**
     * Getter method for property <tt>input</tt>.
     * 
     * @return property value of input
     */
    public InputStream getInput() {
        return inputStream;
    }

    /**
     * Setter method for property <tt>input</tt>.
     * 
     * @param input value to be assigned to property input
     */
    public void setInput(InputStream input) {
        this.inputStream = input;
    }

    /**
     * Getter method for property <tt>charset</tt>.
     * 
     * @return property value of charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * Setter method for property <tt>charset</tt>.
     * 
     * @param charset value to be assigned to property charset
     */
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    /**
     * Getter method for property <tt>message</tt>.
     * 
     * @return property value of message
     */
    public BaseMessage getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     * 
     * @param message value to be assigned to property message
     */
    public void setMessage(BaseMessage message) {
        this.message = message;
    }

    /**
     * 把当前的inputStream 读成byte数组,并放在实例变量中,避免每次重复读取input stream
     * 
     * @return
     * @throws IOException
     */
    public byte[] getInputBytes() throws IOException {
        if (inputBytes == null) {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            int count = -1;
            while ((count = inputStream.read(data, 0, 1024)) != -1) {
                outStream.write(data, 0, count);
            }
            data = null;
            this.inputBytes = outStream.toByteArray();
        }
        return this.inputBytes;
    }

}
