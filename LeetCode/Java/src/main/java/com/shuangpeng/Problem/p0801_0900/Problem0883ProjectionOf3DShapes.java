package com.shuangpeng.Problem.p0801_0900;

/**
 * @Description: Problem0883ProjectionOf3DShapes
 * @Date 2022/4/26 10:14 AM
 * @Version 1.0
 */
public class Problem0883ProjectionOf3DShapes {

    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int row = 0, col = 0;
            for (int j = 0; j < n; ++j) {
                row = Math.max(row, grid[i][j]);
                col = Math.max(col, grid[j][i]);
                ans += grid[i][j] > 0 ? 1 : 0;
            }
            ans += row + col;
        }
        return ans;
    }
}
