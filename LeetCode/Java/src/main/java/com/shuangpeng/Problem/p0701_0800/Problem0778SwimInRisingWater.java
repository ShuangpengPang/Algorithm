package com.shuangpeng.Problem.p0701_0800;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem0778SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a[0]][a[1]]));
        pq.offer(new int[]{0, 0});
        visited[0][0] = true;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] location = pq.poll();
            int i = location[0], j = location[1];
            ans = Math.max(ans, grid[i][j]);
            if (i == n - 1 && j == n - 1) {
                break;
            }
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                    visited[x][y] = true;
                    pq.offer(new int[]{x, y});
                }
            }
        }
        return ans;
    }
}
