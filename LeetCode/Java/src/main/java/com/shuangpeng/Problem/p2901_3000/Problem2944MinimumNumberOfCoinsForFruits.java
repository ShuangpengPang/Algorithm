package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2944MinimumNumberOfCoinsForFruits（购买水果需要的最少金币数）
 * @date 2024/1/9 4:18 PM
 */
public class Problem2944MinimumNumberOfCoinsForFruits {

    public int minimumCoins(int[] prices) {
        int n = prices.length, N = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = N;
            for (int j = i; j >= i >> 1; j--) {
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + prices[j]);
            }
        }
        return dp[n];
    }
}
