package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3142CheckIfGridSatisfiesConditions（判断矩阵是否满足条件）
 * @date 2024/5/31 11:36 AM
 */
public class Problem3142CheckIfGridSatisfiesConditions {

    public boolean satisfiesConditions(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < n; i++) {
            if (grid[0][i] == grid[0][i - 1]) {
                return false;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != grid[0][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
