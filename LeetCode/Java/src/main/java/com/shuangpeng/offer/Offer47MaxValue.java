package com.shuangpeng.offer;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Offer47MaxValue（礼物的最大价值）
 * @date 2023/3/8 10:29 AM
 */
public class Offer47MaxValue {

    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int max = 0;
                if (i > 0) {
                    max = Math.max(max, grid[i - 1][j]);
                }
                if (j > 0) {
                    max = Math.max(max, grid[i][j - 1]);
                }
                grid[i][j] += max;
            }
        }
        return grid[m - 1][n - 1];
    }
}
