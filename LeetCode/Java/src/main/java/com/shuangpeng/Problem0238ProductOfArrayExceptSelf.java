package com.shuangpeng;

public class Problem0238ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
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
}
