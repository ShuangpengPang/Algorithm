package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2257CountUnguardedCellsInTheGrid（统计网格图中没有被保卫的格子数）
 * @date 2023/11/19 5:04 PM
 */
public class Problem2257CountUnguardedCellsInTheGrid {

    static int[] dirs = {-1, 0, 1, 0, -1};
    int ans = 0;

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] w : walls) {
            grid[w[0]][w[1]] = 1;
        }
        for (int[] g : guards) {
            grid[g[0]][g[1]] = 2;
        }
        ans = m * n - guards.length - walls.length;
        for (int[] g : guards) {
            check(grid, g[0], g[1]);
        }
        return ans;
    }

    private void check(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        for (int d = 0; d < 4; d++) {
            for (int x = i + dirs[d], y = j + dirs[d + 1]; x >= 0 && x < m && y >= 0 && y < n && (grid[x][y] == 0 || grid[x][y] == 3); x += dirs[d], y += dirs[d + 1]) {
                if (grid[x][y] == 0) {
                    grid[x][y] = 3;
                    ans--;
                }
            }
        }
    }
}
