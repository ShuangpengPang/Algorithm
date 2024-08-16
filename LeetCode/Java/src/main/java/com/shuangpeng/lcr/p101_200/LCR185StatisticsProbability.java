package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR185StatisticsProbability（LCR 185. 统计结果概率）
 * @date 2024/8/16 12:25 PM
 */
public class LCR185StatisticsProbability {

    public double[] statisticsProbability(int num) {
        int N = num * 6, n = N - num + 1;
        long[] dp = new long[N + 1];
        for (int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }
        for (int i = 2; i <= num; i++) {
            for (int j = N; j >= 1; j--) {
                dp[j] = 0;
                for (int k = Math.max(0, j - 6); k < j; k++) {
                    dp[j] += dp[k];
                }
            }
        }
        double total = Math.pow(6, num);
        double[] ans = new double[n];
        for (int i = 0; i < n; i++) {
            ans[i] = dp[i + num] / total;
        }
        return ans;
    }
}
