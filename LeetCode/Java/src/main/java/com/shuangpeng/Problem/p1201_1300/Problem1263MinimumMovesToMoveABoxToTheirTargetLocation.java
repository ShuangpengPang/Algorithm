package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem1263MinimumMovesToMoveABoxToTheirTargetLocation（推箱子）
 * @Date 2022/7/28 10:50 AM
 * @Version 1.0
 */
public class Problem1263MinimumMovesToMoveABoxToTheirTargetLocation {

    char[][] grid;
    int m, n;
    boolean[] pos;
    int[] dirs = {-1, 0, 1, 0, -1};

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        int px = -1, py = -1, bx = -1, by = -1, tx = -1, ty = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == 'S') {
                    px = i;
                    py = j;
                } else if (c == 'B') {
                    bx = i;
                    by = j;
                } else if (c == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }
        pos = new boolean[m * n];
        dfs(px, py, bx, by);
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][4];
        for (int d = 0; d < 4; d++) {
            int x = bx + dirs[d], y = by + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#') {
                continue;
            }
            int x1 = bx - dirs[d], y1 = by - dirs[d + 1];
            if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || !pos[x1 * n + y1]) {
                continue;
            }
            if (x == tx && y == ty) {
                return 1;
            }
            visited[x][y][d] = true;
            q.offer(new int[]{x, y, bx, by});
        }
        int step = 1;
        while (!q.isEmpty()) {
            step++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] arr = q.poll();
                Arrays.fill(pos, false);
                dfs(arr[2], arr[3], arr[0], arr[1]);
                int x = arr[0], y = arr[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == '#') {
                        continue;
                    }
                    int x1 = x - dirs[d], y1 = y - dirs[d + 1];
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= n || !pos[x1 * n + y1] || visited[nx][ny][d]) {
                        continue;
                    }
                    if (nx == tx && ny == ty) {
                        return step;
                    }
                    visited[nx][ny][d] = true;
                    q.offer(new int[]{nx, ny, x, y});
                }
            }
        }
        return -1;
    }

    private void dfs(int x, int y, int bx, int by) {
        pos[x * n + y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == '#' || pos[nx * n + ny]) {
                continue;
            }
            if (nx == bx && ny == by) {
                continue;
            }
            dfs(nx, ny, bx, by);
        }
    }
}

class Problem1263MinimumMovesToMoveABoxToTheirTargetLocation0 {

    char[][] grid;
    int m, n;
    int[] dirs = {-1, 0, 1, 0, -1};

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        int[] start = new int[4], end = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == 'B') {
                    start[0] = i;
                    start[1] = j;
                } else if (c == 'S') {
                    start[2] = i;
                    start[3] = j;
                } else if (c == 'T') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        boolean[][][][] visited = new boolean[m][n][m][n];
        Queue<int[]> q = new LinkedList<>();
        visited[start[0]][start[1]][start[2]][start[3]] = true;
        q.offer(start);
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nx = x + dirs[d], ny = y + dirs[d + 1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == '#') {
                        continue;
                    }
                    int px = x - dirs[d], py = y - dirs[d + 1];
                    if (px < 0 || px >= m || py < 0 || py >= n || grid[px][py] == '#' || visited[nx][ny][x][y] || !isReachable(cur[2], cur[3], x, y, px, py, new boolean[m][n])) {
                        continue;
                    }
                    if (nx == end[0] && ny == end[1]) {
                        return step;
                    }
                    visited[nx][ny][x][y] = true;
                    q.offer(new int[]{nx, ny, x, y});
                }
            }
        }
        return -1;
    }

    private boolean isReachable(int x, int y, int bx, int by, int tx, int ty, boolean[][] visited) {
        if (x == tx && y == ty) {
            return true;
        }
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dirs[d], ny = y + dirs[d + 1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || (nx == bx && ny == by) || grid[nx][ny] == '#' || visited[nx][ny]) {
                continue;
            }
            if (isReachable(nx, ny, bx, by, tx, ty, visited)) {
                return true;
            }
        }
        return false;
    }
}
