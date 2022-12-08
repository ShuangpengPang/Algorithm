package com.shuangpeng.Problem.P2201_2300;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2258EscapeTheSpreadingFire（逃离火灾）
 * @date 2022/12/7 6:29 PM
 */
public class Problem2258EscapeTheSpreadingFire {

    static final int[] dirs = {-1, 0, 1, 0, -1};
    static final int N = (int) 1e9;

    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] time1 = new int[m][n], time2 = new int[m][n];
        Queue<int[]> q1 = new LinkedList<>();
        q1.offer(new int[]{0, 0});
        time1[0][0] = 1;
        calculate(grid, time1, q1);
        if (time1[m - 1][n - 1] == 0) {
            return -1;
        }
        Queue<int[]> q2 = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q2.offer(new int[]{i, j});
                    time2[i][j] = 1;
                }
            }
        }
        calculate(grid, time2, q2);
        if (time2[m - 1][n - 1] == 0) {
            return N;
        }
        if (time2[m - 1][n - 1] < time1[m - 1][n - 1]) {
            return -1;
        }
        int left = 0, right = time2[m - 1][n - 1] - time1[m - 1][n - 1];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(time1, time2, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private void calculate(int[][] grid, int[][] time, Queue<int[]> q) {
        int m = grid.length, n = grid[0].length;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int t = time[p[0]][p[1]] + 1;
                for (int d = 0; d < 4; d++) {
                    int x = p[0] + dirs[d], y = p[1] + dirs[d + 1];
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 2 || time[x][y] != 0) {
                        continue;
                    }
                    time[x][y] = t;
                    q.offer(new int[]{x, y});
                }
            }
        }
    }

    private boolean check(int[][] time1, int[][] time2, int delay) {
        if (time1[0][0] + delay >= time2[0][0]) {
            return false;
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        time1[0][0] = -time1[0][0];
        int m = time1.length, n = time1[0].length;
        boolean find = false;
        while (!q.isEmpty() && !find) {
            for (int i = q.size() - 1; i >= 0 && !find; i--) {
                int[] p = q.poll();
                int x = p[0], y = p[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1];
                    if (nx == m - 1 && ny == n - 1) {
                        find = true;
                        break;
                    }
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || time1[nx][ny] <= 0 || time1[nx][ny] + delay >= time2[nx][ny]) {
                        continue;
                    }
                    time1[nx][ny] = -time1[nx][ny];
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (time1[i][j] < 0) {
                    time1[i][j] = -time1[i][j];
                }
            }
        }
        return find;
    }
}
