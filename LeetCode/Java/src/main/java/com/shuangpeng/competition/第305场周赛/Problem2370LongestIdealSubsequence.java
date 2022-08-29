package com.shuangpeng.competition.第305场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2370LongestIdealSubsequence（最长理想子序列）
 * @Date 2022/8/29 10:50 PM
 * @Version 1.0
 */
public class Problem2370LongestIdealSubsequence {

    // 比赛时写法
    public int longestIdealString0(String s, int k) {
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int n = s.length();
        int ans = 0;
        int[][] dp = new int[n][26];
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            int max = 0;
            for (int c = Math.max(0, j - k); c <= Math.min(25, j + k); c++) {
                if (last[c] != -1) {
                    max = Math.max(max, dp[last[c]][c]);
                }
            }
            dp[i][j] = max + 1;
            last[j] = i;
            ans = Math.max(ans, dp[i][j]);
        }
        return ans;
    }

    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = Math.max(c - k, 0); j <= Math.min(c + k, 25); j++) {
                if (last[j] != -1) {
                    dp[i] = Math.max(dp[i], dp[last[j]] + 1);
                }
            }
            last[c] = i;
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
