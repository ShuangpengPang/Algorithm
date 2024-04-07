package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2639FindTheWidthOfColumnsOfAGrid（查询网格图中每一列的宽度）
 * @date 2024/4/7 4:08 PM
 */
public class Problem2639FindTheWidthOfColumnsOfAGrid {

    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                minValue = Math.min(minValue, grid[i][j]);
                maxValue = Math.max(maxValue, grid[i][j]);
            }
            ans[j] = Math.max(getWidth(minValue), getWidth(maxValue));
        }
        return ans;
    }

    private int getWidth(int x) {
        int ans = 0;
        if (x < 0) {
            x = -x;
            ans = 1;
        }
        while (x > 9) {
            ans++;
            x /= 10;
        }
        return ans + 1;
    }
}
