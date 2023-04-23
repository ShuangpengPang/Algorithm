package com.shuangpeng.Problem.p0501_0600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0566ReshapeTheMatrix（重塑矩阵）
 * @date 2023/4/23 6:26 PM
 */
public class Problem0566ReshapeTheMatrix {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (m * n != r * c || m == r && n == c) {
            return mat;
        }
        int[][] ans = new int[r][c];
        for (int i = 0, x = 0, y = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = mat[x][y];
                if (++y == n) {
                    x++;
                    y = 0;
                }
            }
        }
        return ans;
    }
}
