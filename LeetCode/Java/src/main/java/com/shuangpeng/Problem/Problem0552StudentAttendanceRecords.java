package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0552StudentAttendanceRecords {

    public int checkRecord0(int n) {
        // _P : 0, PL : 1, LL : 2, _A : 3, AP : 4, AL : 5, ALL : 6
        int MOD = (int) 1e9 + 7;
        if (n <= 0) {
            return 0;
        }
        int[] dp = {1, 1, 0, 1, 0, 0, 0};
        for (int i = 2; i <= n; i++) {
            int[] temp = new int[7];
            temp[0] = ((dp[0] + dp[1]) % MOD + dp[2]) % MOD;
            temp[1] = dp[0];
            temp[2] = dp[1];
            temp[3] = ((dp[0] + dp[1]) % MOD + dp[2]) % MOD;
            temp[4] = ((dp[3] + dp[4]) % MOD + (dp[5] + dp[6]) % MOD) % MOD;
            temp[5] = (dp[3] + dp[4]) % MOD;
            temp[6] = dp[5];
            dp = temp;
        }
        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            answer = (answer + dp[i]) % MOD;
        }
        return answer;
    }

    public int checkRecord1(int n) {
        if (n <= 0) {
            return 0;
        }
        final int MOD = (int) 1e9 + 7;
        int[] dp = {1, 1, 0, 1, 0, 0};
        for (int i = 2; i <= n; i++) {
            int[] temp = new int[6];
            temp[0] = ((dp[0] + dp[1]) % MOD + dp[2]) % MOD;
            temp[1] = dp[0];
            temp[2] = dp[1];
            temp[3] = (((dp[0] + dp[1]) % MOD + (dp[2] + dp[3]) % MOD) % MOD + (dp[4] + dp[5]) % MOD) % MOD;
            temp[4] = dp[3];
            temp[5] = dp[4];
            dp = temp;
        }
        int answer = 0;
        for (int i = 0; i < dp.length; i++) {
            answer = (answer + dp[i]) % MOD;
        }
        return answer;
    }

    public int checkRecord2(int n) {
        final int M = (int) 1e9 + 7;
        long[] dp = {1, 1, 0, 1, 0, 0};
        int length = dp.length;
        for (int i = 1; i < n; ++i) {
            long[] temp = new long[length];
            temp[0] = (dp[0] + dp[1] + dp[2]) % M;
            temp[1] = dp[0];
            temp[2] = dp[1];
            temp[3] = (dp[0] + dp[1] + dp[2] + dp[3] + dp[4] + dp[5]) % M;
            temp[4] = dp[3];
            temp[5] = dp[4];
            dp = temp;
        }
        return (int) (Arrays.stream(dp).sum() % M);
    }

    public int checkRecord(int n) {
        int[][] T = {
                {1, 1, 0, 1, 0, 0},
                {1, 0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0},
        };
        int[] result = matrixPower(T, n)[0];
        final int M = (int) 1e9 + 7;
        int sum = 0;
        for (int i = 0; i < result.length; ++i) {
            sum += result[i];
            if (sum >= M) {
                sum -= M;
            }
        }
        return sum;
    }

    private int[][] matrixMultiply(int[][] A, int[][] B) {
        final int M = (int) 1e9 + 7;
        int m = A.length, n = B[0].length;
        int[][] C = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < B.length; ++k) {
                    C[i][j] = (int) (((long) A[i][k] * B[k][j] + C[i][j]) % M);
                }
            }
        }
        return C;
    }

    private int[][] matrixPower(int[][] T, int exp) {
        if (exp == 1) {
            return T;
        }
        if ((exp & 1) == 1) {
            return matrixMultiply(matrixPower(T, exp - 1), T);
        }
        int[][] A = matrixPower(T, exp >> 1);
        return matrixMultiply(A, A);
    }
}
