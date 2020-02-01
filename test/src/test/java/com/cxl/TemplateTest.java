package com.cxl;

import com.cxl.method.template.AbstractDisplay;
import com.cxl.method.template.Display1;
import com.cxl.method.template.Display2;

public class TemplateTest {
    public static void main(String[] args) {
        AbstractDisplay d1=new Display1("xxx");
        AbstractDisplay d2=new Display2("hello,world");
        AbstractDisplay d3=new Display2("你好，世界");
        d1.display();
        d2.display();
        d3.display();
    }
}
