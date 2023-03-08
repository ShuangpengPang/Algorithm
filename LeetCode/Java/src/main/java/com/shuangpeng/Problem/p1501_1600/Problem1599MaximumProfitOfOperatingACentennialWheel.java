package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1599MaximumProfitOfOperatingACentennialWheel（经营摩天轮的最大利润）
 * @date 2023/3/7 5:49 PM
 */
public class Problem1599MaximumProfitOfOperatingACentennialWheel {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length, max = Integer.MIN_VALUE, ans = 0;
        int s = 0, c = 0, p = 0;
        for (int i = 0; i < n; i++) {
            s = Math.max(s, i);
            c += customers[i];
            if (c < 4) {
                if (s == i) {
                    p += c * boardingCost - runningCost;
                    s++;
                    c = 0;
                }
            } else {
                int cnt = c / 4;
                p += cnt * (boardingCost * 4 - runningCost);
                s += cnt;
                c %= 4;
            }
            if (p > max) {
                max = p;
                ans = s;
            }
        }
        if (c > 0) {
            p += boardingCost * c - runningCost;
            if (p > max) {
                max = p;
                ans = s + 1;
            }
        }
        return max > 0 ? ans : -1;
    }
}
