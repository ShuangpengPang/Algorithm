package com.shuangpeng.Problem.p0901_1000;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem0934ShortestBridge（最短的桥）
 * @Date 2022/10/25 10:01 AM
 * @Version 1.0
 */
public class Problem0934ShortestBridge {

    static int[] dirs = {-1, 0, 1, 0, -1};

    public int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] arr = findIsland(grid);
        int a = arr[0], b = arr[1];
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[m * n];
        dfs(grid, a, b, q, visited);
        int ans = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int h = q.poll(), x = h / n, y = h % n;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1], code = nx * n + ny;
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[code]) {
                        continue;
                    }
                    if (grid[nx][ny] == 1) {
                        return ans;
                    }
                    visited[code] = true;
                    q.add(code);
                }
            }
            ans++;
        }
        return ans;
    }

    private int[] findIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    private void dfs(int[][] grid, int x, int y, Queue<Integer> q, boolean[] visited) {
        int m = grid.length, n = grid[0].length;
        int h = x * n + y;
        visited[h] = true;
        boolean isBorder = false;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (grid[nx][ny] == 0) {
                isBorder = true;
            } else if (!visited[nx * n + ny]) {
                dfs(grid, nx, ny, q, visited);
            }
        }
        if (isBorder) {
            q.add(x * n + y);
        }
    }
}
