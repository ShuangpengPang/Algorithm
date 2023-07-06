package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1254NumberOfClosedIslands（统计封闭岛屿的数目）
 * @date 2023/6/15 12:06 PM
 */
public class Problem1254NumberOfClosedIslands {

    static final int[] dirs = {-1, 0, 1, 0, -1};

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        boolean ans = x != 0 && x != m - 1 && y != 0 && y != n - 1;
        grid[x][y] = 2;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                ans &= dfs(grid, nx, ny);
            }
        }
        return ans;
    }
}

class Problem1254NumberOfClosedIslands0 {

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, N = m * n;
        int[] parent = new int[N];
        Arrays.setAll(parent, i -> i);
        for (int i = 1; i < m; i++) {
            for (int j = 1, idx = n * i + 1; j < n; j++, idx++) {
                if (grid[i][j] == 0) {
                    if (grid[i - 1][j] == 0) {
                        union(parent, m, n, idx, idx - n);
                    }
                    if (grid[i][j - 1] == 0) {
                        union(parent, m, n, idx, idx - 1);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1, idx = i * n + 1; j < n - 1; j++, idx++) {
                if (grid[i][j] == 0 && parent[idx] == idx) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int m, int n, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            int i = px / n, j = px % n;
            if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                parent[py] = px;
            } else {
                parent[px] = py;
            }
        }
    }
}
