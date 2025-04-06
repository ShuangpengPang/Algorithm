package com.shuangpeng.Problem.p3301_3400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3376MinimumTimeToBreakLocksI（破解锁的最少时间I）
 * @date 2025/4/3 17:58
 */
public class Problem3376MinimumTimeToBreakLocksI {

    public int findMinimumTime(List<Integer> strength, int k) {
        int ans = Integer.MAX_VALUE;
        for (int[] a : getPermutation(strength, strength.size() - 1)) {
            ans = Math.min(ans, getTime(a, k));
        }
        return ans;
    }

    private int getTime(int[] a, int k) {
        int x = 1;
        int ans = 0;
        for (int s : a) {
            ans += (s + x - 1) / x;
            x += k;
        }
        return ans;
    }

    private List<int[]> getPermutation(List<Integer> strength, int i) {
        if (i == 0) {
            return new ArrayList<int[]>() {{
                add(new int[]{strength.get(0)});
            }};
        }
        List<int[]> ans = new ArrayList<>();
        int x = strength.get(i);
        for (int[] a : getPermutation(strength, i - 1)) {
            for (int j = 0; j <= i; j++) {
                int[] p = new int[i + 1];
                for (int k = 0; k < j; k++) {
                    p[k] = a[k];
                }
                p[j] = x;
                for (int k = j + 1; k <= i; k++) {
                    p[k] = a[k - 1];
                }
                ans.add(p);
            }
        }
        return ans;
    }
}

class Problem3376MinimumTimeToBreakLocksI0 {

    private int ans, k;

    public int findMinimumTime(List<Integer> strength, int k) {
        ans = Integer.MAX_VALUE;
        this.k = k;
        dfs(strength.toArray(new Integer[strength.size()]), 0, 0, new boolean[strength.size()]);
        return ans;
    }

    private void dfs(Integer[] s, int x, int t, boolean[] used) {
        int n = s.length;
        if (x == n || t >= ans) {
            ans = Math.min(ans, t);
            return;
        }
        int d = x * k + 1;
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                dfs(s, x + 1, t + (s[i] + d - 1) / d, used);
                used[i] = false;
            }
        }
    }
}

class Problem3376MinimumTimeToBreakLocksI1 {

    public int findMinimumTime(List<Integer> strength, int k) {
        int n = strength.size(), N = 1 << n, M = Integer.MAX_VALUE >> 1;
        Integer[] s = strength.toArray(new Integer[n]);
        int[] dp = new int[N];
        Arrays.fill(dp, 1, N, M);
        for (int i = 1; i < N; i++) {
            int d = (Integer.bitCount(i) - 1) * k + 1;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    dp[i] = Math.min(dp[i], dp[i ^ (1 << j)] + (s[j] + d - 1) / d);
                }
            }
        }
        return dp[N - 1];
    }
}
