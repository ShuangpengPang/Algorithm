package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1391CheckIfThereIsAValidPathInAGrid（检查网格中是否存在有效路径）
 * @date 2023/8/17 6:16 PM
 */
public class Problem1391CheckIfThereIsAValidPathInAGrid {

    int[] dirs = {-1, 0, 1, 0, -1};
    int[][] values = {{2, 3, 4}, {1, 3, 5}, {2, 5, 6}, {1, 4, 6}};
    int[][] out = {{1, 3}, {0, 2}, {2, 3}, {1, 2}, {0, 3}, {0, 1}};

    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            if (x == m - 1 && y == n - 1) {
                return true;
            }
            for (int d : out[grid[x][y] - 1]) {
                int nx = x + dirs[d], ny = y + dirs[d + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    boolean valid = false;
                    for (int v : values[d]) {
                        if (v == grid[nx][ny]) {
                            valid = true;
                            break;
                        }
                    }
                    if (valid) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return false;
    }
}
