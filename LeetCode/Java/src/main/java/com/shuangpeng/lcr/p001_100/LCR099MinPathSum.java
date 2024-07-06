package com.shuangpeng.lcr.p001_100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR099MinPathSum
 * @date 2024/7/6 11:13 AM
 */
public class LCR099MinPathSum {

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = grid[0][i] + dp[i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }
}

class LCR099MinPathSum0 {

    int[][] memo, grid;
    int m, n;
    static int N = Integer.MAX_VALUE;

    public int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        memo = new int[m][n];
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], N);
        }
        memo[0][0] = grid[0][0];
        return dfs(m - 1, n - 1);
    }

    private int dfs(int i, int j) {
        if (memo[i][j] != N) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(j > 0 ? dfs(i, j - 1) : N, i > 0 ? dfs(i - 1, j) : N) + grid[i][j];
    }
}
