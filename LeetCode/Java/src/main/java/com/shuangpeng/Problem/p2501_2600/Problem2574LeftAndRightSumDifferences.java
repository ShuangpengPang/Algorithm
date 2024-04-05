package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2574LeftAndRightSumDifferences（左右元素和的差值）
 * @date 2024/4/5 3:55 PM
 */
public class Problem2574LeftAndRightSumDifferences {

    public int[] leftRightDifference(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int[] ans = new int[n];
        for (int i = 0, s = 0; i < n; i++) {
            ans[i] = Math.abs(s - (sum - s - nums[i]));
            s += nums[i];
        }
        return ans;
    }
}
