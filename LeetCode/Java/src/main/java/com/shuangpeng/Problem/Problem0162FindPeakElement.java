package com.shuangpeng.Problem;

public class Problem0162FindPeakElement {

    public int findPeakElement0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left < right - 1) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid - 1]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (left == right || nums[left] > nums[right]) {
            return left;
        } else {
            return right;
        }
    }

    public int findPeakElement1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i - 1;
            }
        }
        return nums.length - 1;
    }

    public int findPeakElement3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return recurse(nums, 0, nums.length - 1);
    }

    public int recurse(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }
        int mid = (start + end) >> 1;
        if (nums[mid] < nums[mid + 1]) {
            return recurse(nums, mid + 1, end);
        } else {
            return recurse(nums, start, mid);
        }
    }

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }
}
