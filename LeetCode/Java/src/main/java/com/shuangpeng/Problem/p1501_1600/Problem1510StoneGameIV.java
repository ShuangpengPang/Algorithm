package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1510StoneGameIV（石头游戏）
 * @Date 2022/8/24 7:19 PM
 * @Version 1.0
 */
public class Problem1510StoneGameIV {

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i && !dp[i]; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }
}
