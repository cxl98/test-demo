package com.cxl.method.template;

public class Display1 extends AbstractDisplay {
    private String name;

    public Display1(String name) {
        this.name = name;
    }

    public void open() {
        System.out.print("<<");
    }

    public void print() {
        System.out.print(name);
    }

    public void close() {
        System.out.println(">>");
    }
}
