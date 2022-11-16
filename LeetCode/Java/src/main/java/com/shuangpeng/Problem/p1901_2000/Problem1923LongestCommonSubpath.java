package com.shuangpeng.Problem.p1901_2000;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1923LongestCommonSubpath（最长公共子路径）
 * @date 2022/11/16 4:21 PM
 */
public class Problem1923LongestCommonSubpath {

    private static final long M1 = (long) 1e9 + 7, M2 = (long) 1e9 + 9, B1 = 101, B2 = 103;

    public int longestCommonSubpath(int n, int[][] paths) {
        int l = 1, r = paths[0].length;
        for (int[] p : paths) {
            r = Math.min(r, p.length);
        }
        while (l <= r) {
            int m = l + (r - l >> 1);
            long h1 = 1, h2 = 1;
            for (int i = 0; i < m; i++) {
                h1 = h1 * B1 % M1;
                h2 = h2 * B2 % M2;
            }
            Set<Pair<Long, Long>> set = getSet(paths[0], m, h1, h2);
            for (int i = 1; i < paths.length && !set.isEmpty(); i++) {
                Set<Pair<Long, Long>> t = new HashSet<>();
                for (Pair<Long, Long> pair : getSet(paths[i], m, h1, h2)) {
                    if (set.contains(pair)) {
                        t.add(pair);
                    }
                }
                set = t;
            }
            if (set.isEmpty()) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l - 1;
    }

    public Set<Pair<Long, Long>> getSet(int[] path, int m, long h1, long h2) {
        int n = path.length;
        long ans1 = 0L, ans2 = 0L;
        Set<Pair<Long, Long>> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ans1 = ans1 * B1 + path[i];
            ans2 = ans2 * B2 + path[i];
            if (i >= m) {
                ans1 = ans1 - h1 * path[i - m] % M1 + M1;
                ans2 = ans2 - h2 * path[i - m] % M2 + M2;
            }
            ans1 %= M1;
            ans2 %= M2;
            if (i >= m - 1) {
                set.add(new Pair<>(ans1, ans2));
            }
        }
        return set;
    }
}