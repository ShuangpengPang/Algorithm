package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @Description: Problem1397FindAllGoodStrings（找到所有好字符串）
 * @Date 2022/8/11 5:23 PM
 * @Version 1.0
 */
public class Problem1397FindAllGoodStrings {

    private static final int N = (int) 1e9 + 7;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        int[][][][] memo = new int[2][2][n][evil.length()];
        for (int f1 = 0; f1 < 2; f1++) {
            for (int f2 = 0; f2 < 2; f2++) {
                for (int i = 0; i < n; i++) {
                    Arrays.fill(memo[f1][f2][i], -1);
                }
            }
        }
        char[] cs = evil.toCharArray();
        return dfs(s1.toCharArray(), s2.toCharArray(), cs, getNext(cs), 1, 1, 0, 0, memo);
    }

    private int[] getNext(char[] cs) {
        int n = cs.length;
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && cs[i] != cs[j]) {
                j = next[j - 1];
            }
            next[i] = j = cs[i] == cs[j] ? j + 1 : j;
        }
        return next;
    }

    private int dfs(char[] cs1, char[] cs2, char[] evil, int[] next, int f1, int f2, int idx, int match, int[][][][] memo) {
        if (match == evil.length) {
            return 0;
        }
        if (idx == cs1.length) {
            return 1;
        }
        if (memo[f1][f2][idx][match] != -1) {
            return memo[f1][f2][idx][match];
        }
        int ans = 0;
        for (char c = f1 == 1 ? cs1[idx] : 'a'; c <= (f2 == 1 ? cs2[idx] : 'z'); c++) {
            int f11 = f1 == 1 && c == cs1[idx] ? 1 : 0;
            int f22 = f2 == 1 && c == cs2[idx] ? 1 : 0;
            int m = match;
            while (m > 0 && evil[m] != c) {
                m = next[m - 1];
            }
            m = evil[m] == c ? m + 1 : m;
            ans = (ans + dfs(cs1, cs2, evil, next, f11, f22, idx + 1, m, memo)) % N;
        }
        return memo[f1][f2][idx][match] = ans;
    }
}

class Problem1397FindAllGoodStrings0 {
    int n;
    int[][] dp;
    int[] next;
    int MOD = (int)1e9 + 7;

    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.n = n;
        int len = evil.length();
        dp = new int[n][len];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        next = new int[len];
        for(int j = 0, i = 1; i < len; i++) {
            while(j > 0 && evil.charAt(i) != evil.charAt(j)) j = next[j - 1];
            if(evil.charAt(i) == evil.charAt(j)) j++;
            next[i] = j;
        }

        return dfs(s1, s2, evil, 0, 0, true, true);
    }

    public int dfs(String s1, String s2, String evil, int i, int j, boolean downLimited, boolean upLimited) {
        // 代表字符串中出现了 evil
        if(j == evil.length()) return 0;
        if(i == n) return 1;
        if(!downLimited && !upLimited && dp[i][j] != -1) return dp[i][j];

        long ans = 0;
        char down = downLimited ? s1.charAt(i) : 'a', up = upLimited ? s2.charAt(i) : 'z';
        for(char k = down; k <= up; k++) {
            int nj = j;
            while(nj > 0 && k != evil.charAt(nj)) nj = next[nj - 1];
            // 此处要注意，当 nj == 0 的时候，会存在 k != evil.charAt(nj) 的情况
            // 若直接 nj + 1 进入递归，是认为此时的两个字符一定是匹配上了，实际上可能并没有
            if(nj == 0 && k != evil.charAt(nj)) nj = -1;
            ans = (ans + dfs(s1, s2, evil, i + 1, nj + 1, downLimited && k == down, upLimited && k == up)) % MOD;
        }
        if(!downLimited && !upLimited) dp[i][j] = (int)ans;
        return (int)ans;
    }
}
