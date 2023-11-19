package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2270NumberOfWaysToSplitArray（分割数组的方案数）
 * @date 2023/11/19 3:57 PM
 */
public class Problem2270NumberOfWaysToSplitArray {

    public int waysToSplitArray(int[] nums) {
        int n = nums.length, ans = 0;
        long sum = 0, left = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n - 1; i++) {
            left += nums[i];
            sum -= nums[i];
            if (left >= sum) {
                ans++;
            }
        }
        return ans;
    }
}
