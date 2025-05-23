package com.shuangpeng.Problem.p1601_1700;


/**
 * @program: Algorithm
 * @description: Problem1685SumOfAbsoluteDifferencesInASortedArray（有序数组中差绝对值之和）
 * @author: ShuangPengPang
 * @create: 2025-05-23 19:16
 */
public class Problem1685SumOfAbsoluteDifferencesInASortedArray {

    public int[] getSumAbsoluteDifferences(int[] nums) {
        int sum = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            sum += nums[i] - nums[0];
        }
        int[] ans = new int[n];
        ans[0] = sum;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + ((i << 1) - n) * (nums[i] - nums[i - 1]);
        }
        return ans;
    }
}
