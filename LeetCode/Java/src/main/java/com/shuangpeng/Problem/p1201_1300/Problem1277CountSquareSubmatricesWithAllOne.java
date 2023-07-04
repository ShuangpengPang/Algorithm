package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1277CountSquareSubmatricesWithAllOne（统计全为1的正方形子矩阵）
 * @date 2023/6/19 11:58 AM
 */
public class Problem1277CountSquareSubmatricesWithAllOne {

    public int countSquares0(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n + 1];
        int leftTop = 0, ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int nextLeftTop = dp[j + 1];
                dp[j + 1] = matrix[i][j] == 0 ? 0 : Math.min(leftTop, Math.min(dp[j], nextLeftTop)) + 1;
                ans += dp[j + 1];
                leftTop = nextLeftTop;
            }
        }
        return ans;
    }

    public int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0, p = 0; j < n; j++) {
                int c = 0;
                if (matrix[i][j] == 1) {
                    c = (j == 0 ? 0 : Math.min(dp[j - 1], Math.min(p, dp[j]))) + 1;
                    ans += c;
                }
                p = dp[j];
                dp[j] = c;
            }
        }
        return ans;
    }
}
