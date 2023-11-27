package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2541MinimumOperationsToMakeArrayEqualII（使数组中所有元素相等的最小操作数II）
 * @date 2023/11/28 12:13 AM
 */
public class Problem2541MinimumOperationsToMakeArrayEqualII {

    public long minOperations(int[] nums1, int[] nums2, int k) {
        if (k == 0) {
            return Arrays.equals(nums1, nums2) ? 0 : -1;
        }
        int n = nums1.length;
        long ans = 0, count = 0;
        for (int i = 0; i < n; i++) {
            int diff = nums1[i] - nums2[i];
            if (diff % k != 0) {
                return -1;
            }
            if (diff > 0) {
                ans += diff / k;
            }
            count += diff;
        }
        return count == 0 ? ans : -1;
    }
}
