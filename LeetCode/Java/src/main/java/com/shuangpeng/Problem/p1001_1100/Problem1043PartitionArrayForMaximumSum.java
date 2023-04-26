package com.shuangpeng.Problem.p1001_1100;

/**
 * @description: 分割数组以得到最大和
 * @date 2023/4/19 10:49 AM
 **/
public class Problem1043PartitionArrayForMaximumSum {

    public int maxSumAfterPartitioning0(int[] arr, int k) {
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

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i, m = 0; j >= 1 && i - j < k; j--) {
                m = Math.max(m, arr[j - 1]);
                dp[i] = Math.max(dp[i], dp[j - 1] + m * (i - j + 1));
            }
        }
        return dp[n];
    }
}
