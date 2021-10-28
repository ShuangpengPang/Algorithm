package com.shuangpeng.Problem.p0001_0100;

public class Problem0088MergeSortedArray {

    public void merge0(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int total = m + n;
        int[] nums = new int[total];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums[k++] = nums1[i];
                i++;
            } else {
                nums[k++] = nums2[j];
                j++;
            }
        }
        while (i < m) {
            nums[k++] = nums1[i];
            i++;
        }
        while (j < n) {
            nums[k++] = nums2[j];
            j++;
        }
        for (i = 0; i < total; i++) {
            nums1[i] = nums[i];
        }
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        for (int i = m - 1; i >= 0; i--) {
            nums1[n + i] = nums1[i];
        }
        int s = n;
        int t = 0;
        int total = m + n;
        int i = 0;
        while (s < total && t < n) {
            if (nums1[s] <= nums2[t]) {
                nums1[i++] = nums1[s++];
            } else {
                nums1[i++] = nums2[t++];
            }
        }
        while (s < total) {
            nums1[i++] = nums1[s++];
        }
        while (t < n) {
            nums1[i++] = nums2[t++];
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int i = m - 1;
        int j = n - 1;
        int p = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[p--] = nums1[i--];
            } else {
                nums1[p--] = nums2[j--];
            }
        }
        while (j >= 0) {
            nums1[p--] = nums2[j--];
        }
    }
}
