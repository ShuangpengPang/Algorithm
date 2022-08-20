package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1473PaintHouseIII（粉刷房子III）
 * @Date 2022/8/18 7:22 PM
 * @Version 1.0
 */
public class Problem1473PaintHouseIII {

    public int minCost0(int[] houses, int[][] cost, int m, int n, int target) {
        int INF = Integer.MAX_VALUE >> 1;
        int[][][] dp = new int[m + 1][n + 1][target + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        dp[0][0][0] = 0;
        for (int i = 1; i <= m; i++) {
            int c = houses[i - 1];
            if (c != 0) {
                for (int j = 0; j <= n; j++) {
                    for (int k = 1; k <= target; k++) {
                        if (c == j) {
                            dp[i][c][k] = Math.min(dp[i][c][k], dp[i - 1][j][k]);
                        } else {
                            dp[i][c][k] = Math.min(dp[i][c][k], dp[i - 1][j][k - 1]);
                        }
                    }
                }
            } else {
                for (c = 1; c <= n; c++) {
                    for (int j = 0; j <= n; j++) {
                        for (int k = 1; k <= target; k++) {
                            if (c == j) {
                                dp[i][c][k] = Math.min(dp[i][c][k], dp[i - 1][j][k] + cost[i - 1][c - 1]);
                            } else {
                                dp[i][c][k] = Math.min(dp[i][c][k], dp[i - 1][j][k - 1] + cost[i - 1][c - 1]);
                            }
                        }
                    }
                }
            }
        }
        int ans = INF;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[m][i][target]);
        }
        return ans == INF ? -1 : ans;
    }

    public int minCost1(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][n][target];
        int INF = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }
        int[][][] best = new int[m][target][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < target; j++) {
                best[i][j][0] = best[i][j][2] = INF;
                best[i][j][1] = -1;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i] != 0 && houses[i] - 1 != j) {
                    continue;
                }
                for (int k = 0; k < target && k <= i; k++) {
                    if (i == 0) {
                        if (k == 0) {
                            dp[i][j][k] = 0;
                        }
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                        if (k > 0) {
                            dp[i][j][k] = Math.min(dp[i][j][k], j == best[i - 1][k - 1][1] ? best[i - 1][k - 1][2] : best[i - 1][k - 1][0]);
                        }
                    }
                    if (houses[i] == 0) {
                        if (dp[i][j][k] != INF) {
                            dp[i][j][k] += cost[i][j];
                        }
                    }
                    if (dp[i][j][k] < best[i][k][0]) {
                        best[i][k][2] = best[i][k][0];
                        best[i][k][0] = dp[i][j][k];
                        best[i][k][1] = j;
                    } else if (dp[i][j][k] < best[i][k][2]) {
                        best[i][k][2] = dp[i][j][k];
                    }
                }
            }
        }
        int ans = INF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i][target - 1]);
        }
        return ans == INF ? -1 : ans;
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int ans = dfs(houses, cost, 0, 0, target, new int[m][n + 1][target + 1]);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(int[] houses, int[][] cost, int idx, int preColor, int target, int[][][] dp) {
        int m = houses.length, n = cost[0].length, INF = Integer.MAX_VALUE;
        if (idx == m) {
            return target == 0 ? 0 : INF;
        }
        if (m - idx < target || target < 0) {
            return INF;
        }
        if (dp[idx][preColor][target] != 0) {
            return dp[idx][preColor][target];
        }
        int ans = INF;
        if (houses[idx] != 0) {
            ans = dfs(houses, cost, idx + 1, houses[idx], target - (houses[idx] == preColor ? 0 : 1), dp);
        } else {
            for (int i = 0; i < n; i++) {
                int res = dfs(houses, cost, idx + 1, i + 1, target - (i + 1 == preColor ? 0 : 1), dp);
                if (res != INF) {
                    res += cost[idx][i];
                }
                ans = Math.min(ans, res);
            }
        }
        dp[idx][preColor][target] = ans;
        return ans;
    }
}

