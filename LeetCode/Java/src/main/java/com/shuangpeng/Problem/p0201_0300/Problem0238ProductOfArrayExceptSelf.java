package com.shuangpeng.Problem.p0201_0300;

public class Problem0238ProductOfArrayExceptSelf {

    public int[] productExceptSelf0(int[] nums) {
        if (nums == null) {
            return null;
        }
        int zeroCount = 0;
        int product = 1;
        int zeroIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                product *= nums[i];
            } else {
                zeroIndex = i;
                zeroCount++;
                if (zeroCount > 1) {
                    break;
                }
            }
        }
        int[] result = new int[nums.length];
        if (zeroCount > 1) {
            return result;
        }
        if (zeroCount == 1) {
            result[zeroIndex] = product;
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            result[i] = product / nums[i];
        }
        return result;
    }

    public int[] productExceptSelf1(int[] nums) {
        if (nums == null) {
            return null;
        }
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
            int j = n - 1 - i;
            right[j] = right[j + 1] * nums[j + 1];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
            return null;
        }
        int length = nums.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * right;
            right *= nums[i];
        }
        return result;
    }
}
