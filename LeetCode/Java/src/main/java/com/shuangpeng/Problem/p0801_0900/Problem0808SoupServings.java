package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;

/**
 * @description: 分汤
 * @date 2022/11/21 11:35 AM
 **/
public class Problem0808SoupServings {

    // (4, 0) (3, 1) (2, 2) (1, 3)
    public double soupServings0(int n) {
        if (n == 0) {
            return 0.5;
        }
        n = (n - 1) / 25 + 1;
        if (n >= 500) {
            return 1;
        }
        double[][] dp = new double[n + 1][n + 1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= n; ++i) {
            dp[i][0] = 0;
        }
        dp[0][0] = 0.5;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = 0.25 * (dp[Math.max(0, i - 4)][j] + dp[Math.max(0, i - 3)][Math.max(0, j - 1)]
                        + dp[Math.max(0, i - 2)][Math.max(0, j - 2)] + dp[Math.max(0, i - 1)][Math.max(0, j - 3)]);
            }
        }
        return dp[n][n];
    }

    public double soupServings(int n) {
        n = (int) Math.ceil((double) n / 25);
        if (n >= 179) {
            return 1.0;
        }
        double[][] dp = new double[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }
        dp[0][0] = 0.5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = 0.25 * (dp[Math.max(0, i - 4)][j] + dp[Math.max(0, i - 3)][Math.max(0, j - 1)] +
                        dp[Math.max(0, i - 2)][Math.max(0, j - 2)] + dp[Math.max(0, i - 1)][Math.max(0, j - 3)]);
            }
        }
        return dp[n][n];
    }
}