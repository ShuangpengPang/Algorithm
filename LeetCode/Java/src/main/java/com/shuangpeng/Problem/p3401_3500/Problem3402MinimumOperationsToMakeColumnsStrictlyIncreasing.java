package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3402MinimumOperationsToMakeColumnsStrictlyIncreasing（使每一列严格递增的最少操作次数）
 * @date 2025/3/4 18:54
 */
public class Problem3402MinimumOperationsToMakeColumnsStrictlyIncreasing {

    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 1, p = grid[0][j]; i < m; i++) {
                int t = Math.max(p + 1, grid[i][j]);
                cnt += t - grid[i][j];
                p = t;
            }
        }
        return cnt;
    }
}
