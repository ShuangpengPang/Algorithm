package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;

public class Problem0807MaxIncreaseToKeepCitySkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] rows = new int[n];
        int[] cols = new int[n];
        Arrays.fill(rows, Integer.MIN_VALUE);
        Arrays.fill(cols, Integer.MIN_VALUE);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = Math.max(rows[i], grid[i][j]);
                cols[j] = Math.max(cols[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += Math.min(rows[i], cols[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
