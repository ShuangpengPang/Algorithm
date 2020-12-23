package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0179LargestNumber {

    public String largestNumber0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        Integer[] sorted = new Integer[nums.length];
        boolean isZero = true;
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i];
            if (nums[i] != 0) {
                isZero = false;
            }
        }
        if (isZero) {
            return "0";
        }
        Arrays.sort(sorted, (a, b) -> {
            if (a == b) {
                return 0;
            }
            String str1 = Integer.toString(a);
            String str2 = Integer.toString(b);
            int length1 = str1.length();
            int length2 = str2.length();
            int length = length1 * length2;
            for (int i = 0; i < length; i++) {
                int j = i % length1;
                int k = i % length2;
                if (str1.charAt(j) > str2.charAt(k)) {
                    return -1;
                } else if (str1.charAt(j) < str2.charAt(k)) {
                    return 1;
                }
            }
            return -1;
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sorted.length; i++) {
            builder.append(sorted[i]);
        }
        return builder.toString();
    }

//    public String largestNumber(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return "";
//        }
//        int length = nums.length;
//        String[] strings = new String[length];
//        for (int i = 0; i < length; i++) {
//            strings[i] = String.valueOf(nums[i]);
//        }
//        Arrays.sort(strings, (a, b) -> {
//
//        });
//    }
}
