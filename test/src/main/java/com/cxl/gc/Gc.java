package com.cxl.gc;

import java.util.ArrayList;

public class Gc {
    public Object object=null;

    private static final int M=1024*1024;
    private byte[] size=new byte[2*M];
    private ArrayList arrayList=new ArrayList();
    public static void main(String[] args) {
        Gc a=new Gc();
        Gc b=new Gc();
        a.object=null;
        b.object=null;
        System.gc();
    }
}
