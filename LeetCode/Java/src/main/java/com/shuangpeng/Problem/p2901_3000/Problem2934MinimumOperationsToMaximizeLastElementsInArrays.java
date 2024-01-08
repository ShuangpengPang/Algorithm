package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2934MinimumOperationsToMaximizeLastElementsInArrays（最大化数组末位元素的最少操作次数）
 * @date 2024/1/8 3:13 PM
 */
public class Problem2934MinimumOperationsToMaximizeLastElementsInArrays {

    static int N = Integer.MAX_VALUE >> 1;

    public int minOperations(int[] nums1, int[] nums2) {
        int ans = getCount(nums1, nums2), n = nums1.length;
        int tmp = nums1[n - 1];
        nums1[n - 1] = nums2[n - 1];
        nums2[n - 1] = tmp;
        ans = Math.min(ans, getCount(nums1, nums2) + 1);
        return ans == N ? -1 : ans;
    }

    private int getCount(int[] nums1, int[] nums2) {
        int cnt = 0, n = nums1.length;
        for (int i = 0; i < n; i++) {
            if (nums1[i] > nums1[n - 1] || nums2[i] > nums2[n - 1]) {
                if (nums2[i] > nums1[n - 1] || nums1[i] > nums2[n - 1]) {
                    return N;
                }
                cnt++;
            }
        }
        return cnt;
    }
}
