package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem1105FillingBookcaseShelves {

    public int minHeightShelves(int[][] books, int shelfWidth) {
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
}
