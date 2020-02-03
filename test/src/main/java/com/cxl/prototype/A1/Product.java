package com.cxl.prototype.A1;

public interface Product extends Cloneable {
    void use(String s);
    Product createClone();
}
