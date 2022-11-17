package com.shuangpeng.Problem.p2001_2100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2040KthSmallestProductOfTwoSortedArrays（两个有序数组的第K小乘积）
 * @date 2022/11/17 4:01 PM
 */
public class Problem2040KthSmallestProductOfTwoSortedArrays {

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int[][] s1 = split(nums1), s2 = split(nums2);
        int[] a11 = s1[0], a12 = s1[1], a21 = s2[0], a22 = s2[1];
        long cnt = (long) a11.length * a22.length + (long) a12.length * a21.length;
        if (cnt >= k) {
            k = cnt - k + 1;
            return -getKthSmallest(a11, a22, a12, a21, k);
        }
        k -= cnt;
        return getKthSmallest(a11, a21, a12, a22, k);
    }

    private long getKthSmallest(int[] a11, int[] a12, int[] a21, int[] a22, long k) {
        int n11 = a11.length, n12 = a12.length, n21 = a21.length, n22 = a22.length;
        long m1 = n11 == 0 || n12 == 0 ? 0L : (long) a11[n11 - 1] * a12[n12 - 1];
        long m2 = n21 == 0 || n22 == 0 ? 0L : (long) a21[n21 - 1] * a22[n22 - 1];
        long left = 0, right = Math.max(m1, m2);
        while (left <= right) {
            long mid = left + (right - left >> 1);
            if (getCount(a11, a12, mid) + getCount(a21, a22, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private long getCount(int[] nums1, int[] nums2, long target) {
        int n1 = nums1.length, n2 = nums2.length;
        long ans = 0L;
        for (int i = 0, j = n2 - 1; i < n1; i++) {
            while (j >= 0 && (long) nums1[i] * nums2[j] > target) {
                j--;
            }
            ans += j + 1;
        }
        return ans;
    }

    private int[][] split(int[] nums) {
        int n = nums.length, n1 = 0, n2 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                n1++;
            } else {
                n2++;
            }
        }
        int[] nums1 = new int[n1], nums2 = new int[n2];
        for (int i = 0; i < n1; i++) {
            nums1[i] = -nums[n1 - i - 1];
        }
        for (int i = 0; i < n2; i++) {
            nums2[i] = nums[n1 + i];
        }
        return new int[][]{nums1, nums2};
    }
}
