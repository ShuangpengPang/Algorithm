package com.shuangpeng.competition.双周赛.第066场双周赛;

public class Problem2088 {

    public int countPyramids0(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j == 0) {
                    left[i][j] = grid[i][j] == 1 ? 0 : 1;
                } else {
                    left[i][j] = grid[i][j] == 1 ? left[i][j - 1] : j + 1;
                }
            }
        }
        int[][] right = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = n - 1; j >=0; --j) {
                if (j == n - 1) {
                    right[i][j] = grid[i][j] == 1 ? j : j - 1;
                } else {
                    right[i][j] = grid[i][j] == 1 ? right[i][j + 1] : j - 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int k = 1;
                    while (i + k < m && left[i + k][j] <= j - k && right[i + k][j] >= j + k) {
                        ++k;
                    }
                    ans += k - 1;
                }
            }
        }
        for (int i = m - 1; i > 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int k = 1;
                    while (i - k >= 0 && left[i - k][j] <= j - k && right[i - k][j] >= j + k) {
                        ++k;
                    }
                    ans += k - 1;
                }
            }
        }
        return ans;
    }

    public int countPyramids(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[2][n];
        int ans = 0;
        for (int i = m - 1; i >= 0; --i) {
            int k = i % 2;
            int p = k ^ 1;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    dp[k][j] = -1;
                } else if (i == m - 1 || j == 0 || j == n - 1) {
                    dp[k][j] = 0;
                } else {
                    dp[k][j] = Math.min(dp[p][j], Math.min(dp[p][j - 1], dp[p][j + 1])) + 1;
                }
                ans += dp[k][j] > 0 ? dp[k][j] : 0;
            }
        }
        for (int i = 0; i < m; ++i) {
            int k = i % 2;
            int p = k ^ 1;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    dp[k][j] = -1;
                } else if (i == 0 || j == 0 || j == n - 1) {
                    dp[k][j] = 0;
                } else {
                    dp[k][j] = Math.min(dp[p][j], Math.min(dp[p][j - 1], dp[p][j + 1])) + 1;
                }
                ans += dp[k][j] > 0 ? dp[k][j] : 0;
            }
        }
        return ans;
    }
}
