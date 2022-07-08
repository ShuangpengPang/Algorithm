package com.shuangpeng.competition.第300场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2328NumberOfIncreasingPathsInAGrid（网格图中递增路径的数目）
 * @Date 2022/7/8 12:02 PM
 * @Version 1.0
 */
public class Problem2328NumberOfIncreasingPathsInAGrid {

    // 比赛时写法
    public int countPaths(int[][] grid) {
        int M = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        long ans = 0;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += dfs(grid, memo, i, j);
            }
        }
        return (int) (ans % M);
    }

    private int dfs(int[][] grid, int[][] memo, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int M = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        long ans = 1;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]) {
                ans += dfs(grid, memo, x, y);
            }
        }
        memo[i][j] = (int) (ans % M);
        return memo[i][j];
    }
}

class Problem2328NumberOfIncreasingPathsInAGrid0 {

    public int countPaths(int[][] grid) {
        int M = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        long ans = 0;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += dfs(grid, memo, i, j);
            }
        }
        return (int) (ans % M);
    }

    private int dfs(int[][] grid, int[][] memo, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int M = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        long ans = 1;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > grid[i][j]) {
                ans += dfs(grid, memo, x, y);
            }
        }
        memo[i][j] = (int) (ans % M);
        return memo[i][j];
    }
}
