package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1855MaximumDistanceBetweenAPairOfValues（下标对中的最大距离）
 * @date 2023/10/9 7:22 PM
 */
public class Problem1855MaximumDistanceBetweenAPairOfValues {

    public int maxDistance0(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int ans = 0;
        for (int i = 0, j = 0; i < n1 && j < n2; j++) {
            while (i < n1 && i <= j && nums1[i] > nums2[j]) {
                i++;
            }
            if (i < n1) {
                ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    public int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] > nums2[j]) {
                i++;
            }
            j++;
        }
        return Math.max(0, j - i - 1);
    }
}
