package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

/**
 * @Description: Problem2373LargestLocalValueInAMatrix（矩阵中的局部最大值）
 * @Date 2022/9/1 5:51 PM
 * @Version 1.0
 */
public class Problem2373LargestLocalValueInAMatrix {

    // 比赛时写法
    public int[][] largestLocal0(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        ans[i][j] = Math.max(ans[i][j], grid[x][y]);
                    }
                }
            }
        }
        return ans;
    }

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                int max = 0;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        max = Math.max(max, grid[i + x][j + y]);
                    }
                }
                ans[i][j] = max;
            }
        }
        return ans;
    }
}
