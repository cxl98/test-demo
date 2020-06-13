package com.cxl.arrayList;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList implements Runnable{

    private ArrayList arrayList=new ArrayList();
    public static void main(String[] args) {
//        TestArrayList testArrayList=new TestArrayList();
//        for (int i = 0; i <100 ; i++) {
//            new Thread(testArrayList).start();
//        }
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.forEach(System.out::println);
    }

    @Override
    public void run() {
        arrayList.add(Thread.currentThread().getName());
    }
}
