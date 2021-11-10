package com.shuangpeng.Problem.p1001_1100;

public class Problem1012NumbersWithRepeatedDigits {

    public int numDupDigitsAtMostN(int n) {
        String s = Integer.toString(n);
        int digits = s.length();
        int[] dp = new int[digits];
        dp[0] = 1;
        int j = 11 - digits;
        for (int i = 1; i < digits; ++i) {
            dp[i] = dp[i - 1] * j;
            ++j;
        }
        int distinct = 0;
        boolean[] visited = new boolean[10];
        for (int i = 0; i < digits; ++i) {
            int k = s.charAt(i) - '0';
            int count = 0;
            for (int c = 0; c < k; ++c) {
                if (!visited[c]) {
                    ++count;
                }
            }
            if (i == 0) {
                --count;
            }
            distinct += count * (dp[digits - i - 1]);
            if (visited[k]) {
                break;
            }
            if (i == digits - 1) {
                ++distinct;
            }
            visited[k] = true;
        }
        int[] dp2 = new int[digits];
        dp2[0] = 1;
        if (digits > 1) {
            dp2[1] = 9;
        }
        for (int i = 2; i < digits; ++i) {
            dp2[i] = (11 - i) * dp2[i - 1];
        }
        for (int i = digits - 1; i >= 1; --i) {
            distinct += dp2[i];
        }
        return n - distinct;
    }
}
