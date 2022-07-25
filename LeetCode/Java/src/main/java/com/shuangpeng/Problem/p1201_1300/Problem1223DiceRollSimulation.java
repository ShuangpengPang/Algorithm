package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1223DiceRollSimulation（掷骰子模拟）
 * @Date 2022/7/24 9:05 PM
 * @Version 1.0
 */
public class Problem1223DiceRollSimulation {

    public int dieSimulator0(int n, int[] rollMax) {
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

    public int dieSimulator(int n, int[] rollMax) {
        int M = (int) 1e9 + 7;
        int[][] dp = new int[6][16];
        for (int i = 0; i < 6; i++) {
            dp[i][0] = 5;
            dp[i][1] = 1;
        }
        for (int i = 1; i < n; i++) {
            long cnt = 0L;
            for (int j = 0; j < 6; j++) {
                cnt += dp[j][rollMax[j]];
            }
            cnt %= M;
            for (int j = 0; j < 6; j++) {
                long last = dp[j][rollMax[j]], m = dp[j][0];
                for (int k = rollMax[j]; k > 0; k--) {
                    m += dp[j][k];
                    dp[j][k] = dp[j][k - 1];
                }
                dp[j][0] = (int) ((m * 5 - cnt + last + M) % M);
            }
        }
        long ans = 0;
        for (int i = 0; i <= rollMax[0]; i++) {
            ans += dp[0][i];
        }
        return (int) (ans % M);
    }
}
