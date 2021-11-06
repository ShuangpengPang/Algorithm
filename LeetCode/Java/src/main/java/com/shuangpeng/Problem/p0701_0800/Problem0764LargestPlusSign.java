package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;

public class Problem0764LargestPlusSign {

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(array[i], 1);
        }
        for (int[] mine : mines) {
            array[mine[0]][mine[1]] = 0;
        }
        int[][][] dp = new int[n + 2][n + 2][4];
        // 0 : up, 1: left  2: down  3: right
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (array[i - 1][j - 1] == 1) {
                    dp[i][j][0] = dp[i - 1][j][0] + 1;
                    dp[i][j][1] = dp[i][j - 1][1] + 1;
                }
            }
        }
        for (int i = n; i >= 1; i--) {
            for (int j = n; j >= 1; j--) {
                if (array[i - 1][j - 1] == 1) {
                    dp[i][j][2] = dp[i + 1][j][2] + 1;
                    dp[i][j][3] = dp[i][j + 1][3] + 1;
                }
            }
        }
        int k = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int m = Math.min(Math.min(dp[i][j][0], dp[i][j][1]), Math.min(dp[i][j][2], dp[i][j][3]));
                k = Math.max(k, m);
            }
        }
        return k;
    }
}
