package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3030FindTheGridOfRegionAverage（找出网格的区域平均强度）
 * @date 2024/2/27 10:35 AM
 */
public class Problem3030FindTheGridOfRegionAverage {

    public int[][] resultGrid(int[][] image, int threshold) {
        int m = image.length, n = image[0].length;
        int[][] cnt = new int[m + 1][n + 1], avg = new int[m + 1][n + 1];
        for (int i = 0; i <= m - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {
                int sum = getSum(image, i, j, threshold);
                if (sum != -1) {
                    update(cnt, i, j, 1);
                    update(avg, i, j, sum / 9);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cnt[i][j] = getResult(cnt, i, j);
                avg[i][j] = getResult(avg, i, j);
                if (cnt[i][j] > 0) {
                    image[i][j] = avg[i][j] / cnt[i][j];
                }
            }
        }
        return image;
    }

    private int getResult(int[][] arr, int i, int j) {
        return arr[i][j] + (i > 0 ? arr[i - 1][j] : 0) + (j > 0 ? arr[i][j - 1] : 0) - (i > 0 && j > 0 ? arr[i - 1][j - 1] : 0);
    }

    private int getSum(int[][] image, int i, int j, int threshold) {
        int m = image.length, n = image[0].length;
        int sum = 0;
        for (int x = i; x < i + 3; x++) {
            for (int y = j; y < j + 3; y++) {
                sum += image[x][y];
                if (y < j + 2 && Math.abs(image[x][y] - image[x][y + 1]) > threshold) {
                    return -1;
                }
                if (x < i + 2 && Math.abs(image[x][y] - image[x + 1][y]) > threshold) {
                    return -1;
                }
            }
        }
        return sum;
    }

    private void update(int[][] arr, int i, int j, int v) {
        arr[i][j] += v;
        arr[i][j + 3] -= v;
        arr[i + 3][j] -= v;
        arr[i + 3][j + 3] += v;
    }
}
