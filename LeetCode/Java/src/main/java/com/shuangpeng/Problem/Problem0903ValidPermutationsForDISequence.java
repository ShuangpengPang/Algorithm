package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem0903ValidPermutationsForDISequence {

    public int numPermsDISequence0(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(i - 1) == 'D') {
                    for (int k = j; k < i; ++k) {
                        dp[i][j] = add(dp[i][j], dp[i - 1][k]);
                    }
                } else {
                    for (int k = 0; k < j; ++k) {
                        dp[i][j] = add(dp[i][j], dp[i - 1][k]);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            ans = add(ans, dp[n][i]);
        }
        return ans;
    }

    public int numPermsDISequence1(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i <= n; ++i) {
            int[] temp = new int[n + 1];
            for (int j = 0; j <= i; ++j) {
                if (s.charAt(i - 1) == 'D') {
                    for (int k = j; k < i; ++k) {
                        temp[j] = add(temp[j], dp[k]);
                    }
                } else {
                    for (int k = 0; k < j; ++k) {
                        temp[j] = add(temp[j], dp[k]);
                    }
                }
            }
            dp = temp;
        }
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            ans = add(ans, dp[i]);
        }
        return ans;
    }

    public int numPermsDISequence2(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 1; i <= n; ++i) {
            int[] temp = new int[n + 1];
            if (s.charAt(i - 1) == 'D') {
                int x = 0;
                for (int j = i - 1; j >= 0; --j) {
                    temp[j] = add(x, dp[j]);
                    x = temp[j];
                }
            } else {
                int x = 0;
                for (int j = 1; j <= i; ++j) {
                    temp[j] = add(x, dp[j - 1]);
                    x = temp[j];
                }
            }
            dp = temp;
        }
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            ans = add(ans, dp[i]);
        }
        return ans;
    }

    private int add(int a, int b) {
        final int M = (int) 1e9 + 7;
        a += b;
        if (a >= M) {
            a -= M;
        }
        return a;
    }

    public int numPermsDISequence(String s) {
        int n = s.length();
        // 预处理组合数，table[i][j]表示i中选j的取法
        int[][] table = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            table[i][0] = 1;
            table[i][i] = 1;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                table[i][j] = add(table[i - 1][j], table[i - 1][j - 1]);
            }
        }
        return partition(s, 0, n, table, new HashMap<>());
    }

    private int partition(String s, int l, int r, int[][] table, Map<Integer, Integer> memo) {
        if (r <= l + 1) {
            return 1;
        }
        int key = l * (s.length() + 1) + r;
        Integer result = memo.get(key);
        if (result != null) {
            return result;
        }
        final int M = (int) 1e9 + 7;
        int ans = 0;
        for (int i = l; i < r; ++i) {
            if (i == l && s.charAt(i) == 'I') {
                ans = add(ans, partition(s, i + 1, r, table, memo));
            } else if (i == r - 1 && s.charAt(i) == 'D') {
                ans = add(ans, partition(s, l, i, table, memo));
            } else if (i > l && s.charAt(i - 1) == 'D' && s.charAt(i) == 'I') {
                int left = partition(s, l, i - 1, table, memo);
                int right = partition(s, i + 1, r, table, memo);
                ans = add(ans, (int) ((long) table[r - l][i - l] * left % M * right % M));
            }
        }
        memo.put(key, ans);
        return ans;
    }
}
