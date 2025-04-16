package com.shuangpeng.Problem.p3401_3500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3418MaximumAmountOfMoneyRobotCanEarn（机器人可以获得的最大金币数）
 * @date 2025/4/15 19:38
 */
public class Problem3418MaximumAmountOfMoneyRobotCanEarn {

    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length, N = Integer.MIN_VALUE >> 1;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], N);
        }
        dp[1][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 2; k >= 0; k--) {
                    dp[j + 1][k] = Math.max(dp[j + 1][k], dp[j][k]) + coins[i][j];
                    if (k > 0) {
                        int t = coins[i][j] >= 0 ? N : Math.max(dp[j][k - 1], dp[j + 1][k - 1]);
                        dp[j + 1][k] = Math.max(dp[j + 1][k], t);
                    }
                }
            }
        }
        return Arrays.stream(dp[n]).max().getAsInt();
    }
}
