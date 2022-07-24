package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1223DiceRollSimulation（掷骰子模拟）
 * @Date 2022/7/24 9:05 PM
 * @Version 1.0
 */
public class Problem1223DiceRollSimulation {

    public int dieSimulator(int n, int[] rollMax) {
        int max = 0;
        for (int r : rollMax) {
            max = Math.max(max, r);
        }
        int[][][] dp = new int[n][6][max];
        for (int i = 0; i < 6; i++) {
            dp[0][i][0] = 1;
        }
        int M = (int) 1e9 + 7;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                long sum = 0L;
                for (int p = 0; p < 6; p++) {
                    if (p == j) {
                        continue;
                    }
                    for (int r = 0; r < rollMax[p]; r++) {
                        sum += dp[i - 1][p][r];
                    }
                }
                dp[i][j][0] = (int) (sum % M);
                for (int k = 1; k < rollMax[j]; k++) {
                    dp[i][j][k] = dp[i - 1][j][k - 1];
                }
            }
        }
        long ans = 0L;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < rollMax[i]; j++) {
                ans += dp[n - 1][i][j];
            }
        }
        return (int) (ans % M);
    }
}
