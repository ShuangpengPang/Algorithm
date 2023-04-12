package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1147LongestChunkedPalindromeDecomposition（段式回文）
 * @date 2023/4/12 10:09 AM
 */
public class Problem1147LongestChunkedPalindromeDecomposition {

    public int longestDecomposition(String text) {
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
