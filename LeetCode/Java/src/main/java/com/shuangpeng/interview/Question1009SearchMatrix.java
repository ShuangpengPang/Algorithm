package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question1009SearchMatrix（面试题 10.09. 排序矩阵查找）
 * @date 2024/10/10 7:21 PM
 */
public class Question1009SearchMatrix {

    public boolean searchMatrix0(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int i = matrix.length - 1, j = 0, n = matrix[0].length;
        while (i >= 0 && j < n) {
            if (target > matrix[i][j]) {
                j++;
            } else if (target < matrix[i][j]) {
                i--;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        return dfs(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);
    }

    private boolean dfs(int[][] matrix, int r1, int r2, int c1, int c2, int target) {
        if (r1 > r2 || c1 > c2) {
            return false;
        }
        int r = r1 + (r2 - r1 >> 1), c = c1 + (c2 - c1 >> 1);
        if (target < matrix[r][c]) {
            return dfs(matrix, r1, r - 1, c1, c2, target) || dfs(matrix, r, r2, c1, c - 1, target);
        } else if (target > matrix[r][c]) {
            return dfs(matrix, r1, r, c + 1, c2, target) || dfs(matrix, r + 1, r2, c1, c2, target);
        }
        return true;
    }
}
