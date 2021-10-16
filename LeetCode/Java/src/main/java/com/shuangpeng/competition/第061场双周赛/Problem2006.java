package com.shuangpeng.competition.第061场双周赛;

public class Problem2006 {

    public int countKDifference0(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    public int countKDifference(int[] nums, int k) {
        final int M = 101;
        int[] count = new int[M];
        for (int num : nums) {
            ++count[num];
        }
        int ans = 0;
        for (int i = 1; i + k < M; ++i) {
            ans += count[i] * count[i + k];
        }
        return ans;
    }
}
