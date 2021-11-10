package com.shuangpeng.Problem.p1101_1200;

public class Problem1137NthTribonacciNumber {

    public int tribonacci0(int n) {
        if (n <= 2) {
            return (n + 1) >> 1;
        }
        int t0 = 0, t1 = 1, t2 = 1;
        for (int i = 3; i <= n; ++i) {
            int temp = t2;
            t2 = t0 + t1 + t2;
            t0 = t1;
            t1 = temp;
        }
        return t2;
    }

    public int tribonacci(int n) {
        if (n <= 2) {
            return (n + 1) >> 1;
        }
        int[][] T = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1}
        };
        int[][] A = {{0}, {1}, {1}};
        return matrixMulti(matrixExp(T, n - 2), A)[2][0];
    }

    private int[][] matrixMulti(int[][] A, int[][] B) {
        int m = A.length, n = B[0].length;
        int[][] C = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < B.length; ++k) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private int[][] matrixExp(int[][] A, int exp) {
        if (exp == 0) {
            return new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        }
        if ((exp & 1) == 1) {
            return matrixMulti(matrixExp(A, exp - 1), A);
        }
        int[][] B = matrixExp(A, exp >> 1);
        return matrixMulti(B, B);
    }
}
