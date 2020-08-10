package com.cxl.bridge.concrete.implementor;

import com.cxl.bridge.implementor.DisplayImpl;

public class StringDisplayImpl extends DisplayImpl {
    private String title;
    private int width;

     public StringDisplayImpl(String title) {
        this.title = title;
        this.width = title.getBytes().length;
    }

    public void rawOpen() {
        printLine();
    }

    public void rawPrint() {
        System.out.println("|" + title + "|");
    }

    public void rawClose() {
        printLine();
    }

    private void printLine() {
        System.out.print("+");
        for (int i = 0; i < width+2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
