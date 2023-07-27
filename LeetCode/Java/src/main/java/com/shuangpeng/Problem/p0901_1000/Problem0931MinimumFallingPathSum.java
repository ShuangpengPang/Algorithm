package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @description:（下降路径最小和）
 * @date 2023/7/13 11:11 AM
 **/
public class Problem0931MinimumFallingPathSum {

    public int minFallingPathSum0(int[][] matrix) {
        int n = matrix.length;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] += matrix[i - 1][j];
                if (j > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], temp + matrix[i - 1][j - 1]);
                }
                if (j < n - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], temp + matrix[i - 1][j + 1]);
                }
            }
        }
        return Arrays.stream(matrix[n - 1]).min().getAsInt();
    }

    public int minFallingPathSum1(int[][] matrix) {
        int n = matrix.length;
        int[] dp = new int[n + 2];
        dp[0] = dp[n + 1] = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 1, prev = dp[0]; j <= n; j++) {
                int p = prev;
                prev = dp[j];
                dp[j] = Math.min(Math.min(p, prev), dp[j + 1]) + matrix[i][j - 1];
            }
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length, N = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] += Math.min(Math.min(j > 0 ? matrix[i - 1][j - 1] : N, matrix[i - 1][j]), j + 1 < n ? matrix[i - 1][j + 1] : N);
            }
        }
        return Arrays.stream(matrix[n - 1]).min().getAsInt();
    }
}
