package com.shuangpeng.competition.第260场周赛;

public class Problem2017 {

    // 比赛时写法
    public long gridGame0(int[][] grid) {
        int n = grid[0].length;
        long[] left = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            left[i] = left[i - 1] + grid[1][i - 1];
        }
        long[] right = new long[n + 2];
        for (int i = n; i >= 1; --i) {
            right[i] = right[i + 1] + grid[0][i - 1];
        }
        long minValue = right[2];
        for (int i = 2; i <= n; ++i) {
            minValue = Math.min(minValue, Math.max(left[i - 1], right[i + 1]));
        }
        return minValue;
    }

    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[] right = new long[n];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = right[i + 1] + grid[0][i + 1];
        }
        long sum = 0;
        long ans = right[0];
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, Math.max(sum, right[i]));
            sum += grid[1][i];
        }
        return ans;
    }
}
