package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3105LongestStrictlyIncreasingOrStrictlyDecreasingSubarray（最长的严格递增或递减子数组）
 * @date 2024/4/23 11:56 AM
 */
public class Problem3105LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length, i = 1, ans = 1;
        while (i < n) {
            int m = 1;
            while (i < n && nums[i - 1] < nums[i]) {
                i++;
                m++;
            }
            ans = Math.max(ans, m);
            while (i < n && nums[i - 1] == nums[i]) {
                i++;
            }
            m = 1;
            while (i < n && nums[i - 1] > nums[i]) {
                i++;
                m++;
            }
            ans = Math.max(ans, m);
        }
        return ans;
    }
}
