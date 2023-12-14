package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0643MaximumAverageSubarrayI（子数组最大平均数I）
 * @date 2023/12/14 2:54 PM
 */
public class Problem0643MaximumAverageSubarrayI {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length, maxSum = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < k - 1; i++) {
            sum += nums[i];
        }
        for (int i = k - 1; i < n; i++) {
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
            sum -= nums[i - k + 1];
        }
        return (double) maxSum / k;
    }
}
