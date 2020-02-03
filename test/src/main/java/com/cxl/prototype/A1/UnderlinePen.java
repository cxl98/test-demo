package com.cxl.prototype.A1;


public class UnderlinePen implements Product {
    private String name;

    public UnderlinePen(String name) {
        this.name = name;
    }


    public void use(String s) {
        int length=s.getBytes().length;
        System.out.println("\""+s+"\"");
        System.out.println();
        for (int i = 0; i <length ; i++) {
            System.out.print(name);
        }
        System.out.println();
    }

    public Product createClone() {
        Product product=null;
        try {
            product= (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void use() {

    }
}
