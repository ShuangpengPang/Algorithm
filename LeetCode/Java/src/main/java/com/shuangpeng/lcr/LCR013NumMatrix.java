package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR013NumMatrix（二维区域和检索-矩阵不可变）
 * @date 2024/6/13 7:26 PM
 */
public class LCR013NumMatrix {

    class NumMatrix {

        private int[][] matrix;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length + 1, n = matrix[0].length + 1;
            this.matrix = new int[m][n];
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    this.matrix[i][j] = this.matrix[i - 1][j] + this.matrix[i][j - 1] + matrix[i - 1][j - 1] - this.matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return matrix[row2 + 1][col2 + 1] + matrix[row1][col1] - matrix[row1][col2 + 1] - matrix[row2 + 1][col1];
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
}
