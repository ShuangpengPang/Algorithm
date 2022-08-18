package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1458MaximumDotProductOfTwoSubsequences（两个子序列的最大点积）
 * @Date 2022/8/18 3:26 PM
 * @Version 1.0
 */
public class Problem1458MaximumDotProductOfTwoSubsequences {

    public int maxDotProduct0(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            int pre = Integer.MIN_VALUE;
            for (int j = 0; j < n2; j++) {
                dp[i][j] = Math.max(pre, nums1[0] * nums2[j]);
                for (int k = 1; k <= i; k++) {
                    dp[i][j] = Math.max(dp[i][j], (j > 0 ? Math.max(0, dp[k - 1][j - 1]) : 0) + nums1[k] * nums2[j]);
                }
                pre = dp[i][j];
            }
        }
        return dp[n1 - 1][n2 - 1];
    }

    public int maxDotProduct1(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                int product = nums1[i] * nums2[j];
                dp[i][j] = product;
                if (i > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + product);
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] dp = new int[n2];
        Arrays.fill(dp, Integer.MIN_VALUE >> 1);
        for (int i = 0; i < n1; i++) {
            int pre = Integer.MIN_VALUE, last = Integer.MIN_VALUE >> 1;
            for (int j = 0; j < n2; j++) {
                int product = nums1[i] * nums2[j];
                pre = Math.max(pre, dp[j]);
                pre = Math.max(pre, product);
                pre = Math.max(pre, last + product);
                last = dp[j];
                dp[j] = pre;
            }
        }
        return dp[n2 - 1];
    }
}

