package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1605FindValidMatrixGivenRowAndColumnSums（给定行和列的和求可行矩阵）
 * @date 2023/3/14 10:53 AM
 */
public class Problem1605FindValidMatrixGivenRowAndColumnSums {

    public int[][] restoreMatrix0(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n && rowSum[i] > 0; j++) {
                int num = Math.min(rowSum[i], colSum[j]);
                ans[i][j] = num;
                rowSum[i] -= num;
                colSum[j] -= num;
            }
        }
        return ans;
    }

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] ans = new int[m][n];
        int i = 0, j = 0;
        while (i < m && j < n) {
            int num = Math.min(rowSum[i], colSum[j]);
            ans[i][j] = num;
            rowSum[i] -= num;
            colSum[j] -= num;
            if (rowSum[i] == 0) {
                i++;
            }
            if (colSum[j] == 0) {
                j++;
            }
        }
        return ans;
    }
}
