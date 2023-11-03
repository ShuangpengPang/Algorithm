package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1895LargestMagicSquare（最大的幻方）
 * @date 2023/11/3 4:03 PM
 */
public class Problem1895LargestMagicSquare {

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] rows = new int[m + 1][n + 1], cols = new int[m + 1][n + 1];
        int[][] topBottom = new int[m + 2][n + 2], bottomTop = new int[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int num = grid[i - 1][j - 1];
                rows[i][j] = rows[i][j - 1] + num;
                cols[i][j] = cols[i - 1][j] + num;
                topBottom[i][j] = topBottom[i - 1][j - 1] + num;
                bottomTop[i][j] = bottomTop[i - 1][j + 1] + num;
            }
        }
        int ans = 1, s = Math.min(m, n);
        boolean find = false;
        for (int d = s; d >= 2 && !find; d--) {
            for (int i = d; i <= m && !find; i++) {
                for (int j = d; j <= n && !find; j++) {
                    int sum = rows[i][j] - rows[i][j - d];
                    boolean valid = topBottom[i][j] - topBottom[i - d][j - d] == sum && bottomTop[i][j - d + 1] - bottomTop[i - d][j + 1] == sum;
                    for (int k = 0; k < d && valid; k++) {
                        valid = rows[i - k][j] - rows[i - k][j - d] == sum && cols[i][j - k] - cols[i - d][j - k] == sum;
                    }
                    find = valid;
                }
            }
            if (find) {
                ans = d;
            }
        }
        return ans;
    }
}
