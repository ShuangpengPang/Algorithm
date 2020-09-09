package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/eQDHPiilPvW7Ro8568Helg
public class PackProblem {
    public static void main(String[] args) {

    }

    public static int complePack(int C[], int W[], int V) {
        if (C.length != W.length) {
            return 0;
        }
        int n = C.length;
        int[][] dp = new int[n + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= C[i - 1] && (dp[i][j] < dp[i][j - C[i - 1]] + W[i - 1])) {
                    dp[i][j] = dp[i][j - C[i - 1]] + W[i - 1];
                }
            }
        }
        return dp[n][V];
    }

    public static int completePackOptimize(int C[], int W[], int V) {
        if (V == 0 || (C.length != W.length)) {
            return 0;
        }
        int n = C.length;
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= V; j++) {
                // 当前的dp[j]的值是i - 1时保存的最大值
                // 0 <= k < j，dp[k]保存的是i，k的最大值
                if (C[i] <= j) {
                    dp[j] = Math.max(dp[j], dp[j - C[i]] + W[i]);
                }
            }
        }
        return dp[V];
    }

    public static int zeroPack(int C[], int W[], int V) {
        if (V == 0 || (C.length != W.length)) {
            return 0;
        }
        int n = C.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = V; j > 0; j--) {
                // dp[j]是i-1时的最大值
                // j < k <= V；dp[k]是i在k时的最大值
                if (j >= C[i]) {
                    dp[j] = Math.max(dp[j], dp[j - C[i]] + W[i]);
                }
            }
        }
        return dp[V];
    }
}