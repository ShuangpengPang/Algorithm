package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;

/**
 * @Description: Problem0719FindKthSmallestPairDistance（找出第K小的数对距离）
 * @Date 2022/6/15 7:24 PM
 * @Version 1.0
 */
public class Problem0719FindKthSmallestPairDistance {

    public int smallestDistancePair0(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = left + (right - left >> 1);
            int cnt = 0;
            for (int i = 0; i < n - 1; i++) {
                cnt += binarySearch(nums, nums[i] + mid) - i;
            }
            if (k > cnt) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int binarySearch(int[] nums, int data) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (data >= nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (getCount(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int getCount(int[] nums, int diff) {
        int count = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            while (nums[r] - nums[l] > diff) {
                ++l;
            }
            count += r - l;
        }
        return count;
    }
}
