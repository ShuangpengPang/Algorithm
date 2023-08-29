package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1289MinimumFallingPathSumII（下降路径最小和II）
 * @date 2023/8/10 10:20 AM
 */
public class Problem1289MinimumFallingPathSumII {

    public int minFallingPathSum0(int[][] grid) {
        int n = grid.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (j != k) {
                        min = Math.min(min, grid[i - 1][k]);
                    }
                }
                grid[i][j] += min;
            }
        }
        return Arrays.stream(grid[n - 1]).min().getAsInt();
    }

    public int minFallingPathSum1(int[][] grid) {
        int n = grid.length, N = Integer.MAX_VALUE;
        int[] right = new int[n];
        right[n - 1] = N;
        for (int i = 1; i < n; i++) {
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.min(grid[i - 1][j + 1], right[j + 1]);
            }
            for (int j = 0, left = N; j < n; j++) {
                grid[i][j] += Math.min(left, right[j]);
                left = Math.min(left, grid[i - 1][j]);
            }
        }
        return Arrays.stream(grid[n - 1]).min().getAsInt();
    }

    public int minFallingPathSum2(int[][] grid) {
        int n = grid.length, N = Integer.MAX_VALUE, firstIndex = -1, secondMin = N;
        for (int i = 0; i < n; i++) {
            if (firstIndex == -1 || grid[0][i] <= grid[0][firstIndex]) {
                if (firstIndex != -1) {
                    secondMin = grid[0][firstIndex];
                }
                firstIndex = i;
            } else if (grid[0][i] < secondMin) {
                secondMin = grid[0][i];
            }
        }
        for (int i = 1; i < n; i++) {
            int tmpIndex = -1, tmpSecond = N;
            for (int j = 0; j < n; j++) {
                grid[i][j] += j == firstIndex ? secondMin : grid[i - 1][firstIndex];
                if (tmpIndex == -1 || grid[i][j] <= grid[i][tmpIndex]) {
                    if (tmpIndex != -1) {
                        tmpSecond = grid[i][tmpIndex];
                    }
                    tmpIndex = j;
                } else if (grid[i][j] < tmpSecond) {
                    tmpSecond = grid[i][j];
                }
            }
            firstIndex = tmpIndex;
            secondMin = tmpSecond;
        }
        return Arrays.stream(grid[n - 1]).min().getAsInt();
    }

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length, N = Integer.MAX_VALUE >> 1;
        int m1 = N, m2 = N;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = grid[0][i];
            if (dp[i] < m1) {
                m2 = m1;
                m1 = dp[i];
            } else if (dp[i] < m2) {
                m2 = dp[i];
            }
        }
        for (int i = 1; i < n; i++) {
            int t1 = N, t2 = N;
            for (int j = 0; j < n; j++) {
                dp[j] = grid[i][j] + (dp[j] == m1 ? m2 : m1);
                if (dp[j] < t1) {
                    t2 = t1;
                    t1 = dp[j];
                } else if (dp[j] < t2) {
                    t2 = dp[j];
                }
            }
            m1 = t1;
            m2 = t2;
        }
        return m1;
    }
}
