package com.shuangpeng.Problem.p1201_1300;

import java.util.*;

/**
 * @Description: Problem1210MinimumMovesToReachTargetWithRotations（穿过迷宫的最少移动次数）
 * @Date 2022/7/23 6:05 PM
 * @Version 1.0
 */
public class Problem1210MinimumMovesToReachTargetWithRotations {

    public int minimumMoves0(int[][] grid) {
        int n = grid.length;
        if (grid[n - 1][n - 1] == 1 || grid[n - 1][n - 2] == 1) {
            return -1;
        }
        int ans = dfs(grid, 1 << 10, 0, new HashMap<>());
        return ans == Integer.MAX_VALUE >> 1 ? -1 : ans;
    }

    private int dfs(int[][] grid, int state, int dir, Map<Integer, Integer> memo) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        int M = 0x3ff;
        int x1 = state & M, y1 = (state >> 10) & M, d = state >> 20, n = grid.length;
        if (x1 == n - 1 && y1 == n - 1 && d == 0) {
            return 0;
        }
        int INF = Integer.MAX_VALUE >> 1;
        int ans = INF;
        int x2 = d == 0 ? x1 : x1 - 1, y2 = d == 0 ? y1 - 1 : y1;
        if (y1 + 1 < n && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0) {
            ans = Math.min(ans, 1 + dfs(grid, toHash(x1, y1 + 1, d), d, memo));
        }
        if (x1 + 1 < n && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0) {
            ans = Math.min(ans, 1 + dfs(grid, toHash(x1 + 1, y1, d), d, memo));
        }
        if (d == dir) {
            int nx = d == 0 ? x1 + 1 : x1 - 1, ny = d == 0 ? y1 - 1 : y1 + 1;
            int j = d == 0 ? nx : nx + 1, k = d == 0 ? ny + 1 : ny;
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0 && grid[j][k] == 0) {
                ans = Math.min(ans, 1 + dfs(grid, toHash(nx, ny, 1 - d), d, memo));
            }
        }
        memo.put(state, ans);
        return ans;
    }

    private int toHash(int x, int y, int d) {
        return d << 20 | y << 10 | x;
    }

    public int minimumMoves1(int[][] grid) {
        int n = grid.length;
        if (grid[n - 1][n - 2] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        int step = 0;
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int[] target = {n - 1, n - 1, n - 1, n - 2, 0};
        q.offer(new int[]{0, 1, 0, 0, 0});
        visited.add(getHash(0, 1, 0));
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] arr = q.poll();
                if (Arrays.equals(arr, target)) {
                    return step;
                }
                int x1 = arr[0], y1 = arr[1], x2 = arr[2], y2 = arr[3], d = arr[4];
                if (y1 + 1 < n && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0 && !visited.contains(getHash(x1, y1 + 1, d))) {
                    visited.add(getHash(x1, y1 + 1, d));
                    q.offer(new int[]{x1, y1 + 1, x2, y2 + 1, d});
                }
                if (x1 + 1 < n && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0 && !visited.contains(getHash(x1 + 1, y1, d))) {
                    visited.add(getHash(x1 + 1, y1, d));
                    q.offer(new int[]{x1 + 1, y1, x2 + 1, y2, d});
                }
                int nx1 = d == 0 ? x1 + 1 : x1 - 1, ny1 = d == 0 ? y1 - 1 : y1 + 1;
                int nx2 = d == 0 ? nx1 : x1, ny2 = d == 0 ? y1 : ny1;
                if (nx1 < n && ny1 < n && grid[nx1][ny1] == 0 && grid[nx2][ny2] == 0 && !visited.contains(getHash(nx1, ny1, 1 - d))) {
                    visited.add(getHash(nx1, ny1, 1 - d));
                    q.offer(new int[]{nx1, ny1, x2, y2, 1 - d});
                }
            }
            step++;
        }
        return -1;
    }

    private int getHash(int x, int y, int d) {
        return x | y << 10 | d << 20;
    }

    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        if (grid[n - 1][n - 1] == 1 || grid[n - 1][n - 2] == 1) {
            return -1;
        }
        int[][] h = new int[n][n], v = new int[n][n];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Arrays.fill(h[i], INF);
            Arrays.fill(v[i], INF);
        }
        dfs(grid, h, v, 0, 0, 0, 1, 0);
        return h[n - 1][n - 1] != INF ? h[n - 1][n - 1] : -1;
    }

    private void dfs(int[][] grid, int[][] h, int[][] v, int x1, int y1, int x2, int y2, int count) {
        if (x1 == x2 && h[x2][y2] <= count || y1 == y2 && v[x2][y2] <= count) {
            return;
        }
        if (x1 == x2) {
            h[x2][y2] = count;
        } else {
            v[x2][y2] = count;
        }
        int n = grid.length;
        if (y2 + 1 < n && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0) {
            dfs(grid, h, v, x1, y1 + 1, x2, y2 + 1, count + 1);
        }
        if (x2 + 1 < n && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0) {
            dfs(grid, h, v, x1 + 1, y1, x2 + 1, y2, count + 1);
        }
        if (x1 == x2 && x2 + 1 < n && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0) {
            dfs(grid, h, v, x1, y1, x1 + 1, y1, count + 1);
        }
        if (y1 == y2 && y2 + 1 < n && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0) {
            dfs(grid, h, v, x1, y1, x1, y1 + 1, count + 1);
        }
    }
}

class Problem1210MinimumMovesToReachTargetWithRotations0 {

    public int minimumMoves(int[][] grid) {
        int n = grid.length;
        if (grid[n - 1][n - 2] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        boolean[][][] visited = new boolean[n][n][2];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 1, 0});
        visited[0][1][0] = true;
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int x = p[0], y = p[1], d = p[2];
                if (d == 0 && x == n - 1 && y == n - 1) {
                    return step;
                }
                if (d == 0) {
                    if (y + 1 < n && !visited[x][y + 1][0] && grid[x][y + 1] == 0) {
                        visited[x][y + 1][0] = true;
                        q.offer(new int[]{x, y + 1, 0});
                    }
                    if (x + 1 < n && !visited[x + 1][y][0] && grid[x + 1][y] == 0 && grid[x + 1][y - 1] == 0) {
                        visited[x + 1][y][0] = true;
                        q.offer(new int[]{x + 1, y, 0});
                    }
                    if (x + 1 < n && !visited[x + 1][y - 1][1] && grid[x + 1][y - 1] == 0 && grid[x + 1][y] == 0) {
                        visited[x + 1][y - 1][1] = true;
                        q.offer(new int[]{x + 1, y - 1, 1});
                    }
                } else if (d == 1) {
                    if (x + 1 < n && !visited[x + 1][y][1] && grid[x + 1][y] == 0) {
                        visited[x + 1][y][1] = true;
                        q.offer(new int[]{x + 1, y, 1});
                    }
                    if (y + 1 < n && !visited[x][y + 1][1] && grid[x][y + 1] == 0 && grid[x - 1][y + 1] == 0) {
                        visited[x][y + 1][1] = true;
                        q.offer(new int[]{x, y + 1, 1});
                    }
                    if (y + 1 < n && !visited[x - 1][y + 1][0] && grid[x - 1][y + 1] == 0 && grid[x][y + 1] == 0) {
                        visited[x - 1][y + 1][0] = true;
                        q.offer(new int[]{x - 1, y + 1, 0});
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
