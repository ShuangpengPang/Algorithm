package com.shuangpeng.Problem;

public class Problem1043PartitionArrayForMaximumSum {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int s = Math.max(1, i - k + 1);
            int maxValue = arr[i - 1];
            for (int j = i; j >= s; --j) {
                maxValue = Math.max(maxValue, arr[j - 1]);
                dp[i] = Math.max(dp[i], dp[j - 1] + maxValue * (i - j + 1));
            }
        }
        return dp[n];
    }
}
