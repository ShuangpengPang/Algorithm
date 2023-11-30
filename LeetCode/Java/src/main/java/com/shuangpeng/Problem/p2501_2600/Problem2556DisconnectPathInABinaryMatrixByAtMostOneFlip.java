package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2556DisconnectPathInABinaryMatrixByAtMostOneFlip（二进制矩阵中翻转最多一次使路径不连通）
 * @date 2023/11/30 2:15 PM
 */
public class Problem2556DisconnectPathInABinaryMatrixByAtMostOneFlip {

    public boolean isPossibleToCutPath(int[][] grid) {
        return !dfs(grid, 0, 0) || !dfs(grid, 0, 0);
    }

    private boolean dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        if (x == m - 1 && y == n - 1) {
            return true;
        }
        if ((x != 0 || y != 0) && grid[x][y] == 0) {
            return false;
        }
        grid[x][y] = 0;
        return x < m - 1 && dfs(grid, x + 1, y) || y < n - 1 && dfs(grid, x, y + 1);
    }
}
