package com.shuangpeng.competition.第232场周赛;

public class Problem1793 {

    public int maximumScore(int[] nums, int k) {
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
}
