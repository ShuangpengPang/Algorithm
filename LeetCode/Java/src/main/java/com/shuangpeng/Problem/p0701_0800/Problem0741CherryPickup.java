package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;

public class Problem0741CherryPickup {

    public int cherryPickup0(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }
        int count = dfs(grid, 0, 0, 0, memo);
        return count == -1 ? 0 : count;
    }

    private int dfs(int[][] grid, int x1, int y1, int x2, int[][][] memo) {
        int n = grid.length;
        int y2 = x1 + y1 - x2;
        if (x1 == n || y1 == n || x2 == n || y2 == n
                || grid[x1][y1] == -1 || grid[x2][y2] == -1) {
            return -1;
        }
        if (memo[x1][y1][x2] != Integer.MIN_VALUE) {
            return memo[x1][y1][x2];
        }
        if (x1 == n - 1 && y1 == n - 1) {
            return grid[x1][y1];
        }
        int count = Math.max(Math.max(dfs(grid, x1 + 1, y1, x2 + 1, memo), dfs(grid, x1, y1 + 1, x2 + 1, memo)),
                Math.max(dfs(grid, x1 + 1, y1, x2, memo), dfs(grid, x1, y1 + 1, x2, memo)));
        if (count == -1) {
            memo[x1][y1][x2] = -1;
            return -1;
        }
        count += grid[x1][y1];
        count += x1 != x2 ? grid[x2][y2] : 0;
        memo[x1][y1][x2] = count;
        return count;
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = grid[0][0];
        final int S = (n - 1) << 1;
        for (int s = 1; s <= S; s++) {
            int[][] temp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(temp[i], Integer.MIN_VALUE);
            }
            for (int i = Math.max(0, s - n + 1); i <= Math.min(s, n - 1); i++) {
                for (int j = Math.max(0, s - n + 1); j <= Math.min(s, n - 1); j++) {
                    if (grid[i][s - i] == -1 || grid[j][s - j] == -1) {
                        temp[i][j] = -1;
                        continue;
                    }
                    int count = Integer.MIN_VALUE;
                    int[][] previous = {{0, 0}, {0, -1}, {-1, 0}, {-1, -1}};
                    for (int[] delta : previous) {
                        int di = i + delta[0];
                        int dj = j + delta[1];
                        if (di < 0 || dj < 0) {
                            continue;
                        }
                        count = Math.max(count, dp[di][dj]);
                    }
                    if (count == -1 || count == Integer.MIN_VALUE) {
                        temp[i][j] = count;
                        continue;
                    }
                    count += grid[i][s - i];
                    if (i != j) {
                        count += grid[j][s - j];
                    }
                    temp[i][j] = count;
                }
            }
            dp = temp;
        }
        return Math.max(0, dp[n - 1][n - 1]);
    }

























    public int cherryPickup1(int[][] grid) {
        int n = grid.length;
        int N = (n << 1) - 1;
        int[][][] dp = new int[N][n][n];
        dp[0][0][0] = grid[0][0];
        for (int i = 1; i < N; i++) {
            for (int x1 = Math.max(0, i - n + 1); x1 <= i && x1 < n; x1++) {
                int y1 = i - x1;
                for (int x2 = Math.max(0, i - n + 1); x2 <= i && x2 < n; x2++) {
                    int y2 = i - x2;
                    if (grid[x1][y1] == -1 || grid[x2][y2] == -1) {
                        dp[i][x1][x2] = Integer.MIN_VALUE;
                        continue;
                    }
                    // dp[i - 1][x1 - 1][x2] dp[i - 1][x1][x2]   dp[i - 1][x1 - 1][x2 - 1]  dp[i - 1][x1][x2 - 1]
                    int a = 0, b = 0, c = 0, d = 0;
                    if (x1 != 0 && x2 != 0) {
                        dp[i][x1][x2] = Math.max(Math.max(dp[i - 1][x1 - 1][x2], dp[i - 1][x1][x2]), Math.max(dp[i - 1][x1 - 1][x2 - 1], dp[i - 1][x1][x2 - 1]));
                    } else if (x1 == 0 && x2 == 0) {
                        dp[i][x1][x2] = dp[i - 1][x1][x2];
                    } else if (x1 == 0) {
                        dp[i][x1][x2] = Math.max(dp[i - 1][x1][x2], dp[i - 1][x1][x2 - 1]);
                    } else if (x2 == 0) {
                        dp[i][x1][x2] = Math.max(dp[i - 1][x1][x2], dp[i - 1][x1 - 1][x2]);
                    }
                    if (dp[i][x1][x2] != Integer.MIN_VALUE) {
                        dp[i][x1][x2] += x1 == x2 ? grid[x1][y1] : grid[x1][y1] + grid[x2][y2];
                    }
                }
            }
        }
        return dp[N - 1][n - 1][n - 1] == Integer.MIN_VALUE ? 0 : dp[N - 1][n - 1][n - 1];
    }
}
