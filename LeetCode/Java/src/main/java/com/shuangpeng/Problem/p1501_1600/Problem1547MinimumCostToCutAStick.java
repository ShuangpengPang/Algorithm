package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @Description: Problem1547MinimumCostToCutAStick（切棍子的最小成本）
 * @Date 2022/8/30 12:13 PM
 * @Version 1.0
 */
public class Problem1547MinimumCostToCutAStick {

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int m = cuts.length + 2, INF = Integer.MAX_VALUE >> 1;
        int[] nums = new int[m];
        for (int i = 0; i < m - 2; i++) {
            nums[i + 1] = cuts[i];
        }
        nums[m - 1] = n;
        int[][] dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = m - 2; i >= 0; i--) {
            dp[i][i + 1] = 0;
            for (int j = i + 2; j < m; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
                dp[i][j] += nums[j] - nums[i];
            }
        }
        return dp[0][m - 1];
    }
}

