package com.shuangpeng.competition.第251到260场周赛.第259场周赛;

public class Problem2012 {

    public int sumOfBeauties0(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n - 1; ++i) {
            left[i] = Math.max(left[i - 1], nums[i - 1]);
            right[n - i - 1] = Math.min(right[n - i], nums[n - i]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (nums[i] > left[i] && nums[i] < right[i]) {
                ans += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ++ans;
            }
        }
        return ans;
    }

    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        for (int i = 1; i < n - 1; ++i) {
            left[i] = Math.max(left[i - 1], nums[i - 1]);
        }
        int ans = 0;
        int minValue = nums[n - 1];
        for (int i = n - 2; i >= 1; --i) {
            if (nums[i] > left[i] && nums[i] < minValue) {
                ans += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ++ans;
            }
            minValue = Math.min(minValue, nums[i]);
        }
        return ans;
    }
}
