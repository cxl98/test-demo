package com.cxl;

import com.cxl.adapter.sample2.Print;
import com.cxl.adapter.sample2.PrintBanner1;

public class AdapterTest {
    public static void main(String[] args) {
//        Print print = new PrintBanner("你好");
//        print.printWeak();
//        print.printStrong();
        Print print=new PrintBanner1("你好");
        print.printStrong();
        print.printWeak();

    }
}
