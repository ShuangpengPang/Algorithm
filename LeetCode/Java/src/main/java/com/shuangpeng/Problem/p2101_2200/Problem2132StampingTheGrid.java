package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2132StampingTheGrid（用邮票贴满网格图）
 * @date 2022/11/11 3:16 PM
 */
public class Problem2132StampingTheGrid {

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }
        int[][] diff = new int[m + 2][n + 2];
        for (int i = stampHeight; i <= m; i++) {
            for (int j = stampWidth; j <= n; j++) {
                if (sum[i][j] - sum[i - stampHeight][j] - sum[i][j - stampWidth] + sum[i - stampHeight][j - stampWidth] == 0) {
                    diff[i - stampHeight + 1][j - stampWidth + 1]++;
                    diff[i - stampHeight + 1][j + 1]--;
                    diff[i + 1][j - stampWidth + 1]--;
                    diff[i + 1][j + 1]++;
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                if (grid[i - 1][j - 1] == 0 && diff[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
