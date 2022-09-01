package com.shuangpeng.competition.第232场周赛;

public class Problem1793 {

    public int maximumScore0(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[k] = nums[k];
        for (int i = k - 1; i >= 0; i--) {
            dp[i] = Math.min(dp[i + 1], nums[i]);
        }
        for (int i = k + 1; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], nums[i]);
        }
        int answer = Integer.MIN_VALUE;
        int i = 0, j = n - 1;
        while (i <= j) {
            answer = Math.max(answer, Math.min(dp[i], dp[j]) * (j - i + 1));
            if (nums[i] < nums[j]) {
                i++;
            } else {
                j--;
            }
        }
        return answer;
    }

    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int minValue = nums[k];
        int i = k, j = k;
        int ans = 0;
        while (i >= 0 || j < n) {
            while (i >= 0 && nums[i] >= minValue) {
                --i;
            }
            while (j < n && nums[j] >= minValue) {
                ++j;
            }
            ans = Math.max(ans, minValue * (j - i - 1));
            if (i < 0 && j >= n) {
                break;
            }
            if (i < 0) {
                minValue = nums[j];
            } else if (j >= n) {
                minValue = nums[i];
            } else {
                minValue = Math.max(nums[i], nums[j]);
            }
        }
        return ans;
    }
}
