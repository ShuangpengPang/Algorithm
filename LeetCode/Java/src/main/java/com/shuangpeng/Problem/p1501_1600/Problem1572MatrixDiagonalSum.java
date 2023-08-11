package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1572MatrixDiagonalSum（矩阵对角线元素的和）
 * @date 2023/8/11 12:13 AM
 */
public class Problem1572MatrixDiagonalSum {

    public int diagonalSum(int[][] mat) {
        int n = mat.length, h = n >> 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - i - 1];
        }
        return sum - mat[h][h] * (n & 1);
    }
}
