package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1563StoneGameV（石子游戏V）
 * @Date 2022/8/31 10:49 AM
 * @Version 1.0
 */
public class Problem1563StoneGameV {

    public int stoneGameV0(int[] stoneValue) {
        int n = stoneValue.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stoneValue[i - 1];
        }
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = i; k < j; k++) {
                    int left = preSum[k + 1] - preSum[i], right = preSum[j + 1] - preSum[k + 1];
                    int value = Math.min(left, right);
                    if (left < right) {
                        value += dp[i][k];
                    } else if (left > right) {
                        value += dp[k + 1][j];
                    } else {
                        value += Math.max(dp[i][k], dp[k + 1][j]);
                    }
                    dp[i][j] = Math.max(dp[i][j], value);
                }
            }
        }
        return dp[0][n - 1];
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] dp = new int[n][n], maxLeft = new int[n][n], maxRight = new int[n][n];
        for (int l = n - 1; l >= 0; l--) {
            maxLeft[l][l] = maxRight[l][l] = stoneValue[l];
            for (int r = l + 1, sum = stoneValue[l], i = l - 1, sumLeft = 0; r < n; r++) {
                sum += stoneValue[r];
                while (i + 1 < r && (sumLeft + stoneValue[i + 1]) << 1 <= sum) {
                    sumLeft += stoneValue[++i];
                }
                if (l <= i) {
                    dp[l][r] = Math.max(dp[l][r], maxLeft[l][i]);
                }
                if (i + 2 <= r) {
                    dp[l][r] = Math.max(dp[l][r], maxRight[i + 2][r]);
                }
                if (sumLeft << 1 == sum) {
                    dp[l][r] = Math.max(dp[l][r], maxRight[i + 1][r]);
                }
                maxLeft[l][r] = Math.max(maxLeft[l][r - 1], dp[l][r] + sum);
                maxRight[l][r] = Math.max(maxRight[l + 1][r], dp[l][r] + sum);
            }
        }
        return dp[0][n - 1];
    }
}

