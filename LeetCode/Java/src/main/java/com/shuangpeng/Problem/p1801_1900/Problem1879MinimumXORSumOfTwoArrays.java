package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

/**
 * @Description: Problem1879MinimumXORSumOfTwoArrays（两个数组最小的异或值之和）
 * @Date 2022/10/25 11:30 AM
 * @Version 1.0
 */
public class Problem1879MinimumXORSumOfTwoArrays {

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int n = nums1.length, N = 1 << n;
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < N; i++) {
            int num = nums1[Integer.bitCount(i) - 1];
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    dp[i] = Math.min(dp[i], dp[i ^ (1 << j)] + (num ^ nums2[j]));
                }
            }
        }
        return dp[N - 1];
    }
}
