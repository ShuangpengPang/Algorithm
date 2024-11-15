package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3239MinimumNumberOfFlipsToMakeBinaryGridPalindromicI（最少翻转次数使二进制矩阵回文I）
 * @date 2024/11/15 11:44 AM
 */
public class Problem3239MinimumNumberOfFlipsToMakeBinaryGridPalindromicI {

    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int rowFlip = 0, colFlip = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i < m / 2 && grid[i][j] != grid[m - i - 1][j]) {
                    colFlip++;
                }
                if (j < n / 2 && grid[i][j] != grid[i][n - j - 1]) {
                    rowFlip++;
                }
            }
        }
        return Math.min(rowFlip, colFlip);
    }
}
