package com.cxl.prototype.A2;

public class Message extends Product {
    private String format;

    public Message(String format) {
        this.format = format;
    }

    void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length; i++) {
            System.out.print(format);
        }
        System.out.println();
        System.out.println("*" + s + "*");

        for (int i = 0; i < length; i++) {
            System.out.print(format);
        }
    }
}
