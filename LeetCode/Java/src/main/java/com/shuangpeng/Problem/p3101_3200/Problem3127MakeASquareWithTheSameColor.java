package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3127MakeASquareWithTheSameColor（构造相同颜色的正方形）
 * @date 2024/5/30 11:47 AM
 */
public class Problem3127MakeASquareWithTheSameColor {

    public boolean canMakeSquare(char[][] grid) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (check(grid, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] grid, int x, int y) {
        int cnt = 0;
        for (int i = x; i < x + 2; i++) {
            for (int j = y; j < y + 2; j++) {
                if (grid[i][j] == 'B') {
                    cnt++;
                }
            }
        }
        return cnt != 2;
    }
}
