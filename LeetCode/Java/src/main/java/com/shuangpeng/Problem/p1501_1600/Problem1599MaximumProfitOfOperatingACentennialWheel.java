package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1599MaximumProfitOfOperatingACentennialWheel（经营摩天轮的最大利润）
 * @date 2023/3/7 5:49 PM
 */
public class Problem1599MaximumProfitOfOperatingACentennialWheel {

    public int minOperationsMaxProfit0(int[] customers, int boardingCost, int runningCost) {
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

    public int minOperationsMaxProfit1(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length, max = 0, ans = -1, cnt = 0, p = 0;
        for (int i = 0; i < n; i++) {
            cnt += customers[i];
            int c = Math.min(cnt, 4);
            cnt -= c;
            p += c * boardingCost - runningCost;
            if (p > max) {
                max = p;
                ans = i + 1;
            }
        }
        int s = cnt / 4, m = cnt % 4;
        p += s * 4 * boardingCost - s * runningCost;
        if (p > max) {
            max = p;
            ans = n + s;
        }
        if (m > 0) {
            p += m * boardingCost - runningCost;
            if (p > max) {
                max = p;
                ans = n + s + 1;
            }
        }
        return max > 0 ? ans : -1;
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n = customers.length, max = 0, p = 0, ans = -1, cnt = 0, i = 0, s = 0;
        while (i < n || cnt > 0) {
            if (i < n) {
                cnt += customers[i];
            }
            int c = Math.min(cnt, 4);
            cnt -= c;
            p += c * boardingCost - runningCost;
            s++;
            if (p > max) {
                max = p;
                ans = s;
            }
            i++;
        }
        return ans;
    }
}
