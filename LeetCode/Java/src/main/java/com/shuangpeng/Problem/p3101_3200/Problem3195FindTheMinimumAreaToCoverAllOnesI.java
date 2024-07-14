package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3195FindTheMinimumAreaToCoverAllOnesI（包含所有 1 的最小矩形面积 I）
 * @date 2024/7/14 11:25 PM
 */
public class Problem3195FindTheMinimumAreaToCoverAllOnesI {

    public int minimumArea(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int x1 = m, x2 = 0, y1 = n, y2 = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x1 = Math.min(x1, i);
                    x2 = Math.max(x2, i);
                    y1 = Math.min(y1, j);
                    y2 = Math.max(y2, j);
                }
            }
        }
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
}
