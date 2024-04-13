package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2946MatrixSimilarityAfterCyclicShifts（循环移位后的矩阵相似检查）
 * @date 2024/4/13 9:16 PM
 */
public class Problem2946MatrixSimilarityAfterCyclicShifts {

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k %= n;
        for (int i = 0; i < m; i++) {
            int s = (n + (((i & 1) << 1) - 1) * k) % n;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != mat[i][(s + j) % n]) {
                    return false;
                }
            }
        }
        return true;
    }
}
