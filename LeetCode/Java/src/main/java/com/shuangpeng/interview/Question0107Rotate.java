package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0107Rotate（面试题 01.07. 旋转矩阵）
 * @date 2024/8/21 7:50 PM
 */
public class Question0107Rotate {

    public void rotate0(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = matrix[n - j - 1][i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ans[i][j];
            }
        }
    }

    public void rotate1(int[][] matrix) {
        int n = matrix.length, h = n >> 1;
        for (int i = 0; i < h; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = t;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n >> 1; i++) {
            for (int j = 0; j < n + 1 >> 1; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = t;
            }
        }
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n >> 1; i++) {
            for (int j = 0; j < n; j++) {
                swap(matrix, i, j, n - i - 1, j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        matrix[x1][y1] = matrix[x1][y1] ^ matrix[x2][y2];
        matrix[x2][y2] = matrix[x1][y1] ^ matrix[x2][y2];
        matrix[x1][y1] = matrix[x1][y1] ^ matrix[x2][y2];
    }
}
