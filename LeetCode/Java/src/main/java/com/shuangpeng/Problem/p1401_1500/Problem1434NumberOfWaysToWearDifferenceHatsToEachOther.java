package com.shuangpeng.Problem.p1401_1500;

import java.util.*;

/**
 * @Description: Problem1434NumberOfWaysToWearDifferenceHatsToEachOther（每个人戴不同帽子的方案数）
 * @Date 2022/8/16 2:55 PM
 * @Version 1.0
 */
public class Problem1434NumberOfWaysToWearDifferenceHatsToEachOther {

    // TLE
    public int numberWays0(List<List<Integer>> hats) {
        int M = (int) 1e9 + 7;
        hats.sort(Comparator.comparingInt(a -> a.size()));
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        for (List<Integer> types : hats) {
            Map<Long, Integer> tmp = new HashMap<>();
            for (int type : types) {
                for (Map.Entry<Long, Integer> entry : cnt.entrySet()) {
                    long key = entry.getKey();
                    if ((key >> type & 1) == 1) {
                        continue;
                    }
                    long hash = key | (1L << type);
                    int count = (entry.getValue() + tmp.getOrDefault(hash, 0)) % M;
                    tmp.put(hash, count);
                }
            }
            cnt = tmp;
        }
        long ans = 0L;
        for (int count : cnt.values()) {
            ans += count;
        }
        return (int) ( ans % M);
    }

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        long[] sets = new long[n];
        int maxHat = 0;
        for (int i = 0; i < n; i++) {
            long h = 0L;
            for (int hat : hats.get(i)) {
                h |= 1L << hat;
                maxHat = Math.max(maxHat, hat);
            }
            sets[i] = h;
        }
        int N = 1 << n, M = (int) 1e9 + 7;
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i <= maxHat; i++) {
            for (int m = N - 1; m > 0; m--) {
                for (int j = 0; j < n; j++) {
                    if ((m >> j & 1) == 0 || (sets[j] >> i & 1) == 0) {
                        continue;
                    }
                    dp[m] = (dp[m] + dp[m ^ (1 << j)]) % M;
                }
            }
        }
        return dp[N - 1];
    }
}


