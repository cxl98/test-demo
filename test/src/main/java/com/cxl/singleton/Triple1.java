package com.cxl.singleton;

import java.util.ArrayList;

public class Triple1 {
    private static Triple1 triple1=new Triple1();
    private static ArrayList<Triple1> arrayList=new ArrayList<Triple1>();
    private Triple1(){
    }

    public static Triple1 getInstance(int id) {
        arrayList.add(0,triple1);
        arrayList.add(1,triple1);
        arrayList.add(2,triple1);
        return arrayList.get(id);
    }

}
