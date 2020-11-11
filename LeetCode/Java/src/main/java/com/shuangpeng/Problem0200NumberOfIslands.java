package com.shuangpeng;

public class Problem0200NumberOfIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        String[][] root = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == 0 || grid[i - 1][j] == '0') && (j == 0 || grid[i][j - 1] == '0')) {
                    root[i][j] = "" + i + j;
                    count++;
                } else {

                }
            }
        }
    }

    public String getRoot(String[][] root, int i, int j) {
        while (root[i][j] != "" + i + j) {

        }
    }
}
