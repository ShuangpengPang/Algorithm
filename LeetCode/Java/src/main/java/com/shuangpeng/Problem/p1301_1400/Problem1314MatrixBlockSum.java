package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1314MatrixBlockSum（矩阵区域和）
 * @date 2023/7/14 4:47 PM
 */
public class Problem1314MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1, s = 0; j <= n; j++) {
                s += mat[i - 1][j - 1];
                preSum[i][j] = preSum[i - 1][j] + s;
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int x1 = Math.max(i - k - 1, 0), x2 = Math.min(i + k, m);
                int y1 = Math.max(j - k - 1, 0), y2 = Math.min(j + k, n);
                ans[i - 1][j - 1] = preSum[x2][y2] - preSum[x2][y1] - preSum[x1][y2] + preSum[x1][y1];
            }
        }
        return ans;
    }
}
