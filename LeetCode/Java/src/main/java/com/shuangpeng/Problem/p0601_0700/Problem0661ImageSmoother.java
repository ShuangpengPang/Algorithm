package com.shuangpeng.Problem.p0601_0700;

/**
 * @Description: Problem0661ImageSmoother
 * @Date 2022/3/24 5:19 PM
 * @Version 1.0
 */
public class Problem0661ImageSmoother {

    public int[][] imageSmoother0(int[][] img) {
        final int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int m = img.length, n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int sum = img[i][j];
                int count = 1;
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        sum += img[x][y];
                        ++count;
                    }
                }
                ans[i][j] = sum / count;
            }
        }
        return ans;
    }

    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] preSum = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                preSum[i + 1][j + 1] = img[i][j] + preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x1 = Math.max(0, i - 1), y1 = Math.max(0, j - 1);
                int x2 = Math.min(m - 1, i + 1), y2 = Math.min(n - 1, j + 1);
                ans[i][j] = (preSum[x2 + 1][y2 + 1] + preSum[x1][y1] - preSum[x2 + 1][y1] - preSum[x1][y2 + 1]) / ((x2 - x1 + 1) * (y2 - y1 + 1));
            }
        }
        return ans;
    }
}
