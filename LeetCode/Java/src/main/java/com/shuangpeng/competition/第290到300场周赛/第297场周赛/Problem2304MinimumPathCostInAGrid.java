package com.shuangpeng.competition.第290到300场周赛.第297场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2304MinimumPathCostInAGrid（网格中的最小路径代价）
 * @Date 2022/6/25 6:32 PM
 * @Version 1.0
 */
public class Problem2304MinimumPathCostInAGrid {

    // 比赛时写法
    public int minPathCost0(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = grid[m - 1][i];
        }
        for (int i = m - 2; i >= 0; --i) {
            int[] temp = new int[n];
            for (int j = 0; j < n; ++j) {
                int ans = Integer.MAX_VALUE;
                int num = grid[i][j];
                for (int k = 0; k < n; ++k) {
                    ans = Math.min(ans, num + moveCost[num][k] + dp[k]);
                }
                temp[j] = ans;
            }
            dp = temp;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }

    public int minPathCost1(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = grid[0][i];
        }
        int[] temp = new int[n];
        int[] s = new int[0];
        for (int i = 1; i < m; i++) {
            Arrays.fill(temp, Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                int num = grid[i - 1][j];
                for (int k = 0; k < n; k++) {
                    temp[k] = Math.min(temp[k], dp[j] + moveCost[num][k] + grid[i][k]);
                }
            }
            s = dp;
            dp = temp;
            temp = s;
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = grid[0][i];
        }
        int[] temp = new int[n];
        int[] s;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    min = Math.min(min, dp[k] + moveCost[grid[i - 1][k]][j]);
                }
                temp[j] = min + grid[i][j];
            }
            s = dp;
            dp = temp;
            temp = s;
        }
        int ans = Integer.MAX_VALUE;
        for (int i : dp) {
            ans = Math.min(ans, i);
        }
        return ans;
    }
}
