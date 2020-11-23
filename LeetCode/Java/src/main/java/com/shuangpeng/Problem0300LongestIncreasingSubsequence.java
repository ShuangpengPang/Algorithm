package com.shuangpeng;

public class Problem0300LongestIncreasingSubsequence {

    // [4,10,4,3,8,9]

    // 动态规划
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] >= dp[i]) {
                    dp[i] = dp[j] + 1;
                    if (dp[i] > max) {
                        break;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
