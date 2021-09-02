package com.shuangpeng.competition.第256场周赛;

import java.util.Arrays;

public class Problem1984 {

    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < n; ++i) {
            minValue = Math.min(minValue, nums[i + k - 1] - nums[i]);
        }
        return minValue;
    }
}
