package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0837New21Game {

    public double new21Game0(int n, int k, int maxPts) {
        if (k + maxPts - 1 <= n) {
            return 1;
        }
        if (n < k) {
            return 0;
        }
        double p = 1.0 / maxPts;
        double[] dp = new double[k + maxPts];
        dp[0] = 1;
        for (int i = 0; i < k; ++i) {
            for (int j = 1; j <= maxPts; ++j) {
                dp[i + j] += dp[i] * p;
            }
        }
        double target = 0;
        double total = 0;
        for (int i = k; i < k + maxPts; ++i) {
            total += dp[i];
            if (i <= n) {
                target += dp[i];
            }
        }
        return target / total;
    }

    //        double[][] T = {
//                {1, p, p, p},
//                {0, 1, p, p},
//                {0, 0, 1, p},
//                {0, 0, 0, 1},
//        };
    public double new21Game1(int n, int k, int maxPts) {
        if (k + maxPts - 1 <= n) {
            return 1;
        }
        if (n < k) {
            return 0;
        }
        double p = 1.0 / maxPts;
        double[][] T = new double[maxPts][maxPts];
        for (int i = 0; i < maxPts; ++i) {
            for (int j = 0; j < maxPts; ++j) {
                if (i == j) {
                    T[i][j] = 1;
                } else if (i < j) {
                    T[i][j] = p;
                }
            }
        }
        double[][] a = new double[1][maxPts];
        Arrays.fill(a[0], p);
        double[][] result = matrixMulti(a, matrixPower(T, k - 1));
        double answer = 0;
        for (int i = 0; i < maxPts; ++i) {
            if (i <= (n - k)) {
                answer += result[0][i];
            }
        }
        return answer / Arrays.stream(result[0]).sum();
    }

    private double[][] matrixPower(double[][] a, int exp) {
        int n = a.length;
        if (exp == 0) {
            double[][] b = new double[n][n];
            for (int i = 0; i < n; ++i) {
                b[i][i] = 1;
            }
            return b;
        }
        if (exp == 1) {
            return a;
        }
        if ((exp & 1) == 1) {
            return matrixMulti(matrixPower(a, exp - 1), a);
        }
        double[][] b = matrixPower(a, exp >> 1);
        return matrixMulti(b, b);
    }

    private double[][] matrixMulti(double[][] a, double[][] b) {
        int m = a.length, n = b[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < b.length; ++k) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1;
        }
        double[] dp = new double[k + maxPts];
        for (int i = k; i <= n && i < k + maxPts; ++i) {
            dp[i] = 1;
        }
        dp[k - 1] = (double) Math.min(n - k + 1, maxPts) / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            dp[i] = dp[i + 1] + (dp[i + 1] - dp[i + maxPts + 1]) / maxPts;
        }
        return dp[0];
    }

//    public static void main(String[] args) {
//        Problem0837New21Game a = new Problem0837New21Game();
//        double result = a.new21Game(6, 1, 10);
//        int i = 1;
//    }
}
