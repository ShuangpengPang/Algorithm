package com.shuangpeng.competition.第299场周赛;

/**
 * @Description: Problem2319CheckIfMatrixIsXMatrix（判断矩阵是否是一个X矩阵）
 * @Date 2022/7/7 4:21 PM
 * @Version 1.0
 */
public class Problem2319CheckIfMatrixIsXMatrix {
    public boolean checkXMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else if (grid[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
