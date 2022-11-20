package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0885SpiralMatrixIII（螺旋矩阵III）
 * @date 2022/11/19 5:11 PM
 */
public class Problem0885SpiralMatrixIII {

    public int[][] spiralMatrixIII0(int rows, int cols, int rStart, int cStart) {
        int top = rStart - 1, bottom = rStart + 1, left = cStart - 1, right = cStart + 1, cnt = 1, n = rows * cols;
        int[][] ans = new int[n][2];
        ans[0] = new int[]{rStart, cStart};
        while (cnt < n) {
            int r = top + 1, c = right;
            while (r < bottom) {
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    ans[cnt++] = new int[]{r, c};
                } else if (c < 0 || c >= cols) {
                    r = bottom;
                    break;
                }
                r++;
            }
            while (c > left) {
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    ans[cnt++] = new int[]{r, c};
                } else if (r < 0 || r >= rows) {
                    c = left;
                    break;
                }
                c--;
            }
            while (r > top) {
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    ans[cnt++] = new int[]{r, c};
                } else if (c < 0 || c >= cols) {
                    r = top;
                    break;
                }
                r--;
            }
            while (c <= right) {
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    ans[cnt++] = new int[]{r, c};
                } else if (r < 0 || r >= rows) {
                    c = right;
                    break;
                }
                c++;
            }
            top--;
            bottom++;
            left--;
            right++;
        }
        return ans;
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int n = rows * cols, d = 0, idx = 0, r = rStart, c = cStart, step = 1;
        int[][] ans = new int[n][2];
        while (idx < n) {
            int x = r + dirs[d][0] * step, y = c + dirs[d][1] * step;
            while (idx < n && (r != x || c != y)) {
                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    ans[idx++] = new int[]{r, c};
                }
                r += dirs[d][0];
                c += dirs[d][1];
            }
            d = (d + 1) % 4;
            if ((d & 1) == 0) {
                step++;
            }
        }
        return ans;
    }
}
