package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3452SumOfGoodNumbers（好数字之和）
 * @date 2025/3/13 18:40
 */
public class Problem3452SumOfGoodNumbers {

    public int sumOfGoodNumbers(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if ((i < k || nums[i] > nums[i - k]) && (i + k >= n || nums[i] > nums[i + k])) {
                sum += nums[i];
            }
        }
        return sum;
    }
}
