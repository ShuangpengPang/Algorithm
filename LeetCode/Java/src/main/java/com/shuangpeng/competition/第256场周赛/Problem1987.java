package com.shuangpeng.competition.第256场周赛;

public class Problem1987 {

    public int numberOfUniqueGoodSubsequences0(String binary) {
        int n = binary.length();
        int hasZero = 0;
        int s = 0;
        while (s < n && binary.charAt(s) == '0') {
            hasZero = 1;
            s++;
        }
        if (s == n) {
            return 1;
        }
        binary = binary.substring(s);
        n = binary.length();
        for (int i = 0; i < n; ++i) {
            if (binary.charAt(i) == '0') {
                hasZero = 1;
                break;
            }
        }
        int[] previous = new int[n + 1];
        previous[1] = 1;
        for (int i = 2; i <= n; ++i) {
            if (binary.charAt(i - 1) == binary.charAt(i - 2)) {
                previous[i] = previous[i - 1];
            } else {
                previous[i] = i;
            }
        }
        final int M = (int) 1e9 + 7;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            int j = i - 1;
            if (binary.charAt(i - 1) != binary.charAt(j - 1)) {
                j = previous[j] - 1;
            }
            dp[i] += (dp[i - 1] << 1) + M - (j > 0 ? dp[j - 1] : 0);
            dp[i] %= M;
        }
        return (int) (hasZero + dp[n]);
    }

    public int numberOfUniqueGoodSubsequences(String binary) {
        int n = binary.length();
        final int M = (int) 1e9 + 7;
        int zero = 0, one = 0;
        int hasZero = 0;
        for (int i = 0; i < n; ++i) {
            if (binary.charAt(i) == '1') {
                one = zero + one + 1;
                if (one >= M) {
                    one -= M;
                }
            } else {
                hasZero = 1;
                zero = zero + one;
                if (zero >= M) {
                    zero -= M;
                }
            }
        }
        return (zero + one + hasZero) % M;
    }
}
