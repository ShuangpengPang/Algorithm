package com.shuangpeng.Problem.p1001_1100;

public class Problem1039MinimumScoreTriangulationOfPolygon {

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = n - 3; i >= 0; --i) {
            dp[i][i + 2] = values[i] * values[i + 1] * values[i + 2];
            for (int j = i + 3; j < n; ++j) {
                int product = values[i] * values[j];
                dp[i][j] = dp[i][j - 1] + values[j - 1] * product;
                for (int k = i + 1; k < j - 1; ++k) {
                    int sum = dp[i][k] + values[k] * product + dp[k][j];
                    dp[i][j] = Math.min(dp[i][j], sum);
                }
            }
        }
        return dp[0][n - 1];
    }
}
