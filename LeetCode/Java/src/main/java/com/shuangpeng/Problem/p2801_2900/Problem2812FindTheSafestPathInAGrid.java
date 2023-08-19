package com.shuangpeng.Problem.p2801_2900;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2812FindTheSafestPathInAGrid（找出最安全路径）
 * @date 2023/8/19 6:56 PM
 */
public class Problem2812FindTheSafestPathInAGrid {

    static int[] dirs = {-1, 0, 1, 0, -1};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dis = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int s = 0;
        while (!q.isEmpty()) {
            s++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int x = p[0] + dirs[d], y = p[1] + dirs[d + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]) {
                        visited[x][y] = true;
                        q.offer(new int[]{x, y});
                        dis[x][y] = s;
                    }
                }
            }
        }
        int[][] path = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        pq.offer(new int[]{0, 0, dis[0][0]});
        path[0][0] = dis[0][0];
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            if (path[p[0]][p[1]] > p[2]) {
                continue;
            }
            if (p[0] == n - 1 && p[1] == n - 1) {
                return p[2];
            }
            for (int d = 0; d < 4; d++) {
                int x = p[0] + dirs[d], y = p[1] + dirs[d + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && path[x][y] < Math.min(p[2], dis[x][y])) {
                    path[x][y] = Math.min(p[2], dis[x][y]);
                    pq.offer(new int[]{x, y, path[x][y]});
                }
            }
        }
        return path[n - 1][n - 1];
    }
}
