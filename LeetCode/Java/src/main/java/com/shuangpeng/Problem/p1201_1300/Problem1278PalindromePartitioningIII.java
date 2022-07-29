package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1278PalindromePartitioningIII（分割回文串III）
 * @Date 2022/7/28 11:23 PM
 * @Version 1.0
 */
public class Problem1278PalindromePartitioningIII {

    public int palindromePartition(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] cnt = new int[n][n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int c = changeCount(chars, i, j);
                cnt[i][j] = c;
                dp[i][j] = c;
            }
        }
        int INF = Integer.MAX_VALUE >> 1;
        for (int c = 1; c < k; c++) {
            for (int i = 0; i < n; i++) {
//                for (int j = i; j < Math.min(n, i + c); j++) {
//                    dp[i][j] = INF;
//                }
                for (int j = i + c; j < n; j++) {
                    dp[i][j] = INF;
                    for (int t = i; t <= j - c; t++) {
                        dp[i][j] = Math.min(dp[i][j], cnt[i][t] + dp[t + 1][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    private int changeCount(char[] chars, int i, int j) {
        int ans = 0;
        for (int l = i, r = j; l < r; l++, r--) {
            if (chars[l] != chars[r]) {
                ans++;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1278PalindromePartitioningIII a = new Problem1278PalindromePartitioningIII();
//        a.palindromePartition("abc", 2);
//    }
}
