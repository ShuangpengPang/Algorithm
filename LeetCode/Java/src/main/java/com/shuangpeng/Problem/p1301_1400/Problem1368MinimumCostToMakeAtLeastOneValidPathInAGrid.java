package com.shuangpeng.Problem.p1301_1400;

import java.util.*;

/**
 * @Description: Problem1368MinimumCostToMakeAtLeastOneValidPathInAGrid（使网格图中至少有一条有效路径的最小代价）
 * @Date 2022/8/9 4:00 PM
 * @Version 1.0
 */
public class Problem1368MinimumCostToMakeAtLeastOneValidPathInAGrid {

    public int minCost0(int[][] grid) {
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

    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        int INF = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], INF);
        }
        dis[0][0] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, 0, 0});
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int[] arr = q.pollFirst();
            int x = arr[0], y = arr[1], d = arr[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0], ny = y + dirs[i][1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                int c = d + (i == grid[x][y] - 1 ? 0 : 1);
                if (c >= dis[nx][ny]) {
                    continue;
                }
                dis[nx][ny] = c;
                int[] tuple = {nx, ny, c};
                if (c == d) {
                    q.addFirst(tuple);
                } else {
                    q.addLast(tuple);
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}

class Problem1368MinimumCostToMakeAtLeastOneValidPathInAGrid0 {

    int[][] grid;
    int m, n;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public int minCost(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.visited = new boolean[m][n];
        Queue<Integer> q = new LinkedList<>();
        process(q, 0, 0);
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int h = q.poll();
                int x = h / n, y = h % n;
                if (x == m - 1 && y == n - 1) {
                    return step;
                }
                for (int d = 0; d < 4; d++) {
                    if (d == grid[x][y] - 1) {
                        continue;
                    }
                    int nx = x + dirs[d][0], ny = y + dirs[d][1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) {
                        continue;
                    }
                    process(q, nx, ny);
                }
            }
            step++;
        }
        return step;
    }

    private int toHash(int x, int y) {
        return x * n + y;
    }

    private void process(Queue<Integer> q, int x, int y) {
        while (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
            visited[x][y] = true;
            q.offer(toHash(x, y));
            int d = grid[x][y] - 1;
            x += dirs[d][0];
            y += dirs[d][1];
        }
    }
}