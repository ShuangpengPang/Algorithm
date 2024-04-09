package com.shuangpeng.Problem.p2801_2900;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2855MinimumRightShiftsToSortTheArray（使数组成为递增数组的最少右移次数）
 * @date 2024/4/9 11:21 AM
 */
public class Problem2855MinimumRightShiftsToSortTheArray {

    public int minimumRightShifts0(List<Integer> nums) {
        int n = nums.size(), left = 0, right = n - 1;
        while (left < right && nums.get(left) > nums.get(right)) {
            int mid = left + (right - left >> 1);
            if (nums.get(mid) > nums.get(right)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = nums.get((i + left) % n);
            if (num < prev) {
                return -1;
            }
            prev = num;
        }
        return left == 0 ? 0 : n - left;
    }

    public int minimumRightShifts(List<Integer> nums) {
        int n = nums.size(), i = 1;
        while (i < n && nums.get(i - 1) < nums.get(i)) {
            i++;
        }
        for (int j = i; j < n; j++) {
            if (nums.get(j) > nums.get(0) || j > i && nums.get(j - 1) > nums.get(j)) {
                return -1;
            }
        }
        return n - i;
    }
}
