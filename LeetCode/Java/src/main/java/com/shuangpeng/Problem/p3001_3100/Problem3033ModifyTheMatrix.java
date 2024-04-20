package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3033ModifyTheMatrix（修改矩阵）
 * @date 2024/4/20 4:34 PM
 */
public class Problem3033ModifyTheMatrix {

    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            for (int i = 0; i < m; i++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = max;
                }
            }
        }
        return matrix;
    }
}
