package com.shuangpeng.Problem;

public class Problem0801MinimumSwapsToMakeSequencesIncreasing {

    public int minSwap0(int[] nums1, int[] nums2) {
        final int INF = Integer.MAX_VALUE >> 1;
        int n = nums1.length;
        int noSwap = 0, swap = 1;
        for (int i = 1; i < n; i++) {
            int a = nums1[i - 1];
            int b = nums2[i - 1];
            int x = nums1[i];
            int y = nums2[i];
            // noSwap
            int tempNoSwap = INF;
            if (a < x && b < y) {
                tempNoSwap = Math.min(tempNoSwap, noSwap);
            }
            if (b < x && a < y) {
                tempNoSwap = Math.min(tempNoSwap, swap);
            }
            int tempSwap = INF;
            if (a < y && b < x) {
                tempSwap = Math.min(tempSwap, noSwap + 1);
            }
            if (a < x && b < y) {
                tempSwap = Math.min(tempSwap, swap + 1);
            }
            noSwap = tempNoSwap;
            swap = tempSwap;
        }
        return Math.min(noSwap, swap);
    }

    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int noSwap = 0, swap = 1;
        for (int i = 1; i < n; ++i) {
            int tempNoSwap = Integer.MAX_VALUE, tempSwap = Integer.MAX_VALUE;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                tempNoSwap = noSwap;
                tempSwap = swap + 1;
            }
            if (B[i - 1] < A[i] && A[i - 1] < B[i]) {
                tempNoSwap = Math.min(tempNoSwap, swap);
                tempSwap = Math.min(tempSwap, noSwap + 1);
            }
            noSwap = tempNoSwap;
            swap = tempSwap;
        }
        return Math.min(noSwap, swap);
    }
}
