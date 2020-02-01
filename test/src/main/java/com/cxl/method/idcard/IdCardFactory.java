package com.cxl.method.idcard;

import com.cxl.method.framework.Factory;
import com.cxl.method.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IdCardFactory extends Factory {
    private List owners=new ArrayList();
    public Product createProduct(String owner) {
        return new IdCard(owner);
    }

    public void registerProduct(Product product) {
        owners.add(((IdCard)product).getOwner());
    }

    public List getOwners() {
        return owners;
    }
    public int size(){
        return owners.size();
    }
}
