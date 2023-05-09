package com.shuangpeng.Problem.p1101_1200;

import java.util.Arrays;

/**
 * @description: 填充书架
 * @date 2023/4/23 10:45 AM
 **/
public class Problem1105FillingBookcaseShelves {

    public int minHeightShelves0(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; ++i) {
            int width = 0, maxHeight = 0;
            for (int j = i; j >= 1 && width + books[j - 1][0] <= shelfWidth; --j) {
                maxHeight = Math.max(maxHeight, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + maxHeight);
                width += books[j - 1][0];
            }
        }
        return dp[n];
    }

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i, w = 0, h = 0; j >= 1; j--) {
                w += books[j - 1][0];
                if (w > shelfWidth) {
                    break;
                }
                h = Math.max(h, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + h);
            }
        }
        return dp[n];
    }
}
