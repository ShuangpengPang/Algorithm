package com.shuangpeng.competition.第290到300场周赛.第299场周赛;

/**
 * @Description: Problem2319CheckIfMatrixIsXMatrix（判断矩阵是否是一个X矩阵）
 * @Date 2022/7/7 4:21 PM
 * @Version 1.0
 */
public class Problem2319CheckIfMatrixIsXMatrix {

    // 比赛时写法
    public boolean checkXMatrix0(int[][] grid) {
        int n = grid.length;
        boolean valid = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0) {
                        valid = false;
                        break;
                    }
                } else if (grid[i][j] != 0) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }

    public boolean checkXMatrix1(int[][] grid) {
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

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0 && i != j && i + j != n - 1) {
                    return false;
                }
                if (grid[i][j] == 0 && (i == j || i + j == n - 1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
