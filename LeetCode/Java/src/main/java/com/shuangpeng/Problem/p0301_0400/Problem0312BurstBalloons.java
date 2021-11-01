package com.shuangpeng.Problem.p0301_0400;

public class Problem0312BurstBalloons {

    public int maxCoins0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] copy = new int[n + 2];
        int length = n + 2;
        copy[0] = 1;
        copy[length - 1] = 1;
        for (int i = 0; i < n; i++) {
            copy[i + 1] = nums[i];
        }
        int[][] dp = new int[length][length];
        for (int len = 2; len < length; len++) {
            for (int i = 0; i + len < length; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + copy[i] * copy[k] * copy[j]);
                }
            }
        }
        return dp[0][length - 1];
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] copy = new int[n + 2];
        int length = copy.length;
        copy[0] = 1;
        copy[length - 1] = 1;
        for (int i = 0; i < n; i ++) {
            copy[i + 1] = nums[i];
        }
        int[][] dp = new int[length][length];
        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + copy[i] * copy[k] * copy[j]);
                }
            }
        }
        return dp[0][length - 1];
    }
}
