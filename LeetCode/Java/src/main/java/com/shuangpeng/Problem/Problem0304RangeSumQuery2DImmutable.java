package com.shuangpeng.Problem;

public class Problem0304RangeSumQuery2DImmutable {

    class NumMatrix {

        private int[][] sumMatrix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int rows = matrix.length;
            int cols = matrix[0].length;
            sumMatrix = new int[rows + 1][cols + 1];
            for (int r = 0; r < rows; r++) {
                int rowSum = 0;
                for (int c = 0; c < cols; c++) {
                    rowSum += matrix[r][c];
                    sumMatrix[r + 1][c + 1] = sumMatrix[r][c + 1] + rowSum;
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumMatrix[row2 + 1][col2 + 1] + sumMatrix[row1][col1]
                    - sumMatrix[row1][col2 + 1] - sumMatrix[row2 + 1][col1];
        }
    }
}
