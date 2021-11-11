package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

public class Problem1818MinimumAbsoluteSumDifference {

    public int minAbsoluteSumDiff0(int[] nums1, int[] nums2) {
        int mod = (int) 1e9 + 7;
        int n = nums1.length;
        int sum = 0;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diffs[i];
            if (sum >= mod) {
                sum -= mod;
            }
        }
        Arrays.sort(nums1);
        int answer = sum;
        for (int i = 0; i < n; i++) {
            int num = nums2[i];
            int index = binarySearch(nums1, num);
            int min = 0;
            if (index <= 0) {
                min = Math.abs(nums1[0] - nums2[i]);
            } else if (index >= n) {
                min = Math.abs(nums1[n - 1] - nums2[i]);
            } else {
                min = Math.min(Math.abs(nums1[index - 1] - nums2[i]), Math.abs(nums1[index] - nums2[i]));
            }
            answer = Math.min(answer, sum + min - diffs[i]);
        }
        if (answer < 0) {
            answer += mod;
        }
        return answer;
    }

    private int binarySearch(int[] nums, int data) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < data) {
                left = mid + 1;
            } else if (nums[mid] > data) {
                right = mid - 1;
            } else {
                return mid + 1;
            }
        }
        return left;
    }

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = (int) 1e9 + 7;
        int n = nums1.length;
        int sum = 0, maxReduce = 0;
        int[] copy = new int[n];
        System.arraycopy(nums1, 0, copy, 0, n);
        Arrays.sort(copy);
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum += diff;
            sum = sum >= MOD ? sum - MOD : sum;
            int index = binarySearch(copy, nums2[i]);
            if (index > 0) {
                maxReduce = Math.max(maxReduce, diff - Math.abs(copy[index - 1] - nums2[i]));
            }
            if (index < n) {
                maxReduce = Math.max(maxReduce, diff - Math.abs(copy[index] - nums2[i]));
            }
        }
        int answer = sum - maxReduce;
        return answer >= 0 ? answer : answer + MOD;
    }
}
