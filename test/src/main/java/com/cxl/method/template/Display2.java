package com.cxl.method.template;

public class Display2 extends AbstractDisplay {
    private String string;
    private int width;

    public Display2(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    public void open() {
        printFormat();
    }

    private void printFormat() {
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void print() {
        System.out.println(string);
    }

    public void close() {
        printFormat();
    }
}
