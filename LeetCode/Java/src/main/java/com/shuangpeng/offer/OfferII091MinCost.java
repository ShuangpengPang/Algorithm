package com.shuangpeng.offer;

import java.util.Arrays;

/**
 * @Description: OfferII091MinCost（粉刷房子）
 * @Date 2022/6/25 9:58 AM
 * @Version 1.0
 */
public class OfferII091MinCost {

    public int minCost0(int[][] costs) {
        int n = costs.length;
        int[] dp = new int[3];
        for (int i = 0; i < n; i++) {
            int[] temp = new int[3];
            temp[0] = Math.min(dp[1], dp[2]) + costs[i][0];
            temp[1] = Math.min(dp[0], dp[2]) + costs[i][1];
            temp[2] = Math.min(dp[0], dp[1]) + costs[i][2];
            dp = temp;
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    public int minCost1(int[][] costs) {
        int[] dp = new int[3], dpTemp = new int[3], temp = new int[0];
        int n = costs.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dpTemp[j] = Math.min(dp[(j + 1) % 3], dp[(j + 2) % 3]) + costs[i][j];
            }
            temp = dp;
            dp = dpTemp;
            dpTemp = temp;
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }

    public int minCost(int[][] costs) {
        int[] dp = new int[3], dpTemp = new int[3], temp = new int[0];
        int n = costs.length;
        for (int i = 0; i < n; i++) {
            dpTemp[0] = Math.min(dp[1], dp[2]) + costs[i][0];
            dpTemp[1] = Math.min(dp[0], dp[2]) + costs[i][1];
            dpTemp[2] = Math.min(dp[0], dp[1]) + costs[i][2];
            temp = dp;
            dp = dpTemp;
            dpTemp = temp;
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}
