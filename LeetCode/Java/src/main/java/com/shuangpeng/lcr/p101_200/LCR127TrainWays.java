package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR127TrainWays（跳跃训练）
 * @date 2024/5/13 4:58 PM
 */
public class LCR127TrainWays {

    public int trainWays0(int num) {
        int N = (int) 1e9 + 7;
        int last1 = 0, last = 1, t = 1;
        for (int i = 0; i < num; i++) {
            t = last;
            last = (last + last1) % N;
            last1 = t;
        }
        return last;
    }

    public int trainWays(int num) {
        int[][] a = {{0, 1}};
        int[][] b = {{0, 1}, {1, 1}}, c = matrixExp(b, num);
        int[][] ans = matrixMultiple(a, c);
        return ans[0][1];
    }

    private int[][] matrixExp(int[][] matrix, int e) {
        int n = matrix.length;
        int[][] ans = new int[n][n];
        for (int i = 0;i < n; i++) {
            ans[i][i] = 1;
        }
        while (e != 0) {
            if ((e & 1) == 1) {
                ans = matrixMultiple(ans, matrix);
            }
            e >>= 1;
            matrix = matrixMultiple(matrix, matrix);
        }
        return ans;
    }

    private int[][] matrixMultiple(int[][] a, int [][] b) {
        int m = a.length, n = b[0].length, N = (int) 1e9 + 7;
        int[][] c = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < b.length; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][k] = (int) ((c[i][k] + (long) a[i][j] * b[j][k]) % N);
                }
            }
        }
        return c;
    }
}
