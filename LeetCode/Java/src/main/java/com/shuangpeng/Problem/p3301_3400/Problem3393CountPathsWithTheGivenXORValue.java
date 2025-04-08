package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3393CountPathsWithTheGivenXORValue（统计异或值为给定值的路径数目）
 * @date 2025/4/8 14:56
 */
public class Problem3393CountPathsWithTheGivenXORValue {

    public int countPathsWithXorValue(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, N = (int) 1e9 + 7;
        int[][] dp = new int[n][16];
        for (int i = 0, x = 0; i < n; i++) {
            x ^= grid[0][i];
            dp[i][x] = 1;
        }
        for (int i = 1; i < m; i++) {
            int[][] t = new int[n][16];
            for (int j = 0; j < n; j++) {
                for (int v = 0; v < 16; v++) {
                    t[j][v] = (t[j][v] + dp[j][v ^ grid[i][j]]) % N;
                    if (j > 0) {
                        t[j][v] = (t[j][v] + t[j - 1][v ^ grid[i][j]]) % N;
                    }
                }
            }
            dp = t;
        }
        return dp[n - 1][k];
    }
}
