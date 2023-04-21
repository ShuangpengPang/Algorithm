package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0733FloodFill（图像渲染）
 * @date 2023/4/21 4:10 PM
 */
public class Problem0733FloodFill {

    int[] dirs = {0, -1, 0, 1, 0};

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfs(image, sr, sc, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int color) {
        if (image[r][c] == color) {
            return;
        }
        int m = image.length, n = image[0].length;
        int oldColor = image[r][c];
        image[r][c] = color;
        for (int d = 0; d < 4; d++) {
            int x = r + dirs[d], y = c +dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || image[x][y] != oldColor) {
                continue;
            }
            dfs(image, x, y, color);
        }
    }
}
