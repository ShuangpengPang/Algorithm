package com.shuangpeng.competition.第062场双周赛;

public class Problem2022 {

    public int[][] construct2DArray(int[] original, int m, int n) {
        int length = original.length;
        if (length != m * n) {
            return new int[0][0];
        }
        int[][] ans = new int[m][n];
        int idx = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = original[idx++];
            }
        }
        return ans;
    }
}
