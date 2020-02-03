package com.cxl.prototype.A2;

public abstract class Product implements Cloneable {
    abstract void use(String s);

    /**
     * 不应该暴露出来，所以Product跟Mannager应该在同一个包
     * 其他不应该同一个包
     * @return
     */
    protected Product createClone(){
        Product product=null;
        try {
            product= (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return product;
    }
}
