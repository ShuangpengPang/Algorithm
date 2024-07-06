package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR098UniquePaths（不同路径）
 * @date 2024/7/6 11:50 AM
 */
public class LCR098UniquePaths {

    public int uniquePaths0(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    // 杨辉三角和组合数
    public int uniquePaths1(int m, int n) {
        int[] c = new int[n];
        c[0] = 1;
        for (int i = 1; i <= m + n - 2; i++) {
            for (int j = Math.min(i, n - 1); j > 0; j--) {
                c[j] += c[j - 1];
            }
        }
        return c[n - 1];
    }

    public int uniquePaths(int m, int n) {
        int N = m + n - 2;
        long ans = 1;
        for (int i = m; i <= N; i++) {
            ans = ans * i / (i - m + 1);
        }
        return (int) ans;
    }
}
