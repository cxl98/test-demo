package com.cxl.prototype.A1;



public class MessageBox implements Product {
    private String name;

    public MessageBox(String name) {
        this.name = name;
    }

    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length; i++) {
            System.out.print(name);
        }
        System.out.println();
        System.out.println(name + " " + s + " " + name);
        for (int i = 0; i <length; i++) {
            System.out.print(name);
        }
        System.out.println();
    }

    public Product createClone() {
        Product p=null;
        try {
            p= (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
