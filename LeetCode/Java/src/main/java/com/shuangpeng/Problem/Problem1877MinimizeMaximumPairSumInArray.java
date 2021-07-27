package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem1877MinimizeMaximumPairSumInArray {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        int half = (n - 1) >> 1;
        for (int i = 0; i <= half; i++) {
            maxSum = Math.max(maxSum, nums[i] + nums[n - i - 1]);
        }
        return maxSum;
    }
}
