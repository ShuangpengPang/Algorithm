package com.shuangpeng;

public class Problem0494TargetSum {

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        sum -= S;
        if ((sum & 1) == 1 || sum < 0) {
            return 0;
        }
        sum >>>= 1;
        int min = Math.min(sum, sum + S);
        int[] dp = new int[min + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = min; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[min];
    }
}
