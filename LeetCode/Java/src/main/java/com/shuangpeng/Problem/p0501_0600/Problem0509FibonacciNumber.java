package com.shuangpeng.Problem.p0501_0600;

public class Problem0509FibonacciNumber {

    public int fib0(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= n; ++i) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int[][] T = {
                {0, 1},
                {1, 1}
        };
        return matrixPower(T, n)[0][1];
    }

    private int[][] matrixPower(int[][] T, int exp) {
        if (exp == 0) {
            return new int[][]{{1, 0}, {0, 1}};
        }
        if ((exp & 1) == 1) {
            return matrixMultiply(matrixPower(T, exp - 1), T);
        }
        int[][] A = matrixPower(T, exp >> 1);
        return matrixMultiply(A, A);
    }

    private int[][] matrixMultiply(int[][] A, int[][] B) {
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
}
