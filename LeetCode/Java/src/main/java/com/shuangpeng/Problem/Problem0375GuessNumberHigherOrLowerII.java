package com.shuangpeng.Problem;

public class Problem0375GuessNumberHigherOrLowerII {

    public int getMoneyAmount0(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                int min = Integer.MAX_VALUE;
                for (int k = j; k < i; k++) {
                    min = Math.min(min, k + Math.max(dp[j][k - 1], dp[k + 1][i]));
                }
                dp[j][i] = min;
            }
        }
        return dp[1][n];
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                int min = Integer.MAX_VALUE;
                for (int k = (j + i) / 2; k < i; k++) {
                    min = Math.min(min, k + Math.max(dp[j][k - 1], dp[k + 1][i]));
                }
                dp[j][i] = min;
            }
        }
        return dp[1][n];
    }
}
