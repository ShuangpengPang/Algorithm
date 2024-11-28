package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3290MaximumMultiplicationScore（最高乘法得分）
 * @date 2024/11/28 5:19 PM
 */
public class Problem3290MaximumMultiplicationScore {

    public long maxScore0(int[] a, int[] b) {
        int n = b.length, m = a.length;
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (long) a[0] * b[i];
        }
        for (int i = 1; i < m; i++) {
            long mx = dp[i - 1], num = a[i];
            for (int j = i; j < n; j++) {
                long t = dp[j];
                dp[j] = mx + num * b[j];
                mx = Math.max(mx, t);
            }
        }
        long ans = Long.MIN_VALUE;
        for (int i = m - 1; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public long maxScore(int[] a, int[] b) {
        int m = a.length, n = b.length;
        long[] dp = new long[m + 1];
        Arrays.fill(dp, 1, m + 1, Long.MIN_VALUE >> 1);
        for (int i = 0; i < n; i++) {
            for (int j = m; j > 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - 1] + (long) b[i] * a[j - 1]);
            }
        }
        return dp[m];
    }
}
