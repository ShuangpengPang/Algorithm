package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3332MaximumPointsTouristCanEarn（旅客可以得到的最多点数）
 * @date 2025/2/25 7:19 PM
 */
public class Problem3332MaximumPointsTouristCanEarn {

    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        // d, c   stayScore[d][c] || travalScore[c][x]
        int[][] dp = new int[2][n];
        for (int i = 0; i < k; i++) {
            int c = i & 1, p = c ^ 1;
            for (int j = 0; j < n; j++) {
                dp[c][j] = dp[p][j] + stayScore[i][j];
                for (int x = 0; x < n; x++) {
                    dp[c][j] = Math.max(dp[c][j], dp[p][x] + travelScore[x][j]);
                }
            }
        }
        return Arrays.stream(dp[k - 1 & 1]).max().getAsInt();
    }
}
