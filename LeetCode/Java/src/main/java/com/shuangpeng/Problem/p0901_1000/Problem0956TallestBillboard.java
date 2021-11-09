package com.shuangpeng.Problem.p0901_1000;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem0956TallestBillboard {

    public int tallestBillboard0(int[] rods) {
        int n = rods.length;
        Integer[][] memo = new Integer[n][10001];
        return dp(rods, 0, 5000, memo);
    }

    private int dp(int[] rods, int i, int s, Integer[][] memo) {
        final int NINF = Integer.MIN_VALUE;
        if (i == rods.length) {
            return s == 5000 ? 0 : NINF;
        }
        if (memo[i][s] != null) {
            return memo[i][s];
        }
        int ans = dp(rods, i + 1, s, memo);
        ans = Math.max(ans, dp(rods, i + 1, s - rods[i], memo));
        ans = Math.max(ans, rods[i] + dp(rods, i + 1, s + rods[i], memo));
        memo[i][s] = ans;
        return ans;
    }

    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        Map<Integer, Integer> left = make(Arrays.copyOfRange(rods, 0, n >> 1));
        Map<Integer, Integer> right = make(Arrays.copyOfRange(rods, n >> 1, n));
        int ans = Math.max(left.getOrDefault(0, 0), right.getOrDefault(0, 0));
        for (int d : left.keySet()) {
            if (right.containsKey(-d)) {
                ans = Math.max(ans, left.get(d) + right.get(-d));
            }
        }
        return ans;
    }

    private Map<Integer, Integer> make(int[] nums) {
        int n = nums.length;
        Pair<Integer, Integer>[] dp = new Pair[(int) Math.pow(3, n) + 1];
        int t = 0;
        dp[t++] = new Pair(0, 0);
        for (int num : nums) {
            int j = t;
            for (int i = 0; i < j; ++i) {
                dp[t++] = new Pair(dp[i].getKey() + num, dp[i].getValue());
                dp[t++] = new Pair(dp[i].getKey(), dp[i].getValue() + num);
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < t; ++i) {
            int x = dp[i].getKey(), y = dp[i].getValue();
            map.put(x - y, Math.max(map.getOrDefault(x - y, 0), x));
        }
        return map;
    }
}
