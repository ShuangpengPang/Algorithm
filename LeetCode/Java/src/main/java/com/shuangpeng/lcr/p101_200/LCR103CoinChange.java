package com.shuangpeng.lcr.p101_200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR103CoinChange（零钱兑换）
 * @date 2024/7/7 10:12 PM
 */
public class LCR103CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int N = Integer.MAX_VALUE >> 1;
        Arrays.fill(dp, N);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i >= c) {
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                }
            }
        }
        return dp[amount] == N ? -1 : dp[amount];
    }
}
