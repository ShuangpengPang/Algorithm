package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.List;

public class Problem1034ColoringABorder {

    public int[][] colorBorder0(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        if (grid[row][col] == color) {
            return grid;
        }
        int origin = grid[row][col];
        dfs(grid, row, col, origin);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == -2) {
                    grid[i][j] = color;
                } else if (grid[i][j] == -1) {
                    grid[i][j] = origin;
                }
            }
        }
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, int origin) {
        int m = grid.length, n = grid[0].length;
        if (check(grid, i, j, origin)) {
            grid[i][j] = -2;
        } else {
            grid[i][j] = -1;
        }
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int k = 0; k < dirs.length; ++k) {
            int x = i + dirs[k][0], y = j + dirs[k][1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == origin) {
                dfs(grid, x, y, origin);
            }
        }
    }

    private boolean check(int[][] grid, int i, int j, int origin) {
        int m = grid.length, n = grid[0].length;
        if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
            return true;
        }
        if (grid[i - 1][j] > 0 && grid[i - 1][j] != origin) {
            return true;
        }
        if (grid[i][j + 1] > 0 && grid[i][j + 1] != origin) {
            return true;
        }
        if (grid[i + 1][j] > 0 && grid[i + 1][j] != origin) {
            return true;
        }
        if (grid[i][j - 1] > 0 && grid[i][j - 1] != origin) {
            return true;
        }
        return false;
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (grid[row][col] == color) {
            return grid;
        }
        int m = grid.length, n = grid[0].length;
        List<int[]> borders = new ArrayList<>();
        dfs(grid, row, col, grid[row][col], new boolean[m][n], borders);
        for (int[] border : borders) {
            grid[border[0]][border[1]] = color;
        }
        return grid;
    }

    private void dfs(int[][] grid, int i, int j, int origin, boolean[][] visited, List<int[]> ans) {
        int m = grid.length, n = grid[0].length;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        boolean isBorder = false;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != origin) {
                isBorder = true;
                continue;
            }
            if (!visited[x][y]) {
                visited[x][y] = true;
                dfs(grid, x, y, origin, visited, ans);
            }
        }
        if (isBorder) {
            ans.add(new int[]{i, j});
        }
    }
}
