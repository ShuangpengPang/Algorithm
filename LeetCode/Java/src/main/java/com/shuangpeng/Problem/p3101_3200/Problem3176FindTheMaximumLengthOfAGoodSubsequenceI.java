package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3176FindTheMaximumLengthOfAGoodSubsequenceI（求出最长好子序列 I）
 * @date 2024/10/17 6:34 PM
 */
public class Problem3176FindTheMaximumLengthOfAGoodSubsequenceI {

    public int maximumLength(int[] nums, int k) {
        int n = nums.length, M = Integer.MIN_VALUE;
        int[][] dp = new int[n][k + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = j == 0 ? 1 : M;
                for (int m = 0; m < i; m++) {
                    if (nums[i] == nums[m]) {
                        dp[i][j] = Math.max(dp[i][j], dp[m][j] + 1);
                    } else if (j != 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + 1);
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
