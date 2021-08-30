package com.shuangpeng.competition.第059场双周赛;

public class Problem1977 {

    public int numberOfCombinations0(String num) {
        if (num.charAt(0) == '0') {
            return 0;
        }
        final int M = (int) 1e9 + 7;
        int n = num.length();
        int[][] lcd = new int[n][n];
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (num.charAt(i) != num.charAt(j)) {
                    lcd[i][j] = 0;
                } else if (j == n - 1) {
                    lcd[i][j] = 1;
                } else {
                    lcd[i][j] = lcd[i + 1][j + 1] + 1;
                }
            }
        }
        int[][] dp = new int[n][n];
        int[][] suffix = new int[n][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            if (num.charAt(i) == '0') {
                continue;
            }
            dp[i][n - 1] = 1;
            suffix[i][n - 1] = 1;
            for (int j = n - 2; j >= i; --j) {
                suffix[i][j] = suffix[i][j + 1];
                int k = (j << 1) + 1 - i;
                if (num.charAt(j + 1) == '0' || k >= n) {
                    continue;
                }
                int x = i + lcd[i][j + 1];
                int y = j + 1 + lcd[i][j + 1];
                if (y == n || y > k || num.charAt(x) < num.charAt(y)) {
                    dp[i][j] += dp[j + 1][k];
                    if (dp[i][j] >= M) {
                        dp[i][j] -= M;
                    }
                }
                dp[i][j] += suffix[j + 1][k + 1];
                if (dp[i][j] >= M) {
                    dp[i][j] -= M;
                }
                suffix[i][j] += dp[i][j];
                if (suffix[i][j] >= M) {
                    suffix[i][j] -= M;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += dp[0][i];
            if (sum >= M) {
                sum -= M;
            }
        }
        return sum;
    }

    public int numberOfCombinations(String num) {
        if (num.charAt(0) == '0') {
            return 0;
        }
        int n = num.length();
        int[][] lcp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            lcp[i][n - 1] = num.charAt(i) == num.charAt(n - 1) ? 1 : 0;
            for (int j = i + 1; j < n - 1; ++j) {
                lcp[i][j] = num.charAt(i) == num.charAt(j) ? lcp[i + 1][j + 1] + 1 : 0;
            }
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; ++i) {
            if (num.charAt(i) == '0') {
                continue;
            }
            int preSum = 0;
            for (int j = i; j < n; ++j) {
                int k = (i << 1) - j - 1;
                dp[i][j] = preSum;
                if (k >= 0) {
                    int x = k + lcp[k][i];
                    int y = i + lcp[k][i];
                    if (x > i - 1 || num.charAt(x) < num.charAt(y)) {
                        dp[i][j] = add(dp[i][j], dp[k][i - 1]);
                    }
                    preSum = add(preSum, dp[k][i - 1]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = add(ans, dp[i][n - 1]);
        }
        return ans;
    }

    private int add(int a, int b) {
        final int M = (int) 1e9 + 7;
        a += b;
        if (a >= M) {
            a -= M;
        }
        return a;
    }
}
