package com.shuangpeng.competition.第061场双周赛;

import java.util.Arrays;
import java.util.Comparator;

public class Problem2008 {

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));
        long[] dp = new long[n + 1];
        int index = 0;
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1];
            while (index < rides.length && i >= rides[index][1]) {
                dp[i] = Math.max(dp[i], dp[rides[index][0]] + rides[index][1] - rides[index][0] + rides[index][2]);
                ++index;
            }
        }
        return dp[n];
    }
}
