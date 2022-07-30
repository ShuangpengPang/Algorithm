package com.shuangpeng.Problem.p1301_1400;

/**
 * @Description: Problem1312MinimumInsertionStepsToMakeAStringPalindrome（让字符串成为回文串的最少插入次数）
 * @Date 2022/7/30 9:55 PM
 * @Version 1.0
 */
public class Problem1312MinimumInsertionStepsToMakeAStringPalindrome {

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
