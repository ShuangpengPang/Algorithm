package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2577MinimumTimeToVVisitACellInAGrid（在网格图中访问一个格子的最少时间）
 * @date 2024/1/29 2:37 PM
 */
public class Problem2577MinimumTimeToVVisitACellInAGrid {

    private int[] dirs = {-1, 0, 1, 0, -1};

    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int[][] time = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        time[0][0] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1], t = time[x][y] + 1;
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d], ny = y + dirs[d + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (t >= grid[nx][ny]) {
                        if (time[nx][ny] > t) {
                            time[nx][ny] = t;
                            q.offer(new int[]{nx, ny});
                        }
                    } else if (((t ^ grid[nx][ny]) & 1) == 0 && time[nx][ny] > grid[nx][ny]) {
                        time[nx][ny] = grid[nx][ny];
                        q.offer(new int[]{nx, ny});
                    } else if (((t ^ grid[nx][ny]) & 1) == 1 && time[nx][ny] > grid[nx][ny] + 1) {
                        time[nx][ny] = grid[nx][ny] + 1;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return time[m - 1][n - 1];
    }
}

class Problem2577MinimumTimeToVVisitACellInAGrid0 {

    private int[] dirs = {-1, 0, 1, 0, -1};

    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int[][] time = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0});
        time[0][0] = 0;
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[0], y = p[1];
            if (time[x][y] < p[2]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                return p[2];
            }
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d], ny = y + dirs[d + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int t = Math.max(p[2] + 1, grid[nx][ny]);
                    t += (t - nx - ny) & 1;
                    if (t < time[nx][ny]) {
                        time[nx][ny] = t;
                        pq.offer(new int[]{nx, ny, t});
                    }
                }
            }
        }
        return time[m - 1][n - 1];
    }
}

class Problem2577MinimumTimeToVVisitACellInAGrid1 {

    private int[] dirs = {-1, 0, 1, 0, -1};

    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        int m = grid.length, n = grid[0].length, maxTime = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxTime = Math.max(maxTime, grid[i][j]);
            }
        }
        int left = grid[m - 1][n - 1], right = maxTime + m * n;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(grid, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left + ((left - m - n) & 1);
    }

    private boolean check(int[][] grid, int step) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{m - 1, n - 1});
        visited[m - 1][n - 1] = true;
        while (!q.isEmpty()) {
            step--;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int x = p[0], y = p[1];
                if (x == 0 && y == 0) {
                    return true;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && step >= grid[nx][ny]) {
                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }
}
