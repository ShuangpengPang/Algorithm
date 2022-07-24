package com.shuangpeng.Problem.p1201_1300;

import java.util.*;

/**
 * @Description: Problem1210MinimumMovesToReachTargetWithRotations（穿过迷宫的最少移动次数）
 * @Date 2022/7/23 6:05 PM
 * @Version 1.0
 */
public class Problem1210MinimumMovesToReachTargetWithRotations {

    public int minimumMoves(int[][] grid) {
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
}
