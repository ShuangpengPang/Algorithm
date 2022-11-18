package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0861ScoreAfterFlippingMatrix（翻转矩阵后的得分）
 * @date 2022/11/18 11:53 PM
 */
public class Problem0861ScoreAfterFlippingMatrix {

    public int matrixScore0(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] ^= 1;
                }
            }
        }
        int ans = 0, b = 1;
        for (int j = n - 1; j >= 0; j--) {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    cnt++;
                }
            }
            ans += Math.max(cnt, m - cnt) * b;
            b <<= 1;
        }
        return ans;
    }

    public int matrixScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0, p = 1;
        for (int j = n - 1; j >= 0; j--) {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if ((grid[i][j] ^ grid[i][0]) == 0) {
                    cnt++;
                }
            }
            ans += Math.max(cnt, m - cnt) * p;
            p <<= 1;
        }
        return ans;
    }
}
