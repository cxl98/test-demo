package com.cxl.prototype.A2;

import java.util.HashMap;

public class Mannager {
    private HashMap showcase=new HashMap();
    public void register(String name, Product proto){
        showcase.put(name,proto);
    }
    public Product create(String protoName){
        Product product= (Product) showcase.get(protoName);
        return product.createClone();
    }
}
