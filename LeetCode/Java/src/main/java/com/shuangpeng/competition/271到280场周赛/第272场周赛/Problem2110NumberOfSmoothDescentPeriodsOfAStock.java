package com.shuangpeng.competition.第272场周赛;

public class Problem2110NumberOfSmoothDescentPeriodsOfAStock {

    // 比赛时写法
    public long getDescentPeriods0(int[] prices) {
        int n = prices.length;
        long ans = n;
        for (int l = 0, r = 1; r < n; ++r) {
            while (r < n && prices[r] == prices[r - 1] - 1) {
                ++r;
            }
            int c = r - l;
            if (c >= 2) {
                ans += (long) c * (c - 1) / 2;
            }
            l = r;
        }
        return ans;
    }

    public long getDescentPeriods1(int[] prices) {
        int n = prices.length;
        long ans = n;
        int i = 0, j = 1;
        while (j < n) {
            if (prices[j] != prices[j - 1] - 1) {
                if (j - i >= 2) {
                    ans += (long) (j - i) * (j - i - 1) >> 1;
                }
                i = j;
            }
            ++j;
        }
        if (j - i >= 2) {
            ans += (long) (j - i) * (j - i - 1) >> 1;
        }
        return ans;
    }

    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long pre = 1, ans = 1;
        for (int i = 1; i < n; ++i) {
            pre = prices[i] == prices[i - 1] - 1 ? pre + 1 : 1;
            ans += pre;
        }
        return ans;
    }
}
