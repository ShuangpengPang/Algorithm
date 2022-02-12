package com.shuangpeng.Problem.p1001_1100;

public class Problem1020NumberOfEnclaves {

    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int M = m * n;
        int[] parent = new int[M + 1];
        for (int i = 0; i <= M; ++i) {
            parent[i] = i;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int k = i * n + j;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        union(parent, k, k - n);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        union(parent, k, k - 1);
                    }
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        union(parent, k, M);
                    }
                }
            }
        }
        int ans = 0;
        int p = find(parent, M);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && find(parent, i * n + j) != p) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private int find(int[] parent, int i) {
        return i == parent[i] ? i : (parent[i] = find(parent, parent[i]));
    }

    private void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = find(parent, j);
    }
}
