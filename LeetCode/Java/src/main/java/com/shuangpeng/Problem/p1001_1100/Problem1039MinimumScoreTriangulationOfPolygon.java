package com.shuangpeng.Problem.p1001_1100;

/**
 * @description: 多边形三角剖分的最低得分
 * @date 2023/4/3 6:02 PM
 **/
public class Problem1039MinimumScoreTriangulationOfPolygon {

    public int minScoreTriangulation0(int[] values) {
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

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int product = values[i] * values[j];
                dp[i][j] = dp[i + 1][j] + product * values[i + 1];
                for (int k = i + 2; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + product * values[k]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
