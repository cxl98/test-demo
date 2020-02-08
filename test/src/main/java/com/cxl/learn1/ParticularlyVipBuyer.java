package com.cxl.learn1;

import java.math.BigDecimal;

public class ParticularlyVipBuyer implements Buyer {
    public BigDecimal calPrice(BigDecimal orderPrice) {
        if (orderPrice.compareTo(new BigDecimal(30))>0) {
            return orderPrice.multiply(new BigDecimal(0.7));
        }
        return orderPrice;
    }
}
