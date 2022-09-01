package com.shuangpeng.competition.双周赛.第059场双周赛;

public class Problem1975 {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int minValue = Integer.MAX_VALUE;
        boolean flag = true;
        long sum = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int value = Math.abs(matrix[i][j]);
                minValue = Math.min(minValue, value);
                sum += value;
                if (matrix[i][j] < 0) {
                    flag = !flag;
                }
            }
        }
        return flag ? sum : sum - (minValue << 1);
    }
}
