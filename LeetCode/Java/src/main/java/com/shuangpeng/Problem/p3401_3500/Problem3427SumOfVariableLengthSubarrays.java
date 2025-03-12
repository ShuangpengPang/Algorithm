package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3427SumOfVariableLengthSubarrays（变长子数组求和）
 * @date 2025/3/12 12:00
 */
public class Problem3427SumOfVariableLengthSubarrays {

    public int subarraySum(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            ans += preSum[i + 1] - preSum[Math.max(0, i - nums[i])];
        }
        return ans;
    }
}
