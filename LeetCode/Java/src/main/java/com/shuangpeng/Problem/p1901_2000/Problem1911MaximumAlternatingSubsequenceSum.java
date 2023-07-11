package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1911MaximumAlternatingSubsequenceSum（最大子序列交替和）
 * @date 2023/7/11 10:31 AM
 */
public class Problem1911MaximumAlternatingSubsequenceSum {

    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int prev = 0, i = 0, n = nums.length;
        while (i < n) {
            while (i + 1 < n && nums[i] <= nums[i + 1]) {
                i++;
            }
            ans += nums[i] - prev;
            while (i + 1 < n && nums[i] >= nums[i + 1]) {
                i++;
            }
            if (i < n) {
                prev = nums[i];
            }
            i++;
        }
        return ans;
    }
}
