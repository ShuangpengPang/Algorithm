package com.shuangpeng.Problem.p1701_1800;

/**
 * @Description: Problem1745PalindromePartitioningIV（回文串分割IV）
 * @Date 2022/10/8 2:41 PM
 * @Version 1.0
 */
public class Problem1745PalindromePartitioningIV {

    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        dp[n - 1][n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][i] = true;
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j < n; j++) {
                if (dp[0][i] && dp[i + 1][j - 1] && dp[j][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }
}
