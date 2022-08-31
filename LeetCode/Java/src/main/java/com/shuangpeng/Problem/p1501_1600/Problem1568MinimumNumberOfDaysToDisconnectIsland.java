package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1568MinimumNumberOfDaysToDisconnectIsland（使陆地分离的最少天数）
 * @Date 2022/8/31 4:25 PM
 * @Version 1.0
 */
public class Problem1568MinimumNumberOfDaysToDisconnectIsland {

    public int minDays(int[][] grid) {
        if (check(grid)) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (check(grid)) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private boolean check(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (cnt > 1) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                    if (cnt > 1) {
                        break;
                    }
                    dfs(grid, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 1;
                }
            }
        }
        return cnt != 1;
    }

    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        grid[i][j] = 2;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
                continue;
            }
            dfs(grid, x, y);
        }
    }
}
