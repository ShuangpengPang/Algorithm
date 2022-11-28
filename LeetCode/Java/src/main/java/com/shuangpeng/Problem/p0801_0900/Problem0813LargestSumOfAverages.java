package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;

/**
 * @description: 最大平均值和的分组
 * @date 2022/11/28 3:49 PM
 **/
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

    public double largestSumOfAverages2(int[] nums, int k) {
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


    public double largestSumOfAverages3(int[] nums, int k) {
        int n = nums.length;
        double[] preSum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        double[][] dp = new double[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -Double.MAX_VALUE);
        }
        dp[0][0] = 0.0;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j >= 1; j--) {
                for (int p = i; p >= j; p--) {
                    dp[i][j] = Math.max(dp[i][j], dp[p - 1][j - 1] + (preSum[i] - preSum[p - 1]) / (i - p + 1));
                }
            }
        }
        return Arrays.stream(dp[n]).max().getAsDouble();
    }

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n][k];
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            dp[i][0] = sum / (i + 1);
            for (int j = Math.min(k - 1, i); j > 0; j--) {
                double s = 0.0;
                for (int p = i; p >= j; p--) {
                    s += nums[p];
                    dp[i][j] = Math.max(dp[i][j], dp[p - 1][j - 1] + s / (i - p + 1));
                }
            }
        }
        return Arrays.stream(dp[n - 1]).max().getAsDouble();
    }

//    public static void main(String[] args) {
//        Problem0813LargestSumOfAverages a = new Problem0813LargestSumOfAverages();
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        a.largestSumOfAverages(nums, 4);
//    }
}
