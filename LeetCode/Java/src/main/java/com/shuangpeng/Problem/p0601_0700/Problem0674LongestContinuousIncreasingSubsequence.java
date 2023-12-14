package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0674LongestContinuousIncreasingSubsequence（最长连续递增序列）
 * @date 2023/12/15 12:21 AM
 */
public class Problem0674LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length, ans = 1, len = 1;
        for (int i = 1; i < n; i++) {
            len = nums[i] > nums[i - 1] ? len + 1 : 1;
            ans = Math.max(ans, len);
        }
        return ans;
    }
}
