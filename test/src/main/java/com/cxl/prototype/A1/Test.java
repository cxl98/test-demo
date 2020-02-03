package com.cxl.prototype.A1;

public class Test {
    public static void main(String[] args) {
        Manager manager=new Manager();
        UnderlinePen underlinePen=new UnderlinePen("~");
        MessageBox messageBox=new MessageBox("*");
        manager.register("aaa",underlinePen);
        manager.register("bbb",messageBox);

        Product product=manager.create("aaa");
        product.use("Hello,World");
        Product product1=manager.create("bbb");
        product1.use("Hello,World");
    }
}
