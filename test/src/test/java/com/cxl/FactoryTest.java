package com.cxl;

import com.cxl.method.framework.Factory;
import com.cxl.method.framework.Product;
import com.cxl.method.idcard.IdCardFactory;

import java.util.List;

public class FactoryTest {
    public static void main(String[] args) {
        Factory factory=new IdCardFactory();
        Product product=factory.create("xxx");
        Product product1=factory.create("yyy");
        Product product2 = factory.create("ccc");
        System.out.println(((IdCardFactory) factory).size());
        product.use();
        product1.use();
        product2.use();
        List owners = ((IdCardFactory) factory).getOwners();
        for (Object item: owners) {
            System.out.println("item  "+item);
        }
    }
}
