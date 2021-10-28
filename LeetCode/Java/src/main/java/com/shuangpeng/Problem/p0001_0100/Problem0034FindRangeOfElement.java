package com.shuangpeng.Problem.p0001_0100;

public class Problem0034FindRangeOfElement {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
//        if (nums.length == 1) {
//            if (nums[0] == target) {
//                return new int[]{0, 0};
//            } else {
//                return new int[]{-1, -1};
//            }
//        }
        int left = searchLeft(nums, target);
        if (left == -1) {
            return new int[]{-1, -1};
        }
        int right = searchRight(nums, target);
        return new int[]{left, right};
    }

    public int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (right + 1 >= nums.length) {
            return -1;
        }
        return nums[right + 1] == target ? right + 1 : -1;
    }

    public int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }
        if (left - 1 < 0) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1 : -1;
    }

    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }
}
