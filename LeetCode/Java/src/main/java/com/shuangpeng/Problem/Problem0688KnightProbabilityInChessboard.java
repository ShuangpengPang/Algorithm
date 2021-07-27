package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem0688KnightProbabilityInChessboard {

    public double knightProbability0(int n, int k, int row, int column) {
        if (row >= n || column >= n) {
            return 0;
        }
        int[][] dirs = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        Map<Integer, Double> map = new HashMap<>();
        map.put(row * n + column, 1.0);
        for (int i = 0; i < k; i++) {
            Map<Integer, Double> temp = new HashMap<>();
            for (int key : map.keySet()) {
                double value = map.get(key);
                int r = key / n;
                int c = key % n;
                for (int[] dir : dirs) {
                    int x = r + dir[0];
                    int y = c + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n) {
                        int newKey = x * n + y;
                        temp.put(newKey, temp.getOrDefault(newKey, 0.0) + value);
                    }
                }
            }
            map = temp;
        }
        double sum = 0;
        for (int key : map.keySet()) {
            sum += map.get(key);
        }
        return sum / Math.pow(8, k);
    }

    public double knightProbability(int n, int k, int row, int column) {
        int index = 0;
        int[] map = new int[n * n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r * n + c == canonical(r, c, n)) {
                    map[r * n + c] = index++;
                } else {
                    map[r * n + c] = map[canonical(r, c, n)];
                }
            }
        }
        int[][] dirs = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        double[][] T = new double[index][index];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int key = r * n + c;
                if (key == canonical(r, c, n)) {
                    for (int[] dir : dirs) {
                        int newR = r + dir[0];
                        int newC = c + dir[1];
                        if (newR >= 0 && newR < n && newC >= 0 && newC < n) {
                            T[map[key]][map[newR * n + newC]] += 0.125;
                        }
                    }
                }
            }
        }
        double[][] matrix = matrixExp(T, k);
        return Arrays.stream(matrix[map[row * n + column]]).sum();
    }

    private double[][] matrixExp(double[][] A, int exp) {
        int m = A.length, n = A[0].length;
        if (exp == 0) {
            double[][] d = new double[m][n];
            int k = Math.min(m, n);
            for (int i = 0; i < k; i++) {
                d[i][i] = 1;
            }
            return d;
        }
        if (exp == 1) {
            return A;
        }
        if ((exp & 1) == 1) {
            return matrixMult(matrixExp(A, exp - 1), A);
        }
        double[][] B = matrixExp(A, exp >> 1);
        return matrixMult(B, B);
    }

    private double[][] matrixMult(double[][] A, double[][] B) {
        int mA = A.length, nA = A[0].length, nB = B[0].length;
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++) {
            for (int j = 0; j < nB; j++) {
                for (int k = 0; k < nA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private int canonical(int r, int c, int n) {
        int half = n >> 1;
        if (r > half) {
            r = n - r - 1;
        }
        if (c > half) {
            c = n - c - 1;
        }
        if (r > c) {
            int t = r;
            r = c;
            c = t;
        }
        return r * n + c;
    }
}
