package com.shuangpeng.competition.第251到260场周赛.第260场周赛;

public class Problem2016 {

    // 比赛时写法
    public int maximumDifference0(int[] nums) {
        int n = nums.length;
        int maxDiff = -1, minValue = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] < minValue) {
                minValue = nums[i];
            } else {
                maxDiff = Math.max(maxDiff, nums[i] - minValue);
            }
        }
        return maxDiff > 0 ? maxDiff : -1;
    }

    public int maximumDifference(int[] nums) {
        int minValue = nums[0];
        int ans = -1;
        for (int i = 1 ; i < nums.length; ++i) {
            ans = Math.max(ans, nums[i] - minValue);
            minValue = Math.min(minValue, nums[i]);
        }
        return ans > 0 ? ans : -1;
    }
}
