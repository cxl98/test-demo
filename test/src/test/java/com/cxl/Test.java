package com.cxl;

import com.cxl.learn1.Buyer;
import com.cxl.learn1.Cashier;
import com.cxl.learn1.SuperVipBuyer;
import com.cxl.learn1.VipBuyer;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        Buyer buyer=new VipBuyer();
        Cashier cashier=new Cashier(buyer);
        BigDecimal quote= cashier.quote(BigDecimal.valueOf(300));
        System.out.println("普通会员商品的最终价格为："+quote.doubleValue());

        buyer=new SuperVipBuyer();
        cashier=new Cashier(buyer);
        quote=cashier.quote(BigDecimal.valueOf(300));
        System.out.println("超级会员商品的最终价格为：" + quote.doubleValue());

    }
}
