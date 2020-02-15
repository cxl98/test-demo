package com.cxl.strategy.A3;

public class SortAndPrint {
    Comparable[] data;
    Sort sort;

    public SortAndPrint(Comparable[] data, Sort sort) {
        this.data = data;
        this.sort = sort;
    }
    public void execute(){
        print();
        sort.sort(data);
        print();
    }

    private void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]+",");
        }
        System.out.println();
    }
}
