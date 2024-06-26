package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR146SpiralArray（螺旋遍历二维数组）
 * @date 2024/5/15 2:25 PM
 */
public class LCR146SpiralArray {

    public int[] spiralArray(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        int m = array.length, n = array[0].length, N = m * n;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        int[] ans = new int[N];
        int index = 0;
        while (up <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                ans[index++] = array[up][i];
            }
            up++;
            for (int i = up; i <= down; i++) {
                ans[index++] = array[i][right];
            }
            right--;
            if (up <= down) {
                for (int i = right; i >= left; i--) {
                    ans[index++] = array[down][i];
                }
                down--;
            }
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    ans[index++] = array[i][left];
                }
                left++;
            }
        }
        return ans;
    }
}

class LCR146SpiralArray0 {

    private static int[] dirs = {0, 1, 0, -1, 0};

    public int[] spiralArray(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        int m = array.length, n = array[0].length, total = m * n;
        int[] ans = new int[total];
        boolean[][] visited = new boolean[m][n];
        int r = 0, c = 0, d = 0;
        for (int i = 0; i < total; i++) {
            ans[i] = array[r][c];
            visited[r][c] = true;
            int nr = r + dirs[d], nc = c + dirs[d + 1];
            if (nr < 0 || nr == m || nc < 0 || nc == n || visited[nr][nc]) {
                d = (d + 1) % 4;
            }
            r += dirs[d];
            c += dirs[d + 1];
        }
        return ans;
    }
}

class LCR146SpiralArray1 {

    public int[] spiralArray(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        int rows = array.length, columns = array[0].length;
        int[] order = new int[rows * columns];
        int index = 0;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order[index++] = array[top][column];
            }
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = array[row][right];
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order[index++] = array[bottom][column];
                }
                for (int row = bottom; row > top; row--) {
                    order[index++] = array[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
