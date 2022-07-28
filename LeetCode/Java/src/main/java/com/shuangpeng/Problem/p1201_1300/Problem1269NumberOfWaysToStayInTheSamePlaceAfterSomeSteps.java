package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1269NumberOfWaysToStayInTheSamePlaceAfterSomeSteps（停在原地的方案数）
 * @Date 2022/7/28 6:15 PM
 * @Version 1.0
 */
public class Problem1269NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {

    public int numWays0(int steps, int arrLen) {
        arrLen = Math.min(arrLen, (steps >> 1) + 1);
        int[][][] dp = new int[steps + 1][arrLen][4];
        dp[0][0][0] = 1;
        dp[0][0][3] = 1;
        int M = (int) 1e9 + 7;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen && j <= steps - i; j++) {
                dp[i][j][0] = dp[i - 1][j][3];
                if (j < arrLen - 1) {
                    dp[i][j][1] = dp[i - 1][j + 1][3];
                }
                if (j > 0) {
                    dp[i][j][2] = dp[i - 1][j - 1][3];
                }
                dp[i][j][3] = (int) (((long) dp[i][j][0] + dp[i][j][1] + dp[i][j][2]) % M);
            }
        }
        return dp[steps][0][3];
    }

    public int numWays1(int steps, int arrLen) {
        int n = Math.min(arrLen, (steps >> 1) + 1);
        int[][] dp = new int[steps + 1][n];
        dp[0][0] = 1;
        int M = (int) 1e9 + 7;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < n; j++) {
                long sum = dp[i - 1][j];
                if (j > 0) {
                    sum += dp[i - 1][j - 1];
                }
                if (j + 1 < n) {
                    sum += dp[i - 1][j + 1];
                }
                dp[i][j] = (int) (sum % M);
            }
        }
        return dp[steps][0];
    }

    public int numWays(int steps, int arrLen) {
        int n = Math.min(arrLen, (steps >> 1) + 1);
        int[] dp = new int[n];
        dp[0] = 1;
        int M = (int) 1e9 + 7;
        for (int i = 1; i <= steps; i++) {
            int pre = 0;
            for (int j = 0; j <= Math.min(n - 1, i); j++) {
                int cur = dp[j];
                long sum = cur;
                if (j > 0) {
                    sum += pre;
                }
                if (j + 1 < n) {
                    sum += dp[j + 1];
                }
                dp[j] = (int) (sum % M);
                pre = cur;
            }
        }
        return dp[0];
    }
}
