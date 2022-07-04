package com.shuangpeng.competition.第298场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2312SellingPiecesOfWood（卖木头块）
 * @Date 2022/7/1 12:05 PM
 * @Version 1.0
 */
public class Problem2312SellingPiecesOfWood {

    public long sellingWood0(int m, int n, int[][] prices) {
        Arrays.sort(prices, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        long[][] dp = new long[m + 1][n + 1];
        int index = 0;
        for (int h = 1; h <= m; h++) {
            for (int w = 1; w <= n; w++) {
                if (index < prices.length && h == prices[index][0] && w == prices[index][1]) {
                    dp[h][w] = prices[index][2];
                    index++;
                }
                for (int i = 1; i <= h / 2 && i < h; i++) {
                    dp[h][w] = Math.max(dp[h][w], dp[i][w] + dp[h - i][w]);
                }
                for (int i = 1; i <= w / 2 && i < w; i++) {
                    dp[h][w] = Math.max(dp[h][w], dp[h][i] + dp[h][w - i]);
                }
            }
        }
        return dp[m][n];
    }

    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] pr : prices) {
            dp[pr[0]][pr[1]] = pr[2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= i / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k <= j / 2; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];
    }
}

