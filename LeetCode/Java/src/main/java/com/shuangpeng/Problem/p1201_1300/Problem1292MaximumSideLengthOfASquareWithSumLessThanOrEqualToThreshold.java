package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1292MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold（元素和小于等于阈值的正方形的最大边长）
 * @date 2023/7/5 2:04 PM
 */
public class Problem1292MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0, s = 0; j < n; j++) {
                s += mat[i][j];
                dp[i + 1][j + 1] = dp[i][j + 1] + s;
            }
        }
        int ans = 0, left = 1, right = Math.min(m, n);
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(dp, mid, threshold)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[][] dp, int length, int threshold) {
        int m = dp.length, n = dp[0].length;
        for (int i = length; i < m; i++) {
            for (int j = length; j< n; j++) {
                if (dp[i][j] - dp[i - length][j] - dp[i][j - length] + dp[i - length][j - length] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}
