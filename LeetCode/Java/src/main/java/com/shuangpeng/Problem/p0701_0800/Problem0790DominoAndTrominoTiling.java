package com.shuangpeng.Problem.p0701_0800;

/**
 * @description: 多米诺和托米诺平铺
 * @date 2022/11/12 11:40 AM
 **/
public class Problem0790DominoAndTrominoTiling {

    public int numTilings0(int n) {
        if (n <= 2) {
            return n;
        }
        final int M = (int) 1e9 + 7;
        int[][] dp = new int[n + 1][6];
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[2][2] = 0;
        dp[2][3] = 0;

        dp[2][4] = 1;
        dp[2][5] = 1;
        for (int i = 3; i <= n; ++i) {
            dp[i][0] = (int) (((long) dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3]) % M);
            dp[i][1] = (int) (((long) dp[i - 2][0] + dp[i - 2][1] + dp[i - 2][2] + dp[i - 2][3]) % M);
            dp[i][2] = dp[i - 1][5];
            dp[i][3] = dp[i - 1][4];
            dp[i][4] = (int) (((long) dp[i][1] + dp[i - 1][5]) % M);
            dp[i][5] = (int) (((long) dp[i][1] + dp[i - 1][4]) % M);
        }
        return (int) (((long) dp[n][0] + dp[n][1] + dp[n][2] + dp[n][3]) % M);
    }

    public int numTilings1(int n) {
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 1;
        dp[1][0] = 1;
        final int M = (int) 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            dp[i][0] = (int) (((long) dp[i - 1][0] + dp[i - 1][1] + dp[i - 2][0]) % M);
            dp[i][1] = (int) (((long) dp[i - 1][1] + (dp[i - 2][0] << 1)) % M);
        }
        return dp[n][0];
    }

    public int numTilings2(int n) {
        final int M = (int) 1e9 + 7;
        int first = 1, second = 1, third = 0;
        for (int i = 2; i <= n; ++i) {
            int temp = second;
            second = (int) (((long) second + first + third) % M);
            third = (int) (((long) third + (first << 1)) % M);
            first = temp;
        }
        return second;
    }

    public int numTilings3(int N) {
        final int M = (int) 1e9 + 7;
        long[] dp = {1, 0, 0, 0};
        for (int i = 0; i < N; ++i) {
            long[] ndp = new long[dp.length];
            ndp[0b00] = (dp[0b00] + dp[0b11]) % M;
            ndp[0b01] = (dp[0b00] + dp[0b10]) % M;
            ndp[0b10] = (dp[0b00] + dp[0b01]) % M;
            ndp[0b11] = (dp[0b00] + dp[0b01] + dp[0b10]) % M;
            dp = ndp;
        }
        return (int) dp[0];
    }

    public int numTilings4(int N) {
        final int M = (int) 1e9 + 7;
        int[][] dp = {{1, 0, 0, 0}};
        int[][] T = {
                {1, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 1, 0, 1},
                {1, 0, 0, 0}
        };
        dp = multiply(dp, quickPower(T, N));
        return dp[0][0];
    }

    private int[][] quickPower(int[][] T, int exp) {
        if (exp == 1) {
            return T;
        }
        int[][] A = quickPower(T, exp >> 1);
        int[][] B = multiply(A, A);
        if ((exp & 1) == 1) {
            return multiply(B, T);
        }
        return B;
    }

    private int[][] multiply(int[][] A, int[][] B) {
        final int M = (int) 1e9 + 7;
        int m = A.length, n = B[0].length;
        int[][] C = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < B.length; ++k) {
                    C[i][j] += (long) A[i][k] * B[k][j] % M;
                    C[i][j] %= M;
                }
            }
        }
        return C;
    }

    public int numTilings(int n) {
        int a20 = 1, a10 = 1, a11 = 0, a12 = 0, M = (int) 1e9 + 7;
        for (int i = 2; i <= n; i++) {
            int t0 = (int) (((long) a10 + a11 + a12 + a20) % M);
            int t1 = (a20 + a12) % M;
            int t2 = (a20 + a11) % M;
            a20 = a10;
            a10 = t0;
            a11 = t1;
            a12 = t2;
        }
        return a10;
    }
}
