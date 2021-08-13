package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0808SoupServings {

    // (4, 0) (3, 1) (2, 2) (1, 3)
    public double soupServings(int n) {
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
}
