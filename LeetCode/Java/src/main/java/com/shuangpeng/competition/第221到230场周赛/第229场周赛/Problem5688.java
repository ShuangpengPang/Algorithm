package com.shuangpeng.competition.第221到230场周赛.第229场周赛;

public class Problem5688 {

    public int longestPalindrome(String word1, String word2) {
        String word = word1 + word2;
        int n1 = word1.length();
        int n = word.length();
        int[][] dp = new int[n][n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    if (i < n1 && j >= n1) {
                        max = Math.max(max, dp[i][j]);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return max;
    }
}
