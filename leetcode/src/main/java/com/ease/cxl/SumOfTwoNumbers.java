package com.ease.cxl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfTwoNumbers {
    public static int[] twoSum(int[] nums, int target) {
        int sum;
        int[] a;
        for (int i = 0, len = nums.length; i < len; i++) {
            for (int j = i + 1; j < len ; j++) {
                sum = nums[i] + nums[j];
                if (sum == target) {
                    a = new int[]{i, j};
                    return a;
                }
            }
        }
        return null;
    }

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {
                    return new int[] { i, map.get(complement) };
                }
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }

    public static void main(String[] args) {
        int[] nums={3,2,4};
        int[] ints = twoSum(nums, 6);
            System.out.println(Arrays.toString(ints));
    }
}
