package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0516LongestPalindromicSubsequence {

    public int longestPalindromeSubseq0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int d = 1; d < n; d++) {
            for (int i = 0; i + d < n; i++) {
                int j = i + d;
                if (chars[i] == chars[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq1(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }

    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i)) {
                    dp[j][i] = dp[j + 1][i - 1] + 2;
                } else {
                    dp[j][i] = Math.max(dp[j][i - 1], dp[j + 1][i]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq3(String s) {
        int n = s.length();
        int N = 26;
        int[][] memo = new int[n][N];
        memo[n - 1][s.charAt(n - 1) - 'a'] = n - 1;
        for (int i = n - 2; i >= 0; --i) {
            memo[i] = Arrays.copyOf(memo[i + 1], N);
            memo[i][s.charAt(i) - 'a'] = i;
        }
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                char c = s.charAt(j);
                int k = memo[i][c - 'a'];
                dp[i][j] = Math.max(dp[i][j - 1], k == j ? 1 : dp[k + 1][j - 1] + 2);
            }
        }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int[] temp = new int[n];
            temp[i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    temp[j] = dp[j - 1] + 2;
                } else {
                    temp[j] = Math.max(temp[j - 1], dp[j]);
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }
}
