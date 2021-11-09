package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;
import java.util.TreeMap;

public class Problem0983MinimumCostForTickets {

    public int mincostTickets0(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        TreeMap<Integer, Integer> indexMap = new TreeMap<>();
        indexMap.put(0, 0);
        for (int i = 1; i <= n; ++i) {
            int day = days[i - 1];
            dp[i] = dp[i - 1] + costs[0];
            int day7 = Math.max(0, day - 7);
            int j = indexMap.floorEntry(day7).getValue();
            dp[i] = Math.min(dp[i], dp[j] + costs[1]);
            int day30 = Math.max(0, day - 30);
            int k = indexMap.floorEntry(day30).getValue();
            dp[i] = Math.min(dp[i], dp[k] + costs[2]);
            indexMap.put(days[i - 1], i);
        }
        return dp[n];
    }

    static boolean[] map = new boolean[366];
    public int mincostTickets1(int[] days, int[] costs) {
        Arrays.fill(map, false);
        for (int d : days) {
            map[d] = true;
        }
        int[] dp = new int[400];
        for (int i = 365; i >= 1; --i) {
            if (!map[i]) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.min(Math.min(dp[i + 1] + costs[0], dp[i + 7] + costs[1]), dp[i + 30] + costs[2]);
            }
        }
        return dp[1];
    }

    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        final int[] durations = {1, 7, 30};
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Integer.MAX_VALUE;
            int k = i + 1;
            for (int j = 0; j < durations.length; ++j) {
                int day = days[i] + durations[j];
                while (k < n && days[k] < day) {
                    ++k;
                }
                dp[i] = Math.min(dp[i], dp[k] + costs[j]);
            }
        }
        return dp[0];
    }
}
