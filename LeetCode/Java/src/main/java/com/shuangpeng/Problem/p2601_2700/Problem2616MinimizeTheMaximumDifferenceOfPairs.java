package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2616MinimizeTheMaximumDifferenceOfPairs（最小化数对的最大差值）
 * @date 2025/4/21 11:59
 */
public class Problem2616MinimizeTheMaximumDifferenceOfPairs {

    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int left = 0, right = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            right = Math.max(right, nums[i] - nums[i - 1]);
        }
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(nums, mid, p)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int x, int p) {
        int i = 1, n = nums.length;
        while (i < n && p > 0) {
            if (nums[i] - nums[i - 1] <= x) {
                i += 2;
                p--;
            } else {
                i++;
            }
        }
        return p == 0;
    }
}
