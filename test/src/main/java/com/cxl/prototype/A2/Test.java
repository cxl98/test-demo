package com.cxl.prototype.A2;


public class Test {
    public static void main(String[] args) {
        Mannager mannager=new Mannager();
        Message message= new Message("*");
        UnderLine underLine=new UnderLine("~");
       mannager.register("aaa",message);
       mannager.register("bbb",underLine);

       Product product=mannager.create("aaa");
       product.use("Hello,World");
       Product product1=mannager.create("bbb");
       product1.use("Hello,World");
    }
}
