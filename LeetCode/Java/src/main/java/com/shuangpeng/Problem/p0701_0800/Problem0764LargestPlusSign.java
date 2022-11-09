package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 最大加号标志
 * @date 2022/11/9 10:21 AM
 **/
public class Problem0764LargestPlusSign {

    public int orderOfLargestPlusSign0(int n, int[][] mines) {
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

    public int orderOfLargestPlusSign1(int n, int[][] mines) {
        boolean[][] grid = new boolean[n][n];
        for (int[] m : mines) {
            grid[m[0]][m[1]] = true;
        }
        int[][] left = new int[n][n], right = new int[n][n], top = new int[n][n], down = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!grid[i][j]) {
                    left[i][j] = j == 0 ? 1 : left[i][j - 1] + 1;
                    top[i][j] = i == 0 ? 1 : top[i - 1][j] + 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (!grid[i][j]) {
                    right[i][j] = j == n - 1 ? 1 : right[i][j + 1] + 1;
                    down[i][j] = i == n - 1 ? 1 : down[i + 1][j] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, Math.min(Math.min(left[i][j], right[i][j]), Math.min(top[i][j], down[i][j])));
            }
        }
        return ans;
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        Set<Integer> banned = new HashSet<>();
        for (int[] m : mines) {
            banned.add(m[0] * n + m[1]);
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                cnt = banned.contains(i * n + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
            cnt = 0;
            for (int j = n - 1; j >= 0; j--) {
                cnt = banned.contains(i * n + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt = banned.contains(i * n + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
            }
            cnt = 0;
            for (int i = n - 1; i >= 0; i--) {
                cnt = banned.contains(i * n + j) ? 0 : cnt + 1;
                dp[i][j] = Math.min(dp[i][j], cnt);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}