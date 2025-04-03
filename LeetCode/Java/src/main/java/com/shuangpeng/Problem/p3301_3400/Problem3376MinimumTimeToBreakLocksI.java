package com.shuangpeng.Problem.p3301_3400;

import java.util.ArrayList;
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
