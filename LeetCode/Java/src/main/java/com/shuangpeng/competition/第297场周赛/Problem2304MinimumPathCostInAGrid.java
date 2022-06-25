package com.shuangpeng.competition.第297场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2304MinimumPathCostInAGrid（网格中的最小路径代价）
 * @Date 2022/6/25 6:32 PM
 * @Version 1.0
 */
public class Problem2304MinimumPathCostInAGrid {

    public int minPathCost(int[][] grid, int[][] moveCost) {
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
}
