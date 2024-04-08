package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3107MinimumOperationsToMakeMedianOfArrayEqualToK（使数组中位数等于K的最少操作数）
 * @date 2024/4/8 7:01 PM
 */
public class Problem3107MinimumOperationsToMakeMedianOfArrayEqualToK {

    public long minOperationsToMakeMedianK0(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, m = n >> 1;
        long ans = 0;
        if (nums[m] > k) {
            for (int i = m; i >= 0 && nums[i] > k; i--) {
                ans += nums[i] - k;
            }
        } else {
            for (int i = m; i < n && nums[i] < k; i++) {
                ans += k - nums[i];
            }
        }
        return ans;
    }

    public long minOperationsToMakeMedianK(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, m = n >> 1;
        long ans = 0;
        for (int i = m; i >= 0 && nums[i] > k; i--) {
            ans += nums[i] - k;
        }
        for (int i = m; i < n && nums[i] < k; i++) {
            ans += k - nums[i];
        }
        return ans;
    }
}
