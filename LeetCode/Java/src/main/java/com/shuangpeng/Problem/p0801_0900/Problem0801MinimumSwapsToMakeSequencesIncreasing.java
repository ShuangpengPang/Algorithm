package com.shuangpeng.Problem.p0801_0900;

/**
 * @Description:（使序列递增的最小交换次数）
 * @Date 2022/10/10 10:37 AM
 **/
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

    public int minSwap1(int[] A, int[] B) {
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

    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int swap = 1, noSwap = 0, INF = Integer.MAX_VALUE >> 1;
        for (int i = 1; i < n; i++) {
            boolean b1 = nums1[i - 1] < nums1[i] && nums2[i - 1] < nums2[i];
            boolean b2 = nums1[i - 1] < nums2[i] && nums2[i - 1] < nums1[i];
            int swapTemp = 1 + Math.min(b1 ? swap : INF, b2 ? noSwap : INF);
            int noSwapTemp = Math.min(b1 ? noSwap : INF, b2 ? swap : INF);
            swap = swapTemp;
            noSwap = noSwapTemp;
        }
        return Math.min(swap, noSwap);
    }
}
