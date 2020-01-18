package com.cxl.learn1;

import com.cxl.learn1.Buyer;

import java.math.BigDecimal;

public class SuperVipBuyer implements Buyer {
    public BigDecimal calPrice(BigDecimal orderPrice) {
        return orderPrice.multiply(new BigDecimal(0.8));
    }
}
