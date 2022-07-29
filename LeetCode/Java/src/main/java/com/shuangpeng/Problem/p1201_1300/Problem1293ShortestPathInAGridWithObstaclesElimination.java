package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem1293ShortestPathInAGridWithObstaclesElimination（网格中的最短路径）
 * @Date 2022/7/29 5:58 PM
 * @Version 1.0
 */
public class Problem1293ShortestPathInAGridWithObstaclesElimination {

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) {
            return 0;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], -1);
        }
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = k;
        q.offer(new int[]{0, 0, k});
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            for (int c = q.size() - 1; c >= 0; c--) {
                int[] arr = q.poll();
                int i = arr[0], j = arr[1], cnt = arr[2];
                for (int d = 0; d < 4; d++) {
                    int x = i + dirs[d], y = j + dirs[d + 1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if (x == m - 1 && y == n - 1) {
                        return step;
                    }
                    if (grid[x][y] == 0 && cnt > visited[x][y]) {
                        visited[x][y] = cnt;
                        q.offer(new int[]{x, y, cnt});
                    } else if (grid[x][y] == 1 && cnt - 1 > visited[x][y]) {
                        visited[x][y] = cnt - 1;
                        q.offer(new int[]{x, y, cnt - 1});
                    }
                }
            }
        }
        return -1;
    }
}
