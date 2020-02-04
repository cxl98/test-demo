package com.cxl.builder.A1;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct(){
        builder.makeTitle("xxx");
        builder.buildString("nihao");
        builder.buildItems(new String[]{
                "xxx",
                "aaa",
                "www"
        });
        builder.close();
    }
}
