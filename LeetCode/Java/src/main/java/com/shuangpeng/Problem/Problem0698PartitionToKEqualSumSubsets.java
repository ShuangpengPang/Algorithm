package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0698PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets0(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxValue = Math.max(maxValue, nums[i]);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (maxValue > target) {
            return false;
        }
        final int MAX = 1 << n;
        int[] dp = new int[MAX];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int status = 0; status < MAX; status++) {
            if (dp[status] == -1) {
                continue;
            }
            for (int i = n - 1; i >= 0; i--) {
                int m = 1 << (n - i - 1);
                if ((status & m) != 0) {
                    continue;
                }
                int remain = target - dp[status] % target;
                if (nums[i] <= remain) {
                    int s = status | m;
                    dp[s] = dp[status] + nums[i];
                    if (dp[s] == sum - target) {
                        return true;
                    }
                }
            }
        }
        return dp[MAX - 1] != -1;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            maxValue = Math.max(maxValue, nums[i]);
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (maxValue > target) {
            return false;
        }
        return dfs(nums, 0, 0, new Boolean[1 << n], sum - target, target);
    }

    private boolean dfs(int[] nums, int status, int sum, Boolean[] memo, int targetSum, int target) {
        if (memo[status] != null) {
            return memo[status];
        }
        if (sum == targetSum) {
            memo[status] = true;
            return true;
        }
        int remain = target - sum % target;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            int m = 1 << n - i - 1;
            if ((status & m) == 0 && nums[i] <= remain
                    && dfs(nums, status | m, sum + nums[i], memo, targetSum, target)) {
                memo[status] = true;
                return true;
            }
        }
        memo[status] = false;
        return false;
    }

//    public static void main(String[] args) {
//        int[] arr = new int[]{2, 2, 2, 2, 3, 4, 5};
//        Problem0698PartitionToKEqualSumSubsets a = new Problem0698PartitionToKEqualSumSubsets();
//        a.canPartitionKSubsets(arr, 4);
//    }
}
