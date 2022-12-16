package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2499MinimumTotalCostsToMakeArraysUnequal（让数组不相等的最小总代价）
 * @date 2022/12/16 4:05 PM
 */
public class Problem2499MinimumTotalCostsToMakeArraysUnequal {

    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length, c = 0, num = -1, total = 0;
        long ans = 0L;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                total++;
                ans += i;
                if (c == 0) {
                    num = nums1[i];
                    c = 1;
                } else {
                    c += num == nums1[i] ? 1 : -1;
                }
            }
        }
        if (c == 0) {
            return ans;
        }
        c = 0;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i] && nums1[i] == num) {
                c++;
            }
        }
        for (int i = 0; i < n && c * 2 > total; i++) {
            if (nums1[i] != nums2[i] && nums1[i] != num && nums2[i] != num) {
                total++;
                ans += i;
            }
        }
        return c * 2 > total ? -1 : ans;
    }
}
