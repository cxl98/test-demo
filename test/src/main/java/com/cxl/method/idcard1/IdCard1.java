package com.cxl.method.idcard1;

import com.cxl.method.framework.Product;

public class IdCard1 extends Product {
    private String owner;
    private int number;

    public IdCard1(String owner, int number) {
        System.out.println("制作"+owner+"("+number+")"+"ID卡");
        this.owner = owner;
        this.number = number;
    }

    public void use() {
        System.out.println("使用 "+owner+"("+number+")"+"ID卡");
    }

    public String getOwner() {
        return owner;
    }

    public int getNumber() {
        return number;
    }
}
