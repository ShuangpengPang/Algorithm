package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1254NumberOfClosedIslands（统计封闭岛屿的数目）
 * @date 2023/6/15 12:06 PM
 */
public class Problem1254NumberOfClosedIslands {

    static final int[] dirs = {-1, 0, 1, 0, -1};

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visited[i][j] && dfs(grid, i, j, visited)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x == 0 || x == m -1 || y == 0 || y == n - 1) {
            return false;
        }
        visited[x][y] = true;
        boolean ans = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (grid[nx][ny] == 0 && !visited[nx][ny]) {
                if (!dfs(grid, nx, ny, visited)) {
                    ans = false;
                }
            }
        }
        return ans;
    }
}
