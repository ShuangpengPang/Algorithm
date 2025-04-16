package com.shuangpeng.Problem.p3401_3500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3424MinimumCostToMakeArraysIdentical（将数组变相同的最小代价）
 * @date 2025/4/16 16:42
 */
public class Problem3424MinimumCostToMakeArraysIdentical {

    public long minCost(int[] arr, int[] brr, long k) {
        long ans = getCost(arr, brr);
        Arrays.sort(arr);
        Arrays.sort(brr);
        return Math.min(ans, getCost(arr, brr) + k);
    }

    private long getCost(int[] a, int[] b) {
        long ans = 0;
        for (int i = 0, n = a.length; i < n; i++) {
            ans += Math.abs(a[i] - b[i]);
        }
        return ans;
    }
}
