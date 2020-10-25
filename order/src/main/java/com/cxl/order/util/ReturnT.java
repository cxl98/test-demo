package com.cxl.order.util;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReturnT<T> implements Serializable {
    private static final long serialVersionUID=208L;

    public static final int SUCCESS_CODE=200;
    public static final int FAIL_CODE=500;

    private int code;
    private String msg;
    private T data;
    public ReturnT() {
    }

    public ReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ReturnT(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }
    public static final ReturnT<String> SUCCESS=new ReturnT<>(null);
    public static final ReturnT<String> FAIL=new ReturnT<>(FAIL_CODE,null);
}
