package com.changcai.bank.pingan.ebao.protocol.struct;

import java.io.Serializable;

public class ContentArrCls implements Serializable{
    /**  */
	private static final long serialVersionUID = -8674638782924165020L;
	private String[] contentArr;
    private int      index;
   

    public String getContentValue() {
        return this.contentArr[index];
    }

    public String[] getContentArr() {
        return this.contentArr;
    }

    public void setContentArr(String[] contentArr) {
        this.contentArr = contentArr;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}