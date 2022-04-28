package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0980UniquePathsIII
 * @Date 2022/4/28 11:29 AM
 * @Version 1.0
 */
public class Problem0980UniquePathsIII {

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int sx = 0, sy = 0, tx = 0, ty = 0, count = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int num = grid[i][j];
                if (num != -1) {
                    if (num == 1) {
                        sx = i;
                        sy = j;
                    } else if (num == 2) {
                        tx = i;
                        ty = j;
                    }
                    ++count;
                }
            }
        }
        int[] ans = new int[1];
        dfs(grid, sx, sy, tx, ty, count - 1, new boolean[m][n], ans);
        return ans[0];
    }

    private void dfs(int[][] grid, int x, int y, int tx, int ty, int remain, boolean[][] visited, int[] p) {
        if (remain == 0) {
            ++p[0];
            return;
        }
        if (x == tx && y == ty) {
            return;
        }
        visited[x][y] = true;
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int a = x + dir[0], b = y + dir[1];
            if (a >= 0 && a < m && b >= 0 && b < n && !visited[a][b] && grid[a][b] != -1) {
                dfs(grid, a, b, tx, ty, remain - 1, visited, p);
            }
        }
        visited[x][y] = false;
    }
}
