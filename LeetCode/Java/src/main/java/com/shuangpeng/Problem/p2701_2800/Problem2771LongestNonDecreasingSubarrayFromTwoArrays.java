package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2771LongestNonDecreasingSubarrayFromTwoArrays（构造最长非递减子数组）
 * @date 2023/12/14 11:36 PM
 */
public class Problem2771LongestNonDecreasingSubarrayFromTwoArrays {

    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length, ans = 1, a = 1, b = 1;
        for (int i = 1; i < n; i++) {
            int t = a;
            a = getLength(nums1, nums2, t, b, i, nums1[i]);
            b = getLength(nums1, nums2, t, b, i, nums2[i]);
            ans = Math.max(ans, Math.max(a, b));
        }
        return ans;
    }

    private int getLength(int[] nums1, int[] nums2, int a, int b, int i, int num) {
        int ans = 1;
        if (nums1[i - 1] <= num) {
            ans = Math.max(ans, a + 1);
        }
        if (nums2[i - 1] <= num) {
            ans = Math.max(ans, b + 1);
        }
        return ans;
    }
}
