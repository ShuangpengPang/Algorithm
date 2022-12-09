package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2435PathsInMatrixWhoseSumIsDivisibleByK（矩阵中和能被K整除的路径）
 * @date 2022/12/9 5:16 PM
 */
public class Problem2435PathsInMatrixWhoseSumIsDivisibleByK {

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, M = (int) 1e9 + 7;
        int[][] dp = new int[n][k], tmp = new int[n][k];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < k; x++) {
                    int y = (x + grid[i][j]) % k;
                    tmp[j][y] = (dp[j][x] + (j > 0 ? tmp[j - 1][x] : 0)) % M;
                }
            }
            int[][] t = dp;
            dp = tmp;
            tmp = t;
        }
        return dp[n - 1][0];
    }
}
