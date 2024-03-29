package com.shuangpeng.Problem.p0101_0200;

import java.util.Arrays;

public class Problem0132PalindromePartitioningII {

    public int minCut0(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (isPalindrome(s, j, i)) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                }
            }
        }
        return dp[n];
    }

    private boolean isPalindrome(String string, int s, int e) {
        while (s < e) {
            if (string.charAt(s) == string.charAt(e)) {
                s++;
                e--;
            } else {
                return false;
            }
        }
        return true;
    }

    public int minCut1(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] p = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(p[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                p[i][j] = s.charAt(i) == s.charAt(j) && p[i + 1][j - 1];
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (p[0][i]) {
                dp[i] = 0;
            } else {
                for (int j = 1; j <= i; j++) {
                    if (p[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
