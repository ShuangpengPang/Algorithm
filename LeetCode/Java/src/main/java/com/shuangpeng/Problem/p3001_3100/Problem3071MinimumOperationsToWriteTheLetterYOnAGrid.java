package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3071MinimumOperationsToWriteTheLetterYOnAGrid（在矩阵上写出字母Y所需的最少操作次数）
 * @date 2024/3/18 2:45 PM
 */
public class Problem3071MinimumOperationsToWriteTheLetterYOnAGrid {

    public int minimumOperationsToWriteY(int[][] grid) {
        int[] cnt1 = new int[3], cnt2 = new int[3];
        int n = grid.length, h = n >> 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == j || i + j == n - 1) && i < h || j == h && i >= h) {
                    cnt1[grid[i][j]]++;
                } else {
                    cnt2[grid[i][j]]++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    count = Math.max(count, cnt1[i] + cnt2[j]);
                }
            }
        }
        return n * n - count;
    }
}
