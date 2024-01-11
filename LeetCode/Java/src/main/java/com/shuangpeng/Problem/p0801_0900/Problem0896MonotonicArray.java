package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0896MonotonicArray（单调数列）
 * @date 2024/1/12 12:12 AM
 */
public class Problem0896MonotonicArray {

    public boolean isMonotonic(int[] nums) {
        int n = nums.length, i = 1;
        while (i < n && nums[i] >= nums[i - 1]) {
            i++;
        }
        if (i == n) {
            return true;
        }
        i = 1;
        while (i < n && nums[i] <= nums[i - 1]) {
            i++;
        }
        return i == n;
    }
}
