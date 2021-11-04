package com.shuangpeng.Problem.p0601_0700;

public class Problem0664StrangePrinter {

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) || s.charAt(j - 1) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int minValue = Integer.MAX_VALUE;
                    for (int k = j - 1; k >= i; k--) {
                        minValue = Math.min(minValue, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = minValue;
                }
            }
        }
        return dp[0][n - 1];
    }

//    public static void main(String[] args) {
//        Problem0664StrangePrinter a = new Problem0664StrangePrinter();
//        a.strangePrinter("tbgtgb");
//    }
}
