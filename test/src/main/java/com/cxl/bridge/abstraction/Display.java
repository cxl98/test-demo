package com.cxl.bridge.abstraction;

import com.cxl.bridge.implementor.DisplayImpl;

public class Display {
    private DisplayImpl display;

    public Display(DisplayImpl display) {
        this.display = display;
    }


    protected void open() {
        display.rawOpen();
    }

    protected void print() {
        display.rawPrint();
    }

    protected void close() {
        display.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}
