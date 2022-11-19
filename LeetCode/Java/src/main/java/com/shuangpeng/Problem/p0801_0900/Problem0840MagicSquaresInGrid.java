package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0840MagicSquaresInGrid（矩阵中的幻方）
 * @date 2022/11/15 11:33 PM
 */
public class Problem0840MagicSquaresInGrid {

    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i <= m - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {
                ans += check(grid, i, j);
            }
        }
        return ans;
    }

    private int check(int[][] grid, int x, int y) {
        boolean[] visited = new boolean[10];
        for (int i = x; i < x + 3; i++) {
            int s = 0;
            for (int j = y; j < y + 3; j++) {
                int num = grid[i][j];
                if (num < 1 || num > 9 || visited[num]) {
                    return 0;
                }
                visited[num] = true;
                s += num;
            }
            if (s != 15) {
                return 0;
            }
        }
        for (int j = y; j < y + 3; j++) {
            int s = 0;
            for (int i = x; i < x + 3; i++) {
                s += grid[i][j];
            }
            if (s != 15) {
                return 0;
            }
        }
        int s1 = 0, s2 = 0;
        for (int i = 0; i < 3; i++) {
            s1 += grid[x + i][y + i];
            s2 += grid[x - i + 2][y + i];
        }
        return s1 == s2 && s1 == 15 ? 1 : 0;
    }
}