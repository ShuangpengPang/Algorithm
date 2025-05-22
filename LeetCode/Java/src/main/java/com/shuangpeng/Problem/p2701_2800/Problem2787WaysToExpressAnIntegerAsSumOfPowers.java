package com.shuangpeng.Problem.p2701_2800;


import java.util.Arrays;

/**
 * @program: Algorithm
 * @description: Problem2787WaysToExpressAnIntegerAsSumOfPowers（将一个数字表示成幂的和的方案数）
 * @author: ShuangPengPang
 * @create: 2025-05-22 16:32
 **/
public class Problem2787WaysToExpressAnIntegerAsSumOfPowers {

    public int numberOfWays0(int n, int x) {
        int N = (int) 1e9 + 7;
        int m = Math.min(n, (int) Math.pow(n + 1, 1.0 / x));
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                dp[i][j] = dp[i][j - 1];
                int p = (int) Math.pow(j, x);
                if (p <= i) {
                    dp[i][j] = (dp[i][j] + dp[i - p][Math.min(i - p, j - 1)]) % N;
                }
            }
        }
        return dp[n][m];
    }

    public int numberOfWays(int n, int x) {
        int N = (int) 1e9 + 7;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1, p = 1; p <= n; i++, p = (int) Math.pow(i, x)) {
            for (int j = n; j >= p; j--) {
                dp[j] = (dp[j] + dp[j - p]) % N;
            }
        }
        return dp[n];
    }
}
