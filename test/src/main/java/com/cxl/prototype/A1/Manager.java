package com.cxl.prototype.A1;

import java.util.HashMap;

public class Manager {
    private HashMap showcase=new HashMap();
    public void register(String name, Product proto){
        showcase.put(name,proto);
    }
    public Product create(String protoName){
        Product product= (Product) showcase.get(protoName);
        return product.createClone();
    }
}
