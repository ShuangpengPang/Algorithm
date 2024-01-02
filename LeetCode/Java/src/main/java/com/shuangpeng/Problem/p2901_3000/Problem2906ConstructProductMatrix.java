package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2906ConstructProductMatrix（构造乘积矩阵）
 * @date 2024/1/2 11:32 PM
 */
public class Problem2906ConstructProductMatrix {

    public int[][] constructProductMatrix(int[][] grid) {
        final int M = 12345;
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        long suf = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                ans[i][j] = (int) suf;
                suf = suf * grid[i][j] % M;
            }
        }
        long pre = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = (int) (pre * ans[i][j] % M);
                pre = pre * grid[i][j] % M;
            }
        }
        return ans;
    }
}
