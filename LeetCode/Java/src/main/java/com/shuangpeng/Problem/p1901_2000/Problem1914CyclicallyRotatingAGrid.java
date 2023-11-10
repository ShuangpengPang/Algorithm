package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1914CyclicallyRotatingAGrid（循环轮转矩阵）
 * @date 2023/11/10 1:19 PM
 */
public class Problem1914CyclicallyRotatingAGrid {

    public int[][] rotateGrid0(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        int r1 = 0, r2 = m - 1, c1 = 0, c2 = n - 1;
        while (r1 < r2 && c1 < c2) {
            rotate(grid, ans, r1, r2, c1, c2, k);
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    private void rotate(int[][] grid, int[][] ans, int r1, int r2, int c1, int c2, int k) {
        int total = (r2 - r1 + c2 - c1) << 1;
        for (int i = r1; i <= r2; i++) {
            int step = i == r1 || i == r2 ? 1 : c2 - c1;
            for (int j = c1; j <= c2; j += step) {
                int x = i, y = j, s = k % total;
                while (s > 0) {
                    int c = 0;
                    if (x == r1 && y != c1) {
                        c = Math.min(y - c1, s);
                        y -= c;
                    } else if (x != r2 && y == c1) {
                        c = Math.min(r2 - x, s);
                        x += c;
                    } else if (x == r2 && y != c2) {
                        c = Math.min(c2 - y, s);
                        y += c;
                    } else if (x != r1 && y == c2) {
                        c = Math.min(x - r1, s);
                        x -= c;
                    }
                    s -= c;
                }
                ans[x][y] = grid[i][j];
            }
        }
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, total = (m + n << 1) - 4;
        int layers = Math.min(m, n) >> 1;
        for (int layer = 0; layer < layers; layer++, total -= 8) {
            int[] r = new int[total], c = new int[total], v = new int[total];
            int idx = 0;
            for (int i = layer; i < m - layer - 1; i++, idx++) {
                r[idx] = i;
                c[idx] = layer;
                v[idx] = grid[i][layer];
            }
            for (int i = layer; i < n - layer - 1; i++, idx++) {
                r[idx] = m - layer - 1;
                c[idx] = i;
                v[idx] = grid[m - layer - 1][i];
            }
            for (int i = m - layer - 1; i > layer; i--, idx++) {
                r[idx] = i;
                c[idx] = n - layer - 1;
                v[idx] = grid[i][n - layer - 1];
            }
            for (int i = n - layer - 1; i > layer; i--, idx++) {
                r[idx] = layer;
                c[idx] = i;
                v[idx] = grid[layer][i];
            }
            for (int i = 0; i < total; i++) {
                int index = (i + k) % total;
                grid[r[index]][c[index]] = v[i];
            }
        }
        return grid;
    }
}
