package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0867TransposeMatrix（转置矩阵）
 * @date 2024/1/12 11:35 PM
 */
public class Problem0867TransposeMatrix {

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }
}
