package com.cxl.builder.A3;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }
    public void construct(){
        builder.makeTitle("你好");
        builder.makeString("cxl");
        builder.makeItem(new String[]{
                "我是xxx",
                "我是aaa",
                "我是vvv"
        });
        builder.close();
    }
}
