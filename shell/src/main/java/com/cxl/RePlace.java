package com.cxl;

public class RePlace {
    public static void main(String[] args) {
//        ArrayList arrayList=new ArrayList();
//        arrayList.addAll(Arrays.asList(args));
        String replace = args[0].replace(";", "?");
        System.out.println(replace);
    }
}
