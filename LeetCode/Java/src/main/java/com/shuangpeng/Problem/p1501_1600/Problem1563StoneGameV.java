package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1563StoneGameV（石子游戏V）
 * @Date 2022/8/31 10:49 AM
 * @Version 1.0
 */
public class Problem1563StoneGameV {

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stoneValue[i - 1];
        }
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i; k < j; k++) {
                    int left = preSum[k + 1] - preSum[i], right = preSum[j + 1] - preSum[k + 1];
                    int value = Math.min(left, right);
                    if (left < right) {
                        value += dp[i][k];
                    } else if (left > right) {
                        value += dp[k + 1][j];
                    } else {
                        value += Math.max(dp[i][k], dp[k + 1][j]);
                    }
                    dp[i][j] = Math.max(dp[i][j], value);
                }
            }
        }
        return dp[0][n - 1];
    }
}
