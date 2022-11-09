package com.shuangpeng.competition.第311到320场周赛.第313场周赛;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2430MaximumDeletionsOnAString（对字符串可执行的最大删除数）
 * @date 2022/11/9 1:55 PM
 */
public class Problem2430MaximumDeletionsOnAString {

    public int deleteString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (cs[i] == cs[j]) {
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
                }
            }
        }
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j <= i + (n - i) / 2; j++) {
                if (lcp[i][j] >= j - i) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
        }
        return dp[0];
    }
}