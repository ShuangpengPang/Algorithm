package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @Description: Problem1639NumberOfWaysToFormATargetStringGivenADictionary（通过给定词典构造目标字符串的方案数）
 * @Date 2022/9/8 12:01 PM
 * @Version 1.0
 */
public class Problem1639NumberOfWaysToFormATargetStringGivenADictionary {

    public int numWays0(String[] words, String target) {
        int m = words[0].length(), n = target.length(), M = (int) 1e9 + 7;
        int[][] cnt = new int[m][26];
        for (String w : words) {
            for (int i = 0; i < m; i++) {
                cnt[i][w.charAt(i) - 'a']++;
            }
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            int c = target.charAt(i - 1) - 'a';
            for (int j = 1; j <= m; j++) {
                dp[i][j] = (int) (((long) dp[i - 1][j - 1] * cnt[j - 1][c] + dp[i][j - 1]) % M);
            }
        }
        return dp[n][m];
    }

    public int numWays(String[] words, String target) {
        int m = words[0].length(), n = target.length(), M = (int) 1e9 + 7;
        int[][] cnt = new int[m][26];
        for (String w : words) {
            for (int i = 0; i < m; i++) {
                cnt[i][w.charAt(i) - 'a']++;
            }
        }
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            int c = target.charAt(i) - 'a', last = i == 0 ? 1 : 0, prev = 0;
            for (int j = 0; j < m; j++) {
                int ans = (int) (((long) last * cnt[j][c] + prev) % M);
                last = dp[j];
                dp[j] = ans;
                prev = ans;
            }
        }
        return dp[m - 1];
    }
}

