package com.cxl.method.idcard1;

import com.cxl.method.framework.Factory;
import com.cxl.method.framework.Product;

import java.util.HashMap;
import java.util.Map;

public class IdCardFactory1 extends Factory {
    private Map map=new HashMap();
    private int number=1000;
    protected Product createProduct(String owner) {
        return new IdCard1(owner,number++);
    }

    protected void registerProduct(Product product) {
        IdCard1 card1= (IdCard1) product;
        map.put(new Integer(card1.getNumber()),card1.getOwner());
    }

    public Map getMap() {
        return map;
    }
}
