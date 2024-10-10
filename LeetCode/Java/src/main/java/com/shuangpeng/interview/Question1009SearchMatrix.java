package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question1009SearchMatrix（面试题 10.09. 排序矩阵查找）
 * @date 2024/10/10 7:21 PM
 */
public class Question1009SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
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
}
