package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3192MinimumOperationsToMakeBinaryArrayElementsEqualToOneII（使二进制数组全部等于 1 的最少操作次数 II）
 * @date 2024/7/14 11:52 PM
 */
public class Problem3192MinimumOperationsToMakeBinaryArrayElementsEqualToOneII {

    public int minOperations(int[] nums) {
        int zeros = 0, ones = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int t = ones;
            if (nums[i] == 1) {
                ones = Math.min(ones, zeros + 1);
                zeros = Math.min(t + 1, zeros + 2);
            } else {
                ones = Math.min(ones + 2, zeros + 1);
                zeros = Math.min(t + 1, zeros);
            }
        }
        return ones;
    }
}
