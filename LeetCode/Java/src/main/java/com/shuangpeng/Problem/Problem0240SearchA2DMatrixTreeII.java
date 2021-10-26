package com.shuangpeng.Problem;

public class Problem0240SearchA2DMatrixTreeII {

//    public static void main(String[] args) {
//        Problem0240SearchA2DMatrixTreeII a = new Problem0240SearchA2DMatrixTreeII();
//        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
////        int[][] matrix = {{-1}, {-1}};
//        a.searchMatrix(matrix, 20);
//    }

    // 二分查找
    public boolean searchMatrix0(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int min = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < min; i++) {
            if (binarySearch(matrix, target, i, false)) {
                return true;
            }
            if (binarySearch(matrix, target, i, true)) {
                return true;
            }
        }
        return false;
    }


    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix.length - 1 : matrix[0].length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int data;
            if (vertical) {
                data = matrix[mid][start];
            } else {
                data = matrix[start][mid];
            }
            if (target < data) {
                hi = mid - 1;
            } else if (target > data) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean partition0(int[][] matrix, int target,
                              int startRow, int endRow, int startCol, int endCol) {
        if (startRow == endRow && startCol == endCol) {
            if (matrix[startRow][startCol] == target) {
                return true;
            } else {
                return false;
            }
        }

        int midRow = (startRow + endRow) >>> 1;
        int midCol = (startCol + endCol) >>> 1;
        int data = matrix[midRow][midCol];
        if (target < data) {
            // 左上
            if (partition0(matrix, target, startRow, midRow, startCol, midCol)) {
                return true;
            }
        } else if (target > data) {
            // 右下
            if (midRow == startRow && midCol == startCol) {
                if (midRow < endRow) {
                    midRow++;
                }
                if (midCol < endCol) {
                    midCol++;
                }
            }
            if (partition0(matrix, target, midRow, endRow, midCol, endCol)) {
                return true;
            }
        } else if (target == data) {
            return true;
        }

        if (startRow != endRow && startCol != endCol) {
            // 右上
            if (partition0(matrix, target, startRow, midRow, midCol, endCol)) {
                return true;
            }
            // 左下
            if (partition0(matrix, target, midRow, endRow, startCol, midCol)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
//        return partition0(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
        return partition(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    public boolean partition(int[][] matrix, int target,
                              int startRow, int endRow, int startCol, int endCol) {
        if (startRow > endRow || startCol > endCol) {
            return false;
        }
        int midRow = (startRow + endRow) >>> 1;
        int midCol = (startCol + endCol) >>> 1;
        int data = matrix[midRow][midCol];
        if (target < data) {
            if (partition(matrix, target, startRow, endRow, startCol, midCol - 1)) {
                return true;
            }
            if (partition(matrix, target, startRow, midRow - 1, midCol, endCol)) {
                return true;
            }
        } else if (target > data) {
            if (partition(matrix, target, midRow + 1, endRow, startCol, endCol)) {
                return true;
            }
            if (partition(matrix, target, startRow, midRow, midCol + 1, endCol)) {
                return true;
            }
        } else if (target == data) {
            return true;
        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (target < matrix[i][j]) {
                i--;
            } else if (target > matrix[i][j]) {
                j++;
            } else if (target == matrix[i][j]) {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (target > matrix[r][c]) {
                ++r;
            } else if (target < matrix[r][c]) {
                --c;
            } else {
                return true;
            }
        }
        return false;
    }
}
