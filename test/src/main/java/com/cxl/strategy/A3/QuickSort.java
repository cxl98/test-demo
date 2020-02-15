package com.cxl.strategy.A3;

public class QuickSort implements Sort {
    Comparable[] date;
    @Override
    public void sort(Comparable[] date) {
        this.date=date;
        qSort(0,date.length-1);
    }

    private void qSort(int pre,int post) {
        int save_pre=pre;
        int save_sort=post;
        Comparable mid=date[(pre+post)/2];
        //////////
    }
}
