package com.cxl.adapter.sample1;

public class PrintBanner extends Banner implements Print {

    public PrintBanner(String name) {
        super(name);
    }

    public void printWeak() {
        showWithParen();
    }

    public void printStrong() {
        showWithAster();
    }
}
