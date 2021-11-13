package com.shuangpeng.Problem.p0001_0100;

public class Problem0052NQueensII {

    public int totalNQueens(int n) {
        return dfs(n, 0, 0, 0, 0);
    }

    private int dfs(int n, int row, int columns, int diagonal1, int diagonal2) {
        if (n == row) {
            return 1;
        }
        int mask = (~(columns | diagonal1 | diagonal2)) & ((1 << n) - 1);
        int count = 0;
        while (mask != 0) {
            int pos = mask & (-mask);
            count += dfs(n, row + 1, columns | pos, (diagonal1 | pos) >> 1, (diagonal2 | pos) << 1);
            mask &= (mask - 1);
        }
        return count;
    }
}
