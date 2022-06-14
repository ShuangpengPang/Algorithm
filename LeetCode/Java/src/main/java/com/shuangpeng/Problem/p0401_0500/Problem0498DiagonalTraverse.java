package com.shuangpeng.Problem.p0401_0500;

/**
 * @Description: Problem0498DiagonalTraverse（对角线遍历）
 * @Date 2022/6/14 10:39 AM
 * @Version 1.0
 */
public class Problem0498DiagonalTraverse {

    public int[] findDiagonalOrder0(int[][] mat) {
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

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m * n];
        int pos = 0;
        for (int i = 0; i < m + n - 1; ++i) {
            if (i % 2 == 0) {
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    ans[pos++] = mat[x--][y++];
                }
            } else {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    ans[pos++] = mat[x++][y--];
                }
            }
        }
        return ans;
    }
}
