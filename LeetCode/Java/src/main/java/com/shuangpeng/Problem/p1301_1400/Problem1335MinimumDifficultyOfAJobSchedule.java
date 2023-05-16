package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1335MinimumDifficultyOfAJobSchedule（工作计划的最低难度）
 * @Date 2022/8/5 2:07 PM
 * @Version 1.0
 */
public class Problem1335MinimumDifficultyOfAJobSchedule {

    public int minDifficulty0(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n) {
            return -1;
        }
        int[] dp = new int[n];
        for (int i = 0, m = 0; i < n; i++) {
            m = Math.max(m, jobDifficulty[i]);
            dp[i] = m;
        }
        for (int i = 1; i < d; i++) {
            for (int j = n - 1; j >= i; j--) {
                dp[j] = Integer.MAX_VALUE;
                for (int k = j, m = 0; k >= i; k--) {
                    m = Math.max(m, jobDifficulty[k]);
                    dp[j] = Math.min(dp[j], dp[k - 1] + m);
                }
            }
        }
        return dp[n - 1];
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (d > n) {
            return -1;
        }
        int[][] dp = new int[2][n];
        for (int i = 0, m = 0; i < n; i++) {
            m = Math.max(m, jobDifficulty[i]);
            dp[0][i] = m;
        }
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 1; i < d; i++) {
            int idx = i & 1, pre = idx ^ 1;
            q.clear();
            for (int j = i; j < n; j++) {
                int m = dp[pre][j - 1], num = jobDifficulty[j];
                while (!q.isEmpty() && jobDifficulty[q.peek()[0]] < num) {
                    m = Math.min(m, q.pop()[1]);
                }
                dp[idx][j] = q.isEmpty() ? m + num : Math.min(dp[idx][q.peek()[0]], m + num);
                q.push(new int[]{j, m});
            }
        }
        return dp[(d - 1) & 1][n - 1];
    }
}
