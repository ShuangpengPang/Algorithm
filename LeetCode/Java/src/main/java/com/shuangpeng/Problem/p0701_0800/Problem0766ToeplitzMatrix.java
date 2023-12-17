package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0766ToeplitzMatrix（托普利茨矩阵）
 * @date 2023/12/17 5:05 PM
 */
public class Problem0766ToeplitzMatrix {

    public boolean isToeplitzMatrix0(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int r = i + 1, c = 1; r < m && c < n; r++, c++) {
                if (matrix[r][c] != matrix[r - 1][c - 1]) {
                    return false;
                }
            }
        }
        for (int j = 1; j < n; j++) {
            for (int r = 1, c = j + 1; r < m && c < n; r++, c++) {
                if (matrix[r][c] != matrix[r - 1][c - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
