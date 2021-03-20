package com.cxl.algorithm;

import java.util.Arrays;

public class Sort {
    public int[] dubbleSort(int[] array) {
        if (0 == array.length) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public int[] dubbleSort1(int[] array) {
        if (0 == array.length) return array;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length - 1; j++) {
                if (array[j + 1] < array[i]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    public int[] selectionSort(int[] array) {
        if (0 == array.length) return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int[] insertionSort(int[] array) {
        if (0 == array.length) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (0 <= preIndex && array[preIndex] > current) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 希尔
     *
     * @param array
     * @return
     */
    public int[] shellSort(int[] array) {
        if (0 == array.length) {
            return array;
        }
        int len = array.length;
        int temp, add = len / 2;
        while (add > 0) {
            for (int i = add; i < len; i++) {
                temp = array[i];
                int preIndex = i - add;
                while (0 <= preIndex && array[preIndex] > temp) {
                    array[preIndex + add] = array[preIndex];
                    preIndex -= add;
                }
                array[preIndex + add] = temp;
            }
            add >>= 1;
        }
        return array;
    }

//    public int [] shellSort1(int [] array){
//        int temp,add=array.length/2;
//        while(add>0){
//            for (int i = 0; i < add; i++) {
//                temp=array[i];
//                int endIndex=i+add;
//                while(array[endIndex]<temp&&endIndex<= array.length){
//                    array[endIndex+add]=array[endIndex];
//                    endIndex-=add;
//                }
//                array[endIndex+add]=temp;
//            }
//            add/=2;
//        }
//        return array;
//    }

    public int[] mergeSort(int[] array) {
        if (2 > array.length) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    public int[] quitSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end > array.length || start > end) {
            return array;
        }
        int index = part(array, start, end);
        if (index > start) {
            quitSort(array, start, index - 1);
        }
        if (index < end) {
            quitSort(array, index + 1, end);
        }
        return array;
    }

    private int part(int[] array, int start, int end) {
        int index = start -1;
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                index++;
                if (i > index) {
                    swap(array, i, index);
                }
            }
        }
        return index;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {

        int array[] = {2, 3, 4, 8, 5, 1, 1, 21, 4, 12, 43, 4};
        Sort sort = new Sort();
        int[] ints = sort.quitSort(array,0,array.length-1);
        for (int item : ints) {
            System.out.println(item);
        }
    }
}