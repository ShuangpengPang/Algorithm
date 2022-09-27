package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;

/**
 * @Description: Problem1723FindMinimumTimeToFinishAllJobs（完成所有工作的最短时间）
 * @Date 2022/9/27 3:36 PM
 * @Version 1.0
 */
public class Problem1723FindMinimumTimeToFinishAllJobs {

    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length, N = 1 << n, INF = Integer.MAX_VALUE >> 1;
        int[] sum = new int[N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    sum[i] = jobs[j] + sum[i ^ (1 << j)];
                    break;
                }
            }
        }
        int[][] dp = new int[N][k + 1];
        for (int i = 1; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= k; j++) {
                for (int s = i; s > 0; s = (s - 1) & i) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(sum[s], dp[i ^ s][j - 1]));
                }
            }
        }
        return dp[N - 1][k];
    }
}
