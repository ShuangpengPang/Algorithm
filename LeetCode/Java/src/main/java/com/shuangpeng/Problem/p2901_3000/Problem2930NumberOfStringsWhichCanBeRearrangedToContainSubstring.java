package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2930NumberOfStringsWhichCanBeRearrangedToContainSubstring（重新排列后包含指定子字符串的字符串数目）
 * @date 2024/1/8 10:29 AM
 */
public class Problem2930NumberOfStringsWhichCanBeRearrangedToContainSubstring {

    public int stringCount(int n) {
        int N = 1 << 4;
        long M = (long) 1e9 + 7;
        long[] dp = new long[N];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int m = N - 1; m >= 0; m--) {
                int mask = m & 3;
                if (mask == 2) {
                    continue;
                }
                long cnt = dp[m] * 23;
                for (int j = 1; j < 16; j <<= 1) {
                    if ((m & j) != 0) {
                        if (j != 1) {
                            cnt += dp[m] + dp[m ^ j];
                        } else if (mask == 1) {
                            cnt += dp[m ^ j];
                        }
                    }
                }
                dp[m] = cnt % M;
            }
        }
        return (int) dp[N - 1];
    }
}
