package com.cxl;

import com.cxl.method.framework.Factory;
import com.cxl.method.framework.Product;
import com.cxl.method.idcard1.IdCardFactory1;

import java.util.Map;

public class FactoryTest1 {
    public static void main(String[] args) {
        Factory factory=new IdCardFactory1();
        Product product=factory.create("小明");
        Product product1=factory.create("小");
        Product product2=factory.create("明");
        Product product3=factory.create("xx");
        product.use();
        product1.use();
        product2.use();
        product3.use();
        Map map = ((IdCardFactory1) factory).getMap();
        for (Object item:map.keySet()) {
            System.out.println(map.get(item));
        }
    }
}
