package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.Set;

public class Problem0073SetMatrixZeroes {

//    public static void main(String[] args) {
//        Problem0073SetMatrixZeroes a = new Problem0073SetMatrixZeroes();
//        int[][] matrix = {{1}, {0}};
//        a.setZeroes(matrix);
//    }

    public void setZeroes0(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        Set<Integer> rowSet = new HashSet<>(rows);
        Set<Integer> colSet = new HashSet<>(cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }
        for (int row : rowSet) {
            for (int i = 0; i < cols; i++) {
                matrix[row][i] = 0;
            }
        }
        for (int col : colSet) {
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = 0;
            }
        }
    }

    public void setZeroes1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int mark = 1000010111;
        boolean zeroRow = false;
        boolean zeroCol = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && matrix[i][j] == 0) {
                    zeroRow = true;
                }
                if (j == 0 && matrix[i][j] == 0) {
                    zeroCol = true;
                }
                if (matrix[i][j] == 0) {
                    matrix[i][0] = mark;
                    matrix[0][j] = mark;
                }
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i == 0 || j == 0) {
                    if (i == 0 && (zeroRow || matrix[i][j] == mark)) {
                        matrix[i][j] = 0;
                    } else if (j == 0 && (zeroCol || matrix[i][j] == mark)) {
                        matrix[i][j] = 0;
                    }
                } else if (matrix[i][0] == mark || matrix[0][j] == mark) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    public void setZeroes2(int[][] matrix) {
        Boolean isCol = false;
        int R = matrix.length;
        int C = matrix[0].length;

        for (int i = 0; i < R; i++) {

            // Since first cell for both first row and first column is the same i.e. matrix[0][0]
            // We can use an additional variable for either the first row/column.
            // For this solution we are using an additional variable for the first column
            // and using matrix[0][0] for the first row.
            if (matrix[i][0] == 0) {
                isCol = true;
            }

            for (int j = 1; j < C; j++) {
                // If an element is zero, we set the first element of the corresponding row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Iterate over the array once again and using the first row and first column, update the elements.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // See if the first row needs to be set to zero as well
        if (matrix[0][0] == 0) {
            for (int j = 0; j < C; j++) {
                matrix[0][j] = 0;
            }
        }

        // See if the first column needs to be set to zero as well
        if (isCol) {
            for (int i = 0; i < R; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean isCol = false;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                isCol = true;
            }
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (matrix[0][0] == 0) {
            for (int i = 1; i < cols; i++) {
                matrix[0][i] = 0;
            }
        }
        if (isCol) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
