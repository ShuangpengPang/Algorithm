package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0730CountDifferencePalindromicSubsequences {

    public int countPalindromicSubsequences0(String s) {
        int n = s.length();
        int S = 4;
        final int M = (int) 1e9 + 7;
        long[][][] dp = new long[n][n][S];
        for (int i = 0; i < n; i++) {
            dp[i][i][s.charAt(i) - 'a'] = 1;
        }
        for (int j = 1; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                for (int k = 0; k < S; k++) {
                    dp[i][j][k] = dp[i][j - 1][k];
                }
                char ci = s.charAt(i);
                char cj = s.charAt(j);
                if (ci == cj) {
                    dp[i][j][cj - 'a'] = 2;
                    for (int k = 0; k < S; k++) {
                        dp[i][j][cj - 'a'] += dp[i + 1][j - 1][k];
                    }
                } else {
                    dp[i][j][cj - 'a'] = dp[i + 1][j][cj - 'a'];
                }
                dp[i][j][cj - 'a'] %= M;
            }
        }
        long total = 0;
        for (int i = 0; i < S; i++) {
            total += dp[0][n - 1][i];
        }
        return (int) (total % M);
    }

    public int countPalindromicSubsequences1(String s) {
        int n = s.length();
        final int S = 4;
        final int M = (int) 1e9 + 7;
        long[][] dp = new long[n][S];
        for (int i = n - 1; i >= 0; i--) {
            long[][] copy = new long[n][S];
            for (int j = 0; j < n; j++) {
                copy[j] = Arrays.copyOf(dp[j], dp[j].length);
            }
            char ci = s.charAt(i);
            dp[i][ci - 'a'] = 1;
            for (int j = i + 1; j < n; j++) {
                char cj = s.charAt(j);
                int c = cj - 'a';
                for (int k = 0; k < S; k++) {
                    dp[j][k] = dp[j - 1][k];
                }
                if (ci == cj) {
                    dp[j][c] = 2;
                    for (int k = 0; k < S; k++) {
                        dp[j][c] += copy[j - 1][k];
                    }
                    dp[j][c] %= M;
                } else {
                    dp[j][c] = copy[j][c];
                }
            }
        }
        long total = 0;
        for (int i = 0; i < S; i++) {
            total += dp[n - 1][i];
        }
        return (int) (total % M);
    }

    public int countPalindromicSubsequences(String s) {
        final int S = 4;
        int n = s.length();
        int[][] previous = new int[n][S];
        int[][] next = new int[n][S];
        int[] current = new int[S];
        Arrays.fill(current, -1);
        for (int i = 0; i < n; i++) {
            current[s.charAt(i) - 'a'] = i;
            for (int j = 0; j < S; j++) {
                previous[i][j] = current[j];
            }
        }
        Arrays.fill(current, -1);
        for (int i = n - 1; i >= 0; i--) {
            current[s.charAt(i) - 'a'] = i;
            for (int j = 0; j < S; j++) {
                next[i][j] = current[j];
            }
        }
        return rangeCount(0, n - 1, previous, next, new int[n][n]);
    }

    private int rangeCount(int s, int e,
                           int[][] previous, int[][] next, int[][] memo) {
        if (s > e) {
            return 0;
        }
        if (memo[s][e] > 0) {
            return memo[s][e];
        }
        final int S = 4;
        final int M = (int) 1e9 + 7;
        int count = 0;
        for (int i = 0; i < S; i++) {
            int x = next[s][i];
            int y = previous[e][i];
            if (x == -1 || y == -1) {
                continue;
            }
            if (x <= e) {
                if (x == y) {
                    count++;
                } else {
                    count = count + 2 + rangeCount(x + 1, y - 1, previous, next, memo);
                }
                if (count >= M) {
                    count -= M;
                }
            }
        }
        memo[s][e] = count;
        return count;
    }

    class Solution {
        public int countPalindromicSubsequences(String S) {
            int n = S.length();
            int mod = 1000000007;
            int[][][] dp = new int[4][n][n];

            for (int i = n-1; i >= 0; --i) {
                for (int j = i; j < n; ++j) {
                    for (int k = 0; k < 4; ++k) {
                        char c = (char) ('a' + k);
                        if (j == i) {
                            if (S.charAt(i) == c) dp[k][i][j] = 1;
                            else dp[k][i][j] = 0;
                        } else { // j > i
                            if (S.charAt(i) != c) dp[k][i][j] = dp[k][i+1][j];
                            else if (S.charAt(j) != c) dp[k][i][j] = dp[k][i][j-1];
                            else { // S[i] == S[j] == c
                                if (j == i+1) dp[k][i][j] = 2; // "aa" : {"a", "aa"}
                                else { // length is > 2
                                    dp[k][i][j] = 2;
                                    for (int m = 0; m < 4; ++m) { // count each one within subwindows [i+1][j-1]
                                        dp[k][i][j] += dp[m][i+1][j-1];
                                        dp[k][i][j] %= mod;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            int ans = 0;
            for (int k = 0; k < 4; ++k) {
                ans += dp[k][0][n-1];
                ans %= mod;
            }

            return ans;
        }
    }

    //    public static void main(String[] args) {
//        Problem0730CountDifferencePalindromicSubsequences a = new Problem0730CountDifferencePalindromicSubsequences();
//        a.countPalindromicSubsequences("bccb");
//    }
}
