package com.shuangpeng.Problem.p1501_1600;

/**
 * @Description: Problem1582SpecialPositionsInABinaryMatrix（二进制矩阵中的特殊位置）
 * @Date 2022/9/4 1:31 PM
 * @Version 1.0
 */
public class Problem1582SpecialPositionsInABinaryMatrix {

    public int numSpecial0(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        boolean[] rows = new boolean[m], cols = new boolean[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i]) {
                    break;
                }
                if (cols[j]) {
                    continue;
                }
                if (mat[i][j] == 1) {
                    rows[i] = true;
                    cols[j] = true;
                    if (check(mat, i, j)) {
                        ans++;
                    }
                    break;
                }
            }
        }
        return ans;
    }

    private boolean check(int[][] mat, int x, int y) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            if (i != x && mat[i][y] == 1) {
                return false;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != y && mat[x][i] == 1) {
                return false;
            }
        }
        return true;
    }

    public int numSpecial1(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (rows[i] != 1) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && cols[j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    cnt++;
                }
            }
            if (i == 0) {
                cnt--;
            }
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[0][j] += cnt;
                }
            }
        }
        int ans = 0;
        for (int num : mat[0]) {
            if (num == 1) {
                ans++;
            }
        }
        return ans;
    }
}
