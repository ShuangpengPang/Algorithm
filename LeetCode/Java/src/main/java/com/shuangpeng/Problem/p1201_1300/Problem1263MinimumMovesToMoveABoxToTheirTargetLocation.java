package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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

class Problem1263MinimumMovesToMoveABoxToTheirTargetLocation1 {

    char[][] grid;
    int m, n, tx, ty;
    int[] dirs = {-1, 0, 1, 0, -1};

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int px = 0, py = 0, bx = 0, by = 0;
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
        List<int[]> neighbors = new ArrayList<>();
        findNeighbor(px, py, bx, by, new boolean[m][n], neighbors);
        return bfs(neighbors);
    }

    private void findNeighbor(int px, int py, int bx, int by, boolean[][] visited, List<int[]> ans) {
        visited[px][py] = true;
        boolean isNeighbor = false;
        for (int d = 0; d < 4; d++) {
            int x = px + dirs[d], y = py + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#' || visited[x][y]) {
                continue;
            }
            if (x == bx && y == by) {
                isNeighbor = true;
                continue;
            }
            findNeighbor(x, y, bx, by, visited, ans);
        }
        if (isNeighbor) {
            ans.add(new int[]{px, py, bx, by});
        }
    }

    private int bfs(List<int[]> neighbors) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[m][n][m][n];
        for (int[] p : neighbors) {
            int px = p[0], py = p[1], bx = p[2], by = p[3];
            visited[px][py][bx][by] = true;
            q.add(p);
        }
        int ans = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int px = p[0], py = p[1], bx = p[2], by = p[3];
                if (bx == tx && by == ty) {
                    return ans;
                }
                int x = bx, y = by;
                bx += bx - px;
                by += by - py;
                if (bx < 0 || bx >= m || by < 0 || by >= n || grid[bx][by] == '#') {
                    continue;
                }
                List<int[]> list = new ArrayList<>();
                findNeighbor(x, y, bx, by, new boolean[m][n], list);
                for (int[] arr : list) {
                    int nx = arr[0], ny = arr[1], nbx = arr[2], nby = arr[3];
                    if (!visited[nx][ny][nbx][nby]) {
                        q.add(arr);
                        visited[nx][ny][nbx][nby] = true;
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}

class Problem1263MinimumMovesToMoveABoxToTheirTargetLocation2 {

    char[][] grid;
    static int INF = Integer.MAX_VALUE >> 1;
    int m, n, tx, ty, ans;
    int[] dirs = {-1, 0, 1, 0, -1};
    int[][][][] memo;

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int px = 0, py = 0, bx = 0, by = 0;
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
        List<int[]> neighbors = new ArrayList<>();
        findNeighbor(px, py, bx, by, new boolean[m][n], neighbors);
        memo = new int[m][n][m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < m; x++) {
                    for (int y = 0; y < n; y++) {
                        memo[i][j][x][y] = INF;
                    }
                }
            }
        }
        ans = INF;
        for (int[] p : neighbors) {
            dfs(p[0], p[1], bx, by, 0);
        }
        return ans == INF ? -1 : ans;
    }

    private void findNeighbor(int px, int py, int bx, int by, boolean[][] visited, List<int[]> ans) {
        visited[px][py] = true;
        boolean isNeighbor = false;
        for (int d = 0; d < 4; d++) {
            int x = px + dirs[d], y = py + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#' || visited[x][y]) {
                continue;
            }
            if (x == bx && y == by) {
                isNeighbor = true;
                continue;
            }
            findNeighbor(x, y, bx, by, visited, ans);
        }
        if (isNeighbor) {
            ans.add(new int[]{px, py});
        }
    }

    private void dfs(int px, int py, int bx, int by, int count) {
        if (count >= ans || count >= memo[px][py][bx][by]) {
            return;
        }
        if (bx == tx && by == ty) {
            ans = count;
            return;
        }
        memo[px][py][bx][by] = count;
        int x = bx, y = by;
        bx += bx - px;
        by += by - py;
        if (bx < 0 || bx >= m || by < 0 || by >= n || grid[bx][by] == '#') {
            return;
        }
        List<int[]> neighbors = new ArrayList<>();
        findNeighbor(x, y, bx, by, new boolean[m][n], neighbors);
        for (int[] p : neighbors) {
            dfs(p[0], p[1], bx, by, count + 1);
        }
    }
}

class Problem1263MinimumMovesToMoveABoxToTheirTargetLocation3 {

    char[][] grid;
    int m, n, tx, ty;
    int[] dirs = {-1, 0, 1, 0, -1};

    public int minPushBox(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int px = 0, py = 0, bx = 0, by = 0;
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
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[m][n][m][n];
        visited[px][py][bx][by] = true;
        q.addLast(new int[]{px, py, bx, by, 0});
        return bfs(q, visited);
    }

    private int bfs(Deque<int[]> q, boolean[][][][] visited) {
        while (!q.isEmpty()) {
            int[] p = q.pollFirst();
            int px = p[0], py = p[1], bx = p[2], by = p[3], c = p[4];
            if (bx == tx && by == ty) {
                return c;
            }
            for (int d = 0; d < 4; d++) {
                int dx = dirs[d], dy = dirs[d + 1], x = px + dx, y = py + dy, cx = bx, cy = by;
                if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#') {
                    continue;
                }
                if (x == cx && y == cy) {
                    cx += dx;
                    cy += dy;
                    if (cx < 0 || cx >= m || cy < 0 || cy >= n || grid[cx][cy] == '#' || visited[x][y][cx][cy]) {
                        continue;
                    }
                    visited[x][y][cx][cy] = true;
                    q.addLast(new int[]{x, y, cx, cy, c + 1});
                } else if (!visited[x][y][cx][cy]) {
                    visited[x][y][cx][cy] = true;
                    q.addFirst(new int[]{x, y, cx, cy, c});
                }
            }
        }
        return -1;
    }
}
