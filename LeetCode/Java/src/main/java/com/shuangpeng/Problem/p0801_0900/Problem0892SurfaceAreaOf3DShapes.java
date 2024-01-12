package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0892SurfaceAreaOf3DShapes（三维形体的表面积）
 * @date 2024/1/12 11:23 PM
 */
public class Problem0892SurfaceAreaOf3DShapes {

    private static int[] dirs = {-1, 0, 1, 0, -1};

    public int surfaceArea(int[][] grid) {
        int n = grid.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                ans += grid[i][j] * 4 + 2;
                for (int d = 0; d < 4; d++) {
                    int x = i + dirs[d], y = j + dirs[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        ans -= Math.min(grid[i][j], grid[x][y]);
                    }
                }
            }
        }
        return ans;
    }
}
