package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3423MaximumDifferenceBetweenAdjacentElementsInACircularArray（循环数组中相邻元素的最大差值）
 * @date 2025/3/12 15:48
 */
public class Problem3423MaximumDifferenceBetweenAdjacentElementsInACircularArray {

    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int ans = Math.abs(nums[0] - nums[n - 1]);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.abs(nums[i - 1] - nums[i]));
        }
        return ans;
    }
}
