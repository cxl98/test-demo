package com.cxl.learn1;

import java.math.BigDecimal;

public class VipBuyer implements Buyer {
    public BigDecimal calPrice(BigDecimal orderPrice) {
        int superVipExpiredOrderDays=getSuperVipExpiredOrderDays();
        int superVipLeadDiscountTimes=getSuperVipLeadDiscountTimes();
        if (superVipExpiredOrderDays<7&& superVipLeadDiscountTimes==0){
            return orderPrice.multiply(new BigDecimal(0.8));
        }
        return orderPrice.multiply(new BigDecimal(0.9));
    }

    private int getSuperVipLeadDiscountTimes() {
        return 11;
    }

    private int getSuperVipExpiredOrderDays() {
        return 0;
    }
}
