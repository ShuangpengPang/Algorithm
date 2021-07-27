package com.shuangpeng.offer;

public class Offer53 {

    public int search0(int[] nums, int target) {
        int answer = 0;
        for (int num : nums) {
            if (num == target) {
                answer++;
            }
        }
        return answer;
    }

    public int search1(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int start = left;
        if (start < 0 || start >= n || nums[start] != target) {
            return 0;
        }
        left = 0;
        right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - start;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int start = binarySearch(nums, target, true);
        if (start >= n || nums[start] != target) {
            return 0;
        }
        int end = binarySearch(nums, target, false);
        return end - start;
    }

    private int binarySearch(int[] nums, int target, boolean low) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (low) {
                if (target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return left;
    }
}
