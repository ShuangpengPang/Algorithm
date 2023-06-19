package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1283FindTheSmallestDivisorGivenAThreshold（使结果不超过阈值的最小除数）
 * @date 2023/6/19 2:16 PM
 */
public class Problem1283FindTheSmallestDivisorGivenAThreshold {

    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 0, n = nums.length;
        for (int num : nums) {
            right = Math.max(right, num);
        }
        while (left <= right) {
            int mid = left + (right - left >> 1), sum = 0;
            for (int i = 0; i < n && sum <= threshold; i++) {
                sum += (nums[i] + mid - 1) / mid;
            }
            if (sum > threshold) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
