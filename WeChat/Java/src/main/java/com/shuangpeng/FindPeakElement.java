package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/juq7uS0htBeb-EyUfNq8xA
// LeetCode链接：https://leetcode.com/problems/find-peak-element/
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end - 1) {
            int mid = (start + end) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid + 1] < nums[mid]) {
                return mid;
            } else if (nums[mid - 1] > nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] > nums[end]) {
            return start;
        } else {
            return end;
        }
    }
}
