package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;

/**
 * @Description: Problem1531StringCompressionII（字符串压缩II）
 * @Date 2022/8/28 6:03 PM
 * @Version 1.0
 */
public class Problem1531StringCompressionII {

    public int getLengthOfOptimalCompression(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, INF = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            char c = cs[i - 1];
            for (int j = i; j >= 1 && cs[j - 1] == c; j--) {
                cnt++;
            }
            dp[i][0] = dp[i - cnt][0] + getLength(cnt);
            for (int j = 1; j <= Math.min(k, i); j++) {
                dp[i][j] = dp[i - 1][j - 1];
                for (int d = 0, p = i; p >= 1 && d <= j; p--) {
                    if (cs[p - 1] != c) {
                        d++;
                    }
                    if (d <= j) {
                        dp[i][j] = Math.min(dp[i][j], dp[p - 1][j - d] + getLength(i - p - d + 1));
                    }
                }
            }
        }
        return dp[n][k];
    }

    private int getLength(int cnt) {
        if (cnt == 1) {
            return 1;
        } else if (cnt < 10) {
            return 2;
        } else if (cnt < 100) {
            return 3;
        }
        return 4;
    }
}

class Problem1531StringCompressionII0 {

    public int getLengthOfOptimalCompression(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, INF = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            char c = cs[i - 1];
            for (int j = 0; j <= Math.min(k, i); j++) {
                if (j > 0) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                int same = 0, diff = 0;
                for (int p = i; p >= 1 && diff <= j; p--) {
                    if (cs[p - 1] == c) {
                        same++;
                        dp[i][j] = Math.min(dp[i][j], dp[p - 1][j - diff] + getLength(same));
                    } else {
                        diff++;
                    }
                }
            }
        }
        return dp[n][k];
    }

    private int getLength(int n) {
        if (n == 1) {
            return 1;
        } else if (n < 10) {
            return 2;
        } else if (n < 100) {
            return 3;
        }
        return 4;
    }
}

