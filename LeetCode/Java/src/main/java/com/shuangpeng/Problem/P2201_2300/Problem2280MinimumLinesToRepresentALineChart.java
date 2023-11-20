package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2280MinimumLinesToRepresentALineChart（表示一个折线图的最少线段数）
 * @date 2023/11/20 3:42 PM
 */
public class Problem2280MinimumLinesToRepresentALineChart {

    public int minimumLines(int[][] stockPrices) {
        Arrays.sort(stockPrices, Comparator.comparingInt(a -> a[0]));
        int n = stockPrices.length;
        if (n == 1) {
            return 0;
        }
        int ans = 1;
        long px = stockPrices[1][0] - stockPrices[0][0], py = stockPrices[1][1] - stockPrices[0][1];
        for (int i = 2; i < n; i++) {
            long x = stockPrices[i][0] - stockPrices[i - 1][0], y = stockPrices[i][1] - stockPrices[i - 1][1];
            if (px * y != py * x) {
                px = x;
                py = y;
                ans++;
            }
        }
        return ans;
    }
}
