package com.shuangpeng.Problem;

public class Problem0813LargestSumOfAverages {

    public double largestSumOfAverages0(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + nums[i];
        }
        double[][] dp = new double[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i][1] = (double) sum[i] / i;
            for (int p = 2; p <= k; ++p) {
                for (int j = i; j >= 1; --j) {
                    dp[i][p] = Math.max(dp[i][p], dp[j - 1][p - 1] + (double) (sum[i] - sum[j - 1]) / (i - j + 1));
                }
            }
        }
        return dp[n][k];
    }

    public double largestSumOfAverages1(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + nums[i];
        }
        double[] dp = new double[n + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i] = (double) sum[i] / i;
        }
        for (int p = 2; p <= k; ++p) {
            for (int i = n; i >= 1; --i) {
                for (int j = 1; j <= i; ++j) {
                    dp[i] = Math.max(dp[i], dp[j - 1] + (double) (sum[i] - sum[j - 1]) / (i - j + 1));
                }
            }
        }
        return dp[n];
    }

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + nums[i];
        }
        double[] dp = new double[n + 2];
        for (int i = n; i >= 1; --i) {
            dp[i] = (double) (sum[n] - sum[i - 1]) / (n - i + 1);
        }
        for (int p = 2; p <= k; ++p) {
            for (int i = 1; i <= n; ++i) {
                for (int j = i; j <= n; ++j) {
                    dp[i] = Math.max(dp[i], (double) (sum[j] - sum[i - 1]) / (j - i + 1) + dp[j + 1]);
                }
            }
        }
        return dp[1];
    }
}
