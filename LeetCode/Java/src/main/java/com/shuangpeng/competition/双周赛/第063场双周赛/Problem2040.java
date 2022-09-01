package com.shuangpeng.competition.双周赛.第063场双周赛;

import java.util.Arrays;

public class Problem2040 {

    // a, b, c
    // d, e, f

    // ad ae af
    // bd be bf
    // cd ce cf

    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int n1 = getNegativeCount(nums1);
        int n2 = getNegativeCount(nums2);
        int count = n1 * (nums2.length - n2) + (nums1.length - n1) * n2;
        if (k <= count) {
            int[] new1 = merge(nums1, nums2, 0, n1 - 1, 0, n2 - 1);
            int[] new2 = merge(nums1, nums2, n1, nums1.length - 1, n2, nums2.length - 1);
            nums1 = new1;
            nums2 = new2;
            k = count - k + 1;
        } else {
            nums1 = Arrays.copyOfRange(nums1, n1, nums1.length);
            nums2 = Arrays.copyOfRange(nums2, n2, nums2.length);
            k -= count;
        }
        return getKthMin(nums1, nums2, k);
    }

    private long getKthMin(int[] nums1, int[] nums2, long k) {
        int n1 = nums1.length, n2 = nums2.length;
        int left = 0, right = n1 + n2 - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int i = Math.max(0, mid - n2 + 1);
            int j = mid < n2 ? mid : n2 - 1;
            long count = (long) i * n2 + j + 1;
            while (i < n1 - 1 && j > 0) {
                int m = i + 1, n = j - 1;
                while ((long) nums1[m] * nums2[n] > (long) nums1[i] * nums2[j]) {
                    --n;
                }
                count += n + 1;
                i = m;
                j = n;
            }
            if (k > count) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int i = Math.max(0, left - n2 + 1);
        int j = left < n2 ? left : n2 - 1;
        k -= (long) i * n2;
        while (k > j + 1) {
            int m = i + 1, n = j - 1;
            while ((long) nums1[m] * nums2[n] > (long) nums1[i] * nums2[j]) {
                --n;
            }
            k -= j + 1;
            i = m;
            j = n;
        }
        return (long) nums1[i] * nums2[(int) k - 1];
    }

    private int getNegativeCount(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num < 0) {
                ++count;
            } else {
                break;
            }
        }
        return count;
    }

    private int[] merge(int[] nums1, int[] nums2, int s1, int e1, int s2, int e2) {
        int i = s1, j = s2;
        int[] nums = new int[e1 - s1 + 1 + e2 - s2 + 1];
        int idx = 0;
        while (i <= e1 || j <= e2) {
            if (i > e1) {
                nums[idx++] = nums2[j++];
            } else if (j > e2) {
                nums[idx++] = nums1[i++];
            } else if (Math.abs(nums1[i]) < Math.abs(nums2[j])) {
                nums[idx++] = Math.abs(nums1[i++]);
            } else {
                nums[idx++] = Math.abs(nums2[j++]);
            }
        }
        return nums;
    }
}
