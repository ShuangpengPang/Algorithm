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
}
