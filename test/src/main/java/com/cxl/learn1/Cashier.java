package com.cxl.learn1;

import com.cxl.learn1.Buyer;

import java.math.BigDecimal;

public class Cashier {
    private Buyer buyer;

    public Cashier(Buyer buyer) {
        this.buyer = buyer;
    }
    public BigDecimal quote(BigDecimal orderPrice){
        return this.buyer.calPrice(orderPrice);
    }
}
