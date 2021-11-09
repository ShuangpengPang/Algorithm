package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

public class Problem0931MinimumFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
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
}
