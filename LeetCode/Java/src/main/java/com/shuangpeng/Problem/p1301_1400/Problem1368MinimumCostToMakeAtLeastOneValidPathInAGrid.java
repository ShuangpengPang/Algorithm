package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: Problem1368MinimumCostToMakeAtLeastOneValidPathInAGrid（使网格图中至少有一条有效路径的最小代价）
 * @Date 2022/8/9 4:00 PM
 * @Version 1.0
 */
public class Problem1368MinimumCostToMakeAtLeastOneValidPathInAGrid {

    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int INF = Integer.MAX_VALUE >> 1;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], INF);
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        dis[0][0] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        q.offer(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0], y = arr[1], d = arr[2];
            if (d > dis[x][y]) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0], ny = y + dirs[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                int c = d + (i == grid[x][y] - 1 ? 0 : 1);
                if (c < dis[nx][ny]) {
                    dis[nx][ny] = c;
                    q.offer(new int[]{nx, ny, c});
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}
