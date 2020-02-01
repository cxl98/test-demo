package com.cxl.method.idcard;

import com.cxl.method.framework.Product;

public class IdCard extends Product {
    private String owner;

    public IdCard(String owner) {
        System.out.println("制作"+owner+"ID卡");
        this.owner = owner;
    }

    public void use() {
        System.out.println("使用"+owner+"ID卡");
    }

    public String getOwner() {
        return owner;
    }
}
