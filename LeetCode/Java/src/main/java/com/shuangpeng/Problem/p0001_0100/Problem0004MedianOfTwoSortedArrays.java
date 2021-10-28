package com.shuangpeng.Problem.p0001_0100;

public class Problem0004MedianOfTwoSortedArrays {

    public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            nums1 = new int[0];
        }
        if (nums2 == null) {
            nums2 = new int[0];
        }
        int length = nums1.length + nums2.length;
        int median1 = length / 2;
        int median2 = median1;
        if (length % 2 == 0) {
            median1--;
        }
        int m = 0;
        int n = 0;
        int j = 0;
        int k = 0;
        for (int i = 0; i < median1; i++) {
            if (j >= nums1.length) {
                k++;
            } else if (k >= nums2.length) {
                j++;
            } else if (nums1[j] <= nums2[k]) {
                j++;
            } else {
                k++;
            }
        }
        if (j >= nums1.length) {
            m = nums2[k];
            n = m;
            if (median1 != median2) {
                n = nums2[k + 1];
            }
        } else if (k >= nums2.length) {
            m = nums1[j];
            n = m;
            if (median1 != median2) {
                n = nums1[j + 1];
            }
        } else if (nums1[j] <= nums2[k]) {
            m = nums1[j];
            n = m;
            if (median1 != median2) {
                if (j + 1 >= nums1.length) {
                    n = nums2[k];
                } else if (nums1[j + 1] <= nums2[k]) {
                    n = nums1[j + 1];
                } else {
                    n = nums2[k];
                }
            }
        } else {
            m = nums2[k];
            n = m;
            if (median1 != median2) {
                if (k + 1 >= nums2.length) {
                    n = nums1[j];
                } else if (nums2[k + 1] <= nums1[j]) {
                    n = nums2[k + 1];
                } else {
                    n = nums1[j];
                }
            }
        }
        return (m + n) / 2.0;
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            nums1 = new int[0];
        }
        if (nums2 == null) {
            nums2 = new int[0];
        }
        int j = 0;
        int k = 0;
        int length = nums1.length + nums2.length;
        int count = length / 2 + 1;
        int left = -1;
        int right = -1;
        for (int i = 0; i < count; i++) {
            left = right;
            if (j >= nums1.length) {
                right = nums2[k];
                k++;
            } else if (k >= nums2.length) {
                right = nums1[j];
                j++;
            } else if (nums1[j] <= nums2[k]) {
                right = nums1[j];
                j++;
            } else {
                right = nums2[k];
                k++;
            }

//            if (j < nums1.length && ((k >= nums2.length) || nums1[j] <= nums2[k])) {
//                right = nums1[j++];
//            } else {
//                right = nums2[k++];
//            }
        }
        return (length & 1) == 0 ? (left + right) / 2.0 : right;
    }

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        if (nums1 == null) {
//            nums1 = new int[0];
//        }
//        if (nums2 == null) {
//            nums2 = new int[0];
//        }
//    }
}
