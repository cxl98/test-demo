package com.cxl.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sort {

    /**
     * O(n^2)  稳定
     * @param array no sort Array
     * @return sort Array
     */
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

    /**
     *
     * @param array no sort Array
     * @return sort Array
     */
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

    /**
     * 选择排序
     * O(n^2)  不稳定
     * @param array 未排序数组
     * @return 排序完的数组
     */
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

    /**
     * 插入排序
     *  O(n^2) 稳定
     * @param array
     * @return
     */
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
     * 希尔  不稳定
     *  O(n log n)
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

    /**
     * 归并排序
     * O(n log n) 稳定
     * @param array
     * @return
     */
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

    /**
     * 快速排序
     * O(nlog n) 不稳定
     * @param array
     * @param start
     * @param end
     * @return
     */
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
        int index = start - 1;
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


    int len;

    /**
     * 堆排序
     *O(nlog n) 不稳定
     * @param array
     * @return
     */
    public int[] heapSort(int[] array) {
        len = array.length;
        if (len < 1) {
            return array;
        }
        //构建一个最大堆
        buildMaxHeap(array);
        //循环将堆首位(最大值)与末位交换，然后再重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustMaxHeap(array, 0);
        }
        return array;
    }

    /**
     * 建立最大堆
     *
     * @param array
     */
    private void buildMaxHeap(int[] array) {
        for (int i = (len / 2 - 1); i >= 0; i--) {
            adjustMaxHeap(array, i);
        }
    }

    /**
     * 调整最大堆
     *
     * @param array
     * @param i
     */
    private void adjustMaxHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex]) {
            maxIndex = i * 2;
        }
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustMaxHeap(array, maxIndex);
        }
    }

    /**
     * 计数排序
     * O(n+k) 稳定
     * @param array
     * @return
     */
    public int[] countSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int bits, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        bits = -min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bits]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bits;
                bucket[i]--;
                index++;
            } else {
                i++;
            }
        }
        return array;
    }



    public static int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j <nums.length ; j++) {
                    if (target==nums[i]+nums[j]){
                        return new int[]{i, j};
                    }
                }
            }
        throw new IllegalArgumentException("NO tow sum solution");
    }


    public static int [] towSum1(int[] nums,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if (map.containsKey(res)){
                return new int[]{map.get(res),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("NO tow sum solution");
    }
    public static void main(String[] args) {

        int array[] = {2, 3, 4, 8, 5, 1, 1, 21, 4, 12};
        Sort sort = new Sort();
//        int[] ints = sort.quitSort(array, 0, array.length - 1);
        int[] ints = sort.heapSort(array);
        for (int item : ints) {
            System.out.println(item);
        }
//        int[] ints = towSum1(array, 33);
//        for (int item: ints) {
//            System.out.println(item);
//        }

    }
}