package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1420BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons（生成数组）
 * @Date 2022/8/15 12:06 PM
 * @Version 1.0
 */
public class Problem1420BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons {

    public int numOfArrays0(int n, int m, int k) {
        if (k == 0 || k > m) {
            return 0;
        }
        int M = (int) 1e9 + 7;
        int[][][] dp = new int[2][k][m];
        for (int i = 0; i < m; i++) {
            dp[0][0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            int cur = i & 1, pre = cur ^ 1;
            for (int s = 0; s < k && s <= i; s++) {
                for (int d = s; d < m; d++) {
                    long cnt = (long) dp[pre][s][d] * (d + 1);
                    if (s > 0) {
                        for (int p = s - 1; p < d; p++) {
                            cnt += dp[pre][s - 1][p];
                        }
                    }
                    dp[cur][s][d] = (int) (cnt % M);
                }
            }
        }
        long ans = 0L;
        int cur = (n - 1) & 1;
        for (int i = k - 1; i < m; i++) {
            ans += dp[cur][k - 1][i];
        }
        return (int) (ans % M);
    }

    public int numOfArrays(int n, int m, int k) {
        if (k == 0 || k > m) {
            return 0;
        }
        int M = (int) 1e9 + 7;
        int[][][] dp = new int[2][k][m];
        for (int i = 0; i < m; i++) {
            dp[0][0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            int cur = i & 1, pre = cur ^ 1;
            for (int s = 0; s < k && s <= i; s++) {
                long preSum = s > 0 ? dp[pre][s - 1][s - 1] : 0;
                for (int d = s; d < m; d++) {
                    dp[cur][s][d] = (int) (((long) dp[pre][s][d] * (d + 1) + preSum) % M);
                    if (s > 0) {
                        preSum += dp[pre][s - 1][d];
                    }
                }
            }
        }
        long ans = 0L;
        int cur = (n - 1) & 1;
        for (int i = k - 1; i < m; i++) {
            ans += dp[cur][k - 1][i];
        }
        return (int) (ans % M);
    }
}

