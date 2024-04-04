package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2529MaximumCountOfPositiveIntegerAndNegativeInteger（正整数和负整数的最大计数）
 * @date 2024/4/4 11:10 PM
 */
public class Problem2529MaximumCountOfPositiveIntegerAndNegativeInteger {

    public int maximumCount0(int[] nums) {
        int n = nums.length, i = 0;
        while (i < n && nums[i] < 0) {
            i++;
        }
        int j = i;
        while (j < n && nums[j] == 0) {
            j++;
        }
        return Math.max(i, n - j);
    }

    public int maximumCount1(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int count = left;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] == 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Math.max(count, nums.length - left);
    }

    public int maximumCount(int[] nums) {
        int n = nums.length, left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int count = left;
        right = n;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (nums[mid] == 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return Math.max(count, n - left);
    }
}
