package com.shuangpeng.offer;

public class Offer10I {

    public int fib0(int n) {
        if (n <= 1) {
            return n;
        }
        final int M = (int) 1e9 + 7;
        int a = 0, b = 1;
        for (int i = 2; i <= n; ++i) {
            int temp = b;
            b += a;
            if (b >= M) {
                b -= M;
            }
            a = temp;
        }
        return b;
    }

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[][] T = {{0, 1}, {1, 1}};
        return matrixPower(T, n)[0][1];
    }

    private int[][] matrixPower(int[][] A, int exp) {
        if (exp == 0) {
            return new int[][]{{1, 0}, {0, 1}};
        }
        if ((exp & 1) == 1) {
            return matrixMultiply(matrixPower(A, exp - 1), A);
        }
        int[][] B = matrixPower(A, exp >> 1);
        return matrixMultiply(B, B);
    }

    private int[][] matrixMultiply(int[][] A, int[][] B) {
        int m = A.length, n = B[0].length;
        final int M = (int) 1e9 + 7;
        int[][] C = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < B.length; ++k) {
                    C[i][j] += ((long) A[i][k] * B[k][j]) % M;
                    if (C[i][j] >= M) {
                        C[i][j] -= M;
                    }
                }
            }
        }
        return C;
    }
}
