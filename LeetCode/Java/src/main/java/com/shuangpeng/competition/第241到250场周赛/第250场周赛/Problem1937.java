package com.shuangpeng.competition.第241到250场周赛.第250场周赛;

import java.util.Arrays;

public class Problem1937 {

    public long maxPoints0(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            dp[i] = points[0][i];
        }
        for (int i = 1; i < m; i++) {
            long[] lMax = new long[n];
            long maxValue = 0;
            for (int j = 0; j < n; j++) {
                maxValue = Math.max(maxValue - 1, dp[j]);
                lMax[j] = maxValue;
            }
            long[] rMax = new long[n];
            maxValue = 0;
            for (int j = n - 1; j >= 0; j--) {
                maxValue = Math.max(maxValue - 1, dp[j]);
                rMax[j] = maxValue;
            }
            for (int j = 0; j < n; j++) {
                dp[j] = points[i][j] + Math.max(lMax[j], rMax[j]);
            }
        }
        long maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;
    }

    public long maxPoints1(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int i = 0; i < m; i++) {
            long[] lMax = new long[n];
            lMax[0] = dp[0];
            for (int j = 1; j < n; j++) {
                lMax[j] = Math.max(lMax[j - 1], dp[j] + j);
            }
            long[] rMax = new long[n];
            rMax[n - 1] = dp[n - 1] - n + 1;
            for (int j = n - 2; j >= 0; j--) {
                rMax[j] = Math.max(rMax[j + 1], dp[j] - j);
            }
            for (int j = 0; j < n; j++) {
                dp[j] = Math.max(lMax[j] - j, rMax[j] + j) + points[i][j];
            }
        }
        return Arrays.stream(dp).max().getAsLong();
    }

    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int i = 0; i < m; i++) {
            long[] temp = new long[n];
            long maxValue = Long.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                maxValue = Math.max(maxValue, dp[j] + j);
                temp[j] = maxValue + points[i][j] - j;
            }
            maxValue = Long.MIN_VALUE;
            for (int j = n - 1; j >= 0; j--) {
                maxValue = Math.max(maxValue, dp[j] - j);
                temp[j] = Math.max(temp[j], maxValue + points[i][j] + j);
            }
            dp = temp;
        }
        return Arrays.stream(dp).max().getAsLong();
    }
}
