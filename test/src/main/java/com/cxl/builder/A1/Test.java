package com.cxl.builder.A1;

public class Test {
    public static void main(String[] args) {
        Builder builder=new StringBuild();
        Director director=new Director(builder);
        director.construct();
        String result = ((StringBuild) builder).getResult();
        System.out.println(result);
    }
}
