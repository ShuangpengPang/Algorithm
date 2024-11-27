package com.shuangpeng.Problem.p3201_3300;

import java.util.ArrayDeque;
import java.util.Deque;
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

class Problem3286FindASafeWalkThroughAGrid0 {

    private int[] dirs = {-1, 0, 1, 0, -1};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size(), N = m * n;
        if (grid.get(0).get(0) >= health) {
            return false;
        }
        Deque<int[]> q = new ArrayDeque<>(N);
        q.offerLast(new int[]{0, 0, health - grid.get(0).get(0)});
        grid.get(0).set(0, N);
        while (!q.isEmpty()) {
            int[] p = q.pollFirst();
            int x = p[0], y = p[1], h = p[2];
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d], ny = y + dirs[d + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && h > grid.get(nx).get(ny)) {
                    List<Integer> list = grid.get(nx);
                    if (list.get(ny) == 0) {
                        q.offerFirst(new int[]{nx, ny, h});
                    } else {
                        q.offerLast(new int[]{nx, ny, h - 1});
                    }
                    list.set(ny, N);
                }
            }
        }
        return false;
    }
}
