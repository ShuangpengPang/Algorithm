package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3381MaximumSubarraySumWithLengthDivisibleByK（长度可被K整除的最大子数组和）
 * @date 2025/4/7 14:03
 */
public class Problem3381MaximumSubarraySumWithLengthDivisibleByK {

    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] minSum = new long[k];
        Arrays.fill(minSum, 1, k, Long.MAX_VALUE >> 1);
        long ans = Long.MIN_VALUE;
        long s = 0;
        for (int i = 1; i <= n; i++) {
            s += nums[i - 1];
            ans = Math.max(ans, s - minSum[i % k]);
            minSum[i % k] = Math.min(minSum[i % k], s);
        }
        return ans;
    }
}
