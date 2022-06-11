package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;

/**
 * @Description: 统计不同回文子序列（730）
 * @Date 2022/6/10 1:52 PM
 **/
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

    public int countPalindromicSubsequences2(String s) {
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

    public int countPalindromicSubsequences3(String s) {
        int M = (int) 1e9 + 7;
        int n = s.length();
        int[][][] dp = new int[n][n][4];
        for (int j = 0; j < n; ++j) {
            int cj = s.charAt(j) - 'a';
            dp[j][j][cj] = 1;
            for (int i = j - 1; i >= 0; --i) {
                for (int k = 0; k < 4; ++k) {
                    dp[i][j][k] = dp[i + 1][j][k];
                }
                int ci = s.charAt(i) - 'a';
                if (ci == cj) {
                    dp[i][j][ci] = 2;
                    for (int k = 0; k < 4; ++k) {
                        dp[i][j][ci] += dp[i + 1][j - 1][k];
                        if (dp[i][j][ci] >= M) {
                            dp[i][j][ci] -= M;
                        }
                    }
                } else {
                    dp[i][j][ci] = dp[i][j - 1][ci];
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 4; ++i) {
            ans += dp[0][n - 1][i];
        }
        return (int) (ans % M);
    }

    public int countPalindromicSubsequences4(String s) {
        int M = (int) 1e9 + 7;
        int n = s.length();
        int[][][] dp = new int[n][n][4];
        for (int i = n - 1; i >= 0; --i) {
            int ci = s.charAt(i) - 'a';
            dp[i][i][ci] = 1;
            for (int j = i + 1; j < n; ++j) {
                for (int k = 0; k < 4; ++k) {
                    dp[i][j][k] = dp[i][j - 1][k];
                }
                int cj = s.charAt(j) - 'a';
                if (cj == ci) {
                    dp[i][j][cj] = 2;
                    for (int k = 0; k < 4; ++k) {
                        dp[i][j][cj] += dp[i + 1][j - 1][k];
                        if (dp[i][j][cj] >= M) {
                            dp[i][j][cj] -= M;
                        }
                    }
                } else {
                    dp[i][j][cj] = dp[i + 1][j][cj];
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 4; ++i) {
            ans += dp[0][n - 1][i];
        }
        return (int) (ans % M);
    }

    public int countPalindromicSubsequences5(String s) {
        int M = (int) 1e9 + 7;
        int n = s.length();
        int[][] prev = new int[n][4], next = new int[n][4];
        Arrays.fill(prev[0], -1);
        Arrays.fill(next[n - 1], n);
        for (int i = 1; i < n; ++i) {
            int j = n - i - 1;
            for (int k = 0; k < 4; ++k) {
                prev[i][k] = s.charAt(i - 1) - 'a' == k ? i - 1 : prev[i - 1][k];
                next[j][k] = s.charAt(j + 1) - 'a' == k ? j + 1 : next[j + 1][k];
            }
        }
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            dp[i][i] = 1;
            int ci = s.charAt(i) - 'a';
            for (int j = i + 1; j < n; ++j) {
                int cj = s.charAt(j) - 'a';
                if (ci == cj) {
                    int i1 = next[i][ci], i2 = prev[j][cj];
                    dp[i][j] = dp[i + 1][j - 1] << 1;
                    if (i1 < i2) {
                        dp[i][j] -= dp[i1 + 1][i2 - 1];
                    } else if (i1 == i2) {
                        ++dp[i][j];
                    } else {
                        dp[i][j] += 2;
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
                if (dp[i][j] < 0) {
                    dp[i][j] += M;
                } else if (dp[i][j] >= M) {
                    dp[i][j] %= M;
                }
            }
        }
        return dp[0][n - 1];
    }

    public int countPalindromicSubsequences6(String s) {
        int M = (int) 1e9 + 7;
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        int[] left = new int[4], right = new int[4];
        Arrays.fill(left, n);
        for (int i = n - 1; i >= 0; --i) {
            left[chars[i] - 'a'] = i;
            Arrays.fill(right, -1);
            for (int j = i; j < n; ++j) {
                right[chars[j] - 'a'] = j;
                for (int k = 0; k < 4; ++k) {
                    int l = left[k], r = right[k];
                    if (l == r) {
                        ++dp[i][j];
                    } else if (l < r) {
                        dp[i][j] += dp[l + 1][r - 1] + 2;
                    }
                    dp[i][j] %= M;
                }
            }
        }
        return dp[0][n - 1];
    }

    public int countPalindromicSubsequences(String s) {
        int M = (int) 1e9 + 7;
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][4];
        for (int i = n - 1; i >= 0; --i) {
            int charIndex = chars[i] - 'a';
            dp[i][charIndex] = 1;
            int count = 1;
            int last = 0;
            for (int j = i + 1; j < n; ++j) {
                if (chars[j] - 'a' == charIndex) {
                    count = 2;
                    for (int k = 0; k < 4; ++k) {
                        count += k == charIndex ? last : dp[j - 1][k];
                        if (count >= M) {
                            count -= M;
                        }
                    }
                }
                last = dp[j][charIndex];
                dp[j][charIndex] = count;
            }
        }
        long ans = 0;
        for (int i = 0; i < 4; ++i) {
            ans += dp[n - 1][i];
        }
        return (int) (ans % M);
    }

    //    public static void main(String[] args) {
//        Problem0730CountDifferencePalindromicSubsequences a = new Problem0730CountDifferencePalindromicSubsequences();
//        a.countPalindromicSubsequences("bccb");
//    }
}
