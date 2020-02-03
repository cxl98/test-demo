package com.cxl.prototype.A2;

public class UnderLine extends Product {
    private String format;

    public UnderLine(String format) {
        this.format = format;
    }

    void use(String s) {
        int length = s.getBytes().length;
        System.out.println();
        System.out.println("\"" + s + "\"");
        for (int i = 0; i <length ; i++) {
            System.out.print(format);
        }
        System.out.println();
    }
}
