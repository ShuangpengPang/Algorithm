package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/_SANAykO8jbl-wBMhyLLWg
// LeetCode链接：https://leetcode.com/problems/container-with-most-water/submissions/
public class MaxArea {
    public int maxArea0(int[] height) {
        int[] area = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            int max = area[i - 1];
            for (int j = 0; j < i; j++) {
                max = Math.max(max, Math.min(height[i], height[j]) * (i - j));
            }
            area[i] = max;
        }
        return area[height.length - 1];
    }

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max = Integer.MIN_VALUE;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
