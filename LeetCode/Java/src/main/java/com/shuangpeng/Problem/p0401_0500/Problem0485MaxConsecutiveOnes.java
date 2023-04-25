package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0485MaxConsecutiveOnes（最大连续1的个数）
 * @date 2023/4/25 7:48 PM
 */
public class Problem0485MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, ans = 0, count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                count++;
                if (i == n - 1 || nums[i + 1] == 0) {
                    ans = Math.max(ans, count);
                }
            } else {
                count = 0;
            }
        }
        return ans;
    }
}
