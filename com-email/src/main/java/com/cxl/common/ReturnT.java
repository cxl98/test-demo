package com.cxl.common;

import lombok.Data;

import java.io.Serializable;
@Data
public class ReturnT<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    public static <T> ReturnT<T>

    success(T data){
        ReturnT<T> result=new ReturnT<T>();
        result.setStatus(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static <T> ReturnT<T> fali(int status,String msg){
        ReturnT<T> result=new ReturnT<T>();
        result.setStatus(status);
        result.setMsg(msg);
        return result;
    }
}
