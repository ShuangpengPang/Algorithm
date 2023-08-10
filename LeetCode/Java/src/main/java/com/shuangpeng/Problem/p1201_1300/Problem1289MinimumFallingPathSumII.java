package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1289MinimumFallingPathSumII（下降路径最小和II）
 * @date 2023/8/10 10:20 AM
 */
public class Problem1289MinimumFallingPathSumII {

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (j != k) {
                        min = Math.min(min, grid[i - 1][k]);
                    }
                }
                grid[i][j] += min;
            }
        }
        return Arrays.stream(grid[n - 1]).min().getAsInt();
    }
}
