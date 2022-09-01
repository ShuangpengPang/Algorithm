package com.shuangpeng.competition.第251到260场周赛.第256场周赛;

public class Problem1986 {

    public int minSessions0(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int M = 1 << n;
        int[][] dp = new int[M][2];
        for (int i = 0; i < M; ++i) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = sessionTime;
        }
        dp[0][0] = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) == 0) {
                    int k = i | (1 << j);
                    if (tasks[j] <= sessionTime - dp[i][1]) {
                        if (dp[k][0] >= dp[i][0]) {
                            dp[k][0] = dp[i][0];
                            dp[k][1] = Math.min(dp[k][1], dp[i][1] + tasks[j]);
                        }
                    } else if (dp[k][0] >= dp[i][0] + 1) {
                        dp[k][0] = dp[i][0] + 1;
                        dp[k][1] = Math.min(dp[k][1], tasks[j]);
                    }
                }
            }
        }
        return dp[M - 1][0];
    }

    public int minSessions1(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int M = 1 << n;
        boolean[] valid = new boolean[M];
        for (int i = 1; i < M; ++i) {
            int needTime = 0;
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    needTime += tasks[j];
                }
            }
            if (needTime <= sessionTime) {
                valid[i] = true;
            }
        }
        int[] dp = new int[M];
        for (int i = 1; i < M; ++i) {
            dp[i] = Integer.MAX_VALUE;
            for (int s = i; s > 0; s = (s - 1) & i) {
                if (valid[s]) {
                    dp[i] = Math.min(dp[i], 1 + dp[i ^ s]);
                }
            }
        }
        return dp[M - 1];
    }

    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int M = 1 << n;
        int[][] dp = new int[M][2];
        dp[0][1] = sessionTime;
        for (int mask = 1; mask < M; ++mask) {
            dp[mask][0] = Integer.MAX_VALUE;
            dp[mask][1] = sessionTime;
            for (int i = 0; i < n; ++i) {
                int j = 1 << i;
                if ((mask & j) != 0) {
                    int count = dp[mask ^ j][0];
                    int time = dp[mask ^ j][1];
                    if (time + tasks[i] > sessionTime) {
                        count++;
                        time = tasks[i];
                    } else {
                        time += tasks[i];
                    }
                    if (dp[mask][0] > count) {
                        dp[mask][0] = count;
                        dp[mask][1] = time;
                    } else if (dp[mask][0] == count) {
                        dp[mask][1] = Math.min(dp[mask][1], time);
                    }
                }
            }
        }
        return dp[M - 1][0];
    }
}
