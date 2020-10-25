package com.cxl.order.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Id {
    private static AtomicInteger generator=new AtomicInteger();


    public long getNextId(){
            return generator.incrementAndGet();
    }
}
