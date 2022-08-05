package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @Description: Problem1335MinimumDifficultyOfAJobSchedule（工作计划的最低难度）
 * @Date 2022/8/5 2:07 PM
 * @Version 1.0
 */
public class Problem1335MinimumDifficultyOfAJobSchedule {

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n) {
            return -1;
        }
        int[] dp = new int[n + 1];
        int INF = Integer.MAX_VALUE / 3;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= d; i++) {
            for (int j = n; j >= i; j--) {
                dp[j] = INF;
                int max = jobDifficulty[j - 1];
                for (int k = j - 1; k >= i - 1; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    dp[j] = Math.min(dp[j], dp[k] + max);
                }
            }
        }
        return dp[n];
    }
}

