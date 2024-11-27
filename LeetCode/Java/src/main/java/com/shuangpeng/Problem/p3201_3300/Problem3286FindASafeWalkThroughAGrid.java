package com.shuangpeng.Problem.p3201_3300;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3286FindASafeWalkThroughAGrid（穿越网格图的安全路径）
 * @date 2024/11/27 6:54 PM
 */
public class Problem3286FindASafeWalkThroughAGrid {

    private int[] dirs = {-1, 0, 1, 0, -1};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            List<Integer> list = grid.get(i);
            for (int j = 0; j < n; j++) {
                arr[i][j] = list.get(j);
            }
        }
        return dfs(arr, new int[m][n], 0, 0, health);
    }

    private boolean dfs(int[][] grid, int[][] maxHealth, int x, int y, int health) {
        int m = grid.length, n = grid[0].length, N = m * n;
        if (health <= grid[m - 1][n - 1] || x == m - 1 && y == n - 1) {
            return health > grid[m - 1][n - 1];
        }
        maxHealth[x][y] = health;
        int t = grid[x][y];
        health -= t;
        grid[x][y] = N;
        boolean ans = false;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && health > maxHealth[nx][ny] && dfs(grid, maxHealth, nx, ny, health)) {
                ans = true;
                break;
            }
        }
        grid[x][y] = t;
        return ans;
    }
}
