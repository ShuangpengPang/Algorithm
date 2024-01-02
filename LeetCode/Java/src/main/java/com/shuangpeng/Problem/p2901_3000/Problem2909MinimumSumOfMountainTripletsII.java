package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2909MinimumSumOfMountainTripletsII（元素和最小的山形三元组II）
 * @date 2024/1/3 12:11 AM
 */
public class Problem2909MinimumSumOfMountainTripletsII {

    public int minimumSum(int[] nums) {
        int n = nums.length, N = Integer.MAX_VALUE, ans = N;
        int[] right = new int[n];
        right[n - 1] = N;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i + 1]);
        }
        int left = nums[0];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > left && nums[i] > right[i]) {
                ans = Math.min(ans, left + nums[i] + right[i]);
            }
            left = Math.min(left, nums[i]);
        }
        return ans == N ? -1 : ans;
    }
}
