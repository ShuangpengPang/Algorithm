package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0811WaysToChange（面试题 08.11. 硬币）
 * @date 2024/10/10 3:40 PM
 */
public class Question0811WaysToChange {

    private static final int[] coins = {1, 5, 10, 25};

    public int waysToChange(int n) {
        int N = (int) 1e9 + 7;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int c : coins) {
            for (int i = c; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - c]) % N;
            }
        }
        return dp[n];
    }
}
