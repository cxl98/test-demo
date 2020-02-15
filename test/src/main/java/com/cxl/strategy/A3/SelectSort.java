package com.cxl.strategy.A3;

public class SelectSort implements Sort {
    @Override
    public void sort(Comparable[] date) {
        for (int i = 0; i < date.length; i++) {
            int min=i;
            for (int j = i+1; j <date.length ; j++) {
                if (date[min].compareTo(date[j])>0) {
                    min=j;
                }
            }
            Comparable passingplace=date[min];
            date[min]=date[i];
            date[i]=passingplace;
        }
    }
}
