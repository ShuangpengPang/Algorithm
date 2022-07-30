package com.shuangpeng.Problem.p1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem1312MinimumInsertionStepsToMakeAStringPalindrome（让字符串成为回文串的最少插入次数）
 * @Date 2022/7/30 9:55 PM
 * @Version 1.0
 */
public class Problem1312MinimumInsertionStepsToMakeAStringPalindrome {

    public int minInsertions0(String s) {
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

    public int minInsertions1(String s) {
        return dfs(s, 0, s.length() - 1, new HashMap<>());
    }

    private int dfs(String s, int i, int j, Map<Integer, Integer> memo) {
        if (i >= j) {
            return 0;
        }
        int key = i | j << 10;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int ans = 0;
        if (s.charAt(i) == s.charAt(j)) {
            ans = dfs(s, i + 1, j - 1, memo);
        } else {
            ans = Math.min(dfs(s, i + 1, j, memo), dfs(s, i, j - 1, memo)) + 1;
        }
        memo.put(key, ans);
        return ans;
    }

    public int minInsertions2(String s) {
        int n = s.length();
        int[][] dp = new int[2][n];
        for (int i = n - 2; i >= 0; i--) {
            int cur = i & 1, pre = cur ^ 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[cur][j] = dp[pre][j - 1];
                } else {
                    dp[cur][j] = Math.min(dp[pre][j], dp[cur][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

    public int minInsertions3(String s) {
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
        return n - dp[0][n - 1];
    }

    public int minInsertions(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int cur = dp[j];
                if (chars[i] == chars[j]) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + 1;
                }
                pre = cur;
            }
        }
        return dp[n - 1];
    }
}
