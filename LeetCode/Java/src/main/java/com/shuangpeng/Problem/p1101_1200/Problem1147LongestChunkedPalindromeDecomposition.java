package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1147LongestChunkedPalindromeDecomposition（段式回文）
 * @date 2023/4/12 10:09 AM
 */
public class Problem1147LongestChunkedPalindromeDecomposition {

    public int longestDecomposition0(String text) {
        char[] cs = text.toCharArray();
        return dfs(cs, 0, new int[(cs.length + 1) >> 1]);
    }

    private int dfs(char[] cs, int start, int[] memo) {
        int n = cs.length, mid = n >> 1;
        int end = n - start - 1;
        if (start >= end) {
            return start == end ? 1 : 0;
        }
        if (memo[start] != 0) {
            return memo[start];
        }
        int ans = 1;
        for (int i = start; i < mid; i++) {
            if (check(cs, start, i)) {
                ans = Math.max(ans, dfs(cs, i + 1, memo) + 2);
            }
        }
        memo[start] = ans;
        return ans;
    }

    private boolean check(char[] cs, int start, int end) {
        int n = end - start + 1;
        for (int i = start, j = cs.length - i - n; i <= end; i++, j++) {
            if (cs[i] != cs[j]) {
                return false;
            }
        }
        return true;
    }
}

class Problem1147LongestChunkedPalindromeDecomposition0 {

    public int longestDecomposition(String text) {
        char[] cs = text.toCharArray();
        int n = cs.length, h = (n + 1) >> 1, m = n >> 1;
        int[] dp = new int[h + 1];
        for (int i = h - 1; i >= 0; i--) {
            int ans = 1;
            for (int j = i; j < m; j++) {
                if (check(cs, i, n - j - 1, j - i + 1)) {
                    ans = Math.max(ans, dp[j + 1] + 2);
                    break;
                }
            }
            dp[i] = ans;
        }
        return dp[0];
    }

    private boolean check(char[] cs, int s1, int s2, int n) {
        for (int i = 0; i < n; i++) {
            if (cs[s1 + i] != cs[s2 + i]) {
                return false;
            }
        }
        return true;
    }
}
