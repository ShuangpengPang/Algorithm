package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1886DeterminWhetherMatrixCanBeObtainedByRotation（判断矩阵经轮转后是否一致）
 * @date 2024/3/20 2:33 PM
 */
public class Problem1886DeterminWhetherMatrixCanBeObtainedByRotation {

    public boolean findRotation0(int[][] mat, int[][] target) {
        int n = mat.length;
        return checkRow(mat,target, 0, 1) || checkRow(mat, target, n - 1, -1)
                || checkCol(mat, target, 0, 1) || checkCol(mat, target, n - 1, -1);
    }

    private boolean checkRow(int[][] mat, int[][] target, int start, int add) {
        int n = mat.length;
        for (int i = start, x = 0; x < n; i += add, x++) {
            for (int j = start, y = 0; y < n; j += add, y++) {
                if (mat[i][j] != target[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkCol(int[][] mat, int[][] target, int start, int add) {
        int n = mat.length;
        for (int j = start, x = 0; x < n; j += add, x++) {
            for (int i = n - start - 1, y = 0; y < n; i -= add, y++) {
                if (mat[i][j] != target[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        boolean ans = true, one = true, two = true, three = true, four = true;
        for (int i = 0; i < n && ans; i++) {
            for (int j = 0; j < n && ans; j++) {
                if (one && mat[i][j] != target[i][j]) {
                    one = false;
                }
                if (two && mat[n - j - 1][i] != target[i][j]) {
                    two = false;
                }
                if (three && mat[n - i - 1][n - j - 1] != target[i][j]) {
                    three = false;
                }
                if (four && mat[j][n - i - 1] != target[i][j]) {
                    four = false;
                }
                ans = one || two || three || four;
            }
        }
        return ans;
    }
}
