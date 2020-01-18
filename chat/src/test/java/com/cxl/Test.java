package com.cxl;

public class Test {
    private static <T extends Number & Comparable<? super T>> T min(T[] value) {
        if (value == null || value.length == 0) {
            return null;
        }
        T min = value[0];
        for (int i = 1; i < value.length; i++) {
            if (min.compareTo(value[i]) > 0) {
                min = value[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int mini=min(new Integer[]{1,2,3});
        double mind=min(new Double[]{1.2,2.2,-1d});
    }
}
