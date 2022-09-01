package com.shuangpeng.competition.第290到300场周赛.第295场周赛;

import java.util.*;

/**
 * @Description: Problem2290MinimumObstacleRemovalToReachCorner（到达角落需要移除障碍物的最小数目）
 * @Date 2022/6/13 11:26 PM
 * @Version 1.0
 */
public class Problem2290MinimumObstacleRemovalToReachCorner {

    private int m, n;
    private static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] grid;
    private int[][] removes;
    private Queue<int[]> queue;
    private boolean find = false;

    public int minimumObstacles(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        find = false;
        removes = new int[m][n];
        queue = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            Arrays.fill(removes[i], Integer.MAX_VALUE);
        }
        removes[0][0] = 0;
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty() && !find) {
            int[] pair = queue.poll();
            dfs(pair[0], pair[1]);
        }
        return removes[m - 1][n - 1];
    }

    private void dfs(int x, int y) {
        int count = removes[x][y];
        for (int[] dir : dirs) {
            if (find) {
                return;
            }
            int i = x + dir[0], j = y + dir[1];
            if (i >= 0 && i < m && j >= 0 && j < n) {
                if (grid[i][j] == 0) {
                    if (removes[i][j] > count) {
                        removes[i][j] = count;
                        if (i == m - 1 && j == n - 1) {
                            find = true;
                            return;
                        }
                        dfs(i, j);
                    }
                } else {
                    if (removes[i][j] > count + 1) {
                        removes[i][j] = count + 1;
                        queue.offer(new int[]{i, j});
                    }
                }
            }
        }
    }
}

class Problem2290MinimumObstacleRemovalToReachCorner0 {

    static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, 0});
        dis[0][0] = 0;
        while (!q.isEmpty() && dis[m - 1][n - 1] == Integer.MAX_VALUE) {
            int[] p = q.pollFirst();
            int x = p[0], y = p[1];
            int count = dis[x][y];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && dis[nx][ny] == Integer.MAX_VALUE) {
                    if (grid[nx][ny] == 0) {
                        dis[nx][ny] = count;
                        q.addFirst(new int[]{nx, ny});
                    } else {
                        dis[nx][ny] = count + 1;
                        q.addLast(new int[]{nx, ny});
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}

class Problem2290MinimumObstacleRemovalToReachCorner1 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        dis[0][0] = 0;
        q.addFirst(new int[]{0, 0});
        while (dis[m - 1][n - 1] == Integer.MAX_VALUE) {
            int[] p = q.pollFirst();
            int x = p[0], y = p[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    if (grid[nx][ny] == 0 && dis[nx][ny] == Integer.MAX_VALUE) {
                        q.addFirst(new int[]{nx, ny});
                        dis[nx][ny] = dis[x][y];
                    } else if (grid[nx][ny] == 1 && dis[nx][ny] == Integer.MAX_VALUE){
                        q.addLast(new int[]{nx, ny});
                        dis[nx][ny] = dis[x][y] + 1;
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }
}
