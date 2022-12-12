package com.shuangpeng.Problem.p2401_2500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2454NextGreaterElementIV（下一个更大元素IV）
 * @date 2022/12/12 2:46 PM
 */
public class Problem2454NextGreaterElementIV {

    static final int N = (int) 1e5;
    static final int[] first = new int[N], second = new int[N];

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length, p = 0, q = 0;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (q > 0 && nums[second[q - 1]] < x) {
                ans[second[--q]] = x;
            }
            int tp = p;
            while (p > 0 && nums[first[p - 1]] < x) {
                p--;
            }
            if (p < tp) {
                System.arraycopy(first, p, second, q, tp - p);
                q += tp - p;
            }
            first[p++] = i;
        }
        return ans;
    }
}
