package com.cxl.arrayList;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestHashMap {
    public static void main(String[] args) {
        Map<Integer, Integer> map=new ConcurrentHashMap<>();
        map.put(1,2);
        map.put(2,2);
    }
}
