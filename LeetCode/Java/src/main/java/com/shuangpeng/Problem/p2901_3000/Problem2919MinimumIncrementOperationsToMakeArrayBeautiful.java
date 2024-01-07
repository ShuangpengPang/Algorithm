package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2919MinimumIncrementOperationsToMakeArrayBeautiful（使数组变美的最小增量运算数）
 * @date 2024/1/7 8:49 PM
 */
public class Problem2919MinimumIncrementOperationsToMakeArrayBeautiful {

    public long minIncrementOperations0(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        for (int i = 2; i < n; i++) {
            long cnt = Long.MAX_VALUE;
            for (int j = i; j > i - 3; j--) {
                cnt = Math.min(cnt, dp[j] + Math.max(0, k - nums[j]));
            }
            dp[i + 1] = cnt;
        }
        return dp[n];
    }

    public long minIncrementOperations(int[] nums, int k) {
        long[] dp = new long[4];
        for (int i = 2; i < nums.length; i++) {
            long cnt = Long.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                cnt = Math.min(cnt, dp[3 - j] + Math.max(0, k - nums[i - j]));
            }
            for (int j = 1; j <= 3; j++) {
                dp[j - 1] = dp[j];
            }
            dp[3] = cnt;
        }
        return dp[3];
    }
}
