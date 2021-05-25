package com.cxl.easrch.springcore;

/**
 * @author cxl
 */
public class Entry {
    private String message;
    private String name;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("创建玩实例");
    }
    public void destroy(){
        System.out.println("destroy");
    }
}
