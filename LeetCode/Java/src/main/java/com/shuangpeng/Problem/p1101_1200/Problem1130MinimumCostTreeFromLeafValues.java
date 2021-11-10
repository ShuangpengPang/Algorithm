package com.shuangpeng.Problem.p1101_1200;

public class Problem1130MinimumCostTreeFromLeafValues {

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[][] maxes = new int[n][n];
        for (int i = 0; i < n; ++i) {
            maxes[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                maxes[i][j] = Math.max(maxes[i][j - 1], arr[j]);
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                long cost = dp[i + 1][j] + arr[i] * maxes[i + 1][j];
                for (int k = i + 1; k < j; ++k) {
                    cost = Math.min(cost, dp[i][k] + dp[k + 1][j] + maxes[i][k] * maxes[k + 1][j]);
                }
                dp[i][j] = (int) cost;
            }
        }
        return dp[0][n - 1];
    }

//    public static void main(String[] args) {
//        Problem1130MinimumCostTreeFromLeafValues a = new Problem1130MinimumCostTreeFromLeafValues();
//        a.mctFromLeafValues(new int[]{14, 11, 3, 9, 3, 2});
//    }
}
