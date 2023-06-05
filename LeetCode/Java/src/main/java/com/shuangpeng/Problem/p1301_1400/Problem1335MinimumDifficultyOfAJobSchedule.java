package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayDeque;
import java.util.Arrays;
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

    public int minDifficulty1(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length, N = Integer.MAX_VALUE >> 1;
        int[] arr = new int[n + 1];
        System.arraycopy(jobDifficulty, 0, arr, 1, n);
        arr[0] = N;
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], N);
        dp[0][0] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 1; i <= d; i++) {
            int id = i & 1, p = id ^ 1;
            Arrays.fill(dp[id], N);
            q.clear();
            q.push(new int[]{i - 1, dp[p][i - 1]});
            for (int j = i; j <= n; j++) {
                int s = N;
                while (!q.isEmpty() && arr[j] >= arr[q.peek()[0]]) {
                    s = Math.min(s, q.pop()[1]);
                }
                dp[id][j] = Math.min(dp[id][j], arr[j] + Math.min(s, q.isEmpty() ? N : dp[p][q.peek()[0]]));
                dp[id][j] = Math.min(dp[id][j], q.isEmpty() ? N : dp[id][q.peek()[0]]);
                q.push(new int[]{j, Math.min(s, dp[p][j])});
            }
        }
        int id = d & 1;
        q.clear();
        for (int i = n; i >= 1; i--) {
            int s = dp[id][i];
            while (!q.isEmpty() && arr[i] >= arr[q.peek()[0]]) {
                s = Math.min(s, q.pop()[1]);
            }
            if (q.isEmpty()) {
                q.push(new int[]{i, s});
            }
        }
        int ans = q.pop()[1];
        return ans == N ? -1 : ans;
    }

    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length, N = Integer.MAX_VALUE >> 1;
        if (d > n) {
            return -1;
        }
        int[][] dp = new int[2][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = i == 0 ? jobDifficulty[0] : Math.max(jobDifficulty[i], dp[0][i - 1]);
        }
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 1; i < d; i++) {
            int id = i & 1, p = id ^ 1;
            q.clear();
            for (int j = i; j < n; j++) {
                int s = N;
                while (!q.isEmpty() && jobDifficulty[j] >= jobDifficulty[q.peek()[0]]) {
                    s = Math.min(s, q.pop()[1]);
                }
                s = Math.min(s, q.isEmpty() ? dp[p][i - 1] : dp[p][q.peek()[0]]);
                int num = s + jobDifficulty[j];
                dp[id][j] = q.isEmpty() ? num : Math.min(num, dp[id][q.peek()[0]]);
                q.push(new int[]{j, Math.min(s, dp[p][j])});
            }
        }
        return dp[(d - 1) & 1][n - 1];
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
