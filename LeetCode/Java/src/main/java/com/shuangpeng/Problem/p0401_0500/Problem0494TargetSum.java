package com.shuangpeng.Problem.p0401_0500;

public class Problem0494TargetSum {

    public int findTargetSumWays0(int[] nums, int S) {
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

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        int t = sum - target;
        if (t < 0 || (t & 1) == 1) {
            return 0;
        }
        t = (sum - target) >> 1;
        t = Math.min(t, sum - t);
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = t; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[t];
    }
}
