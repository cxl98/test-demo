package com.cxl.adapter.sample2;

import com.cxl.adapter.sample1.Banner;

public class PrintBanner1 extends Print {
    private Banner banner;

    public PrintBanner1(String name) {
        this.banner=new Banner(name);
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }
}
