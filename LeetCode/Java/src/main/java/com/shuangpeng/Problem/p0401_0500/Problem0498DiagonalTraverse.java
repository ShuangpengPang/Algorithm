package com.shuangpeng.Problem.p0401_0500;

/**
 * @Description: Problem0498DiagonalTraverse（对角线遍历）
 * @Date 2022/6/14 10:39 AM
 * @Version 1.0
 */
public class Problem0498DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int N = m * n;
        int[] ans = new int[N];
        boolean up = true;
        for (int i = 0, x = 0, y = 0; i < N; ++i) {
            ans[i] = mat[x][y];
            if (up) {
                if (x > 0 && y < n - 1) {
                    --x;
                    ++y;
                } else {
                    up = false;
                    if (y == n - 1) {
                        ++x;
                    } else {
                        ++y;
                    }
                }
            } else {
                if (x < m - 1 && y > 0) {
                    ++x;
                    --y;
                } else {
                    up = true;
                    if (x == m - 1) {
                        ++y;
                    } else {
                        ++x;
                    }
                }
            }
        }
        return ans;
    }
}
