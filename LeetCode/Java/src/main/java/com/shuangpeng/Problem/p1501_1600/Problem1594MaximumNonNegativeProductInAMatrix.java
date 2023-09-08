package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1594MaximumNonNegativeProductInAMatrix（矩阵的最大非负积）
 * @date 2023/9/8 5:23 PM
 */
public class Problem1594MaximumNonNegativeProductInAMatrix {

    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] dp = new long[n][2];
        long N = Long.MAX_VALUE >> 2;
        dp[0][0] = grid[0][0] >= 0 ? grid[0][0] : -N;
        dp[0][1] = grid[0][0] <= 0 ? grid[0][0] : N;
        for (int i = 1; i < n; i++) {
            dp[i][0] = -N;
            dp[i][1] = N;
            if (grid[0][i] == 0) {
                dp[i][0] = dp[i][1] = 0;
            } else if (grid[0][i] > 0) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][0] * grid[0][i]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] * grid[0][i]);
            } else {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] * grid[0][i]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] * grid[0][i]);
            }
        }
        for (int i = 1; i < m; i++) {
            long m1 = dp[0][0], m2 = dp[0][1];
            if (grid[i][0] == 0) {
                dp[0][0] = dp[0][1] = 0;
            } else if (grid[i][0] > 0) {
                dp[0][0] = Math.max(-N, m1 * grid[i][0]);
                dp[0][1] = Math.min(N, m2 * grid[i][0]);
            } else {
                dp[0][0] = Math.max(-N, m2 * grid[i][0]);
                dp[0][1] = Math.min(N, m1 * grid[i][0]);
            }
            for (int j = 1; j < n; j++) {
                long t1 = Math.max(dp[j][0], dp[j - 1][0]), t2 = Math.min(dp[j][1], dp[j - 1][1]);
                if (grid[i][j] == 0) {
                    dp[j][0] = dp[j][1] = 0;
                } else if (grid[i][j] > 0) {
                    dp[j][0] = Math.max(-N, t1 * grid[i][j]);
                    dp[j][1] = Math.min(N, t2 * grid[i][j]);
                } else {
                    dp[j][0] = Math.max(-N, t2 * grid[i][j]);
                    dp[j][1] = Math.min(N, t1 * grid[i][j]);
                }
            }
        }
        return dp[n - 1][0] == -N ? -1 : (int) (dp[n - 1][0] % ((long) 1e9 + 7));
    }
}
