package com.shuangpeng.Problem.p2401_2500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2500DeleteGreatestValueInEachRow（删除每行中的最大值）
 * @date 2023/7/27 10:34 AM
 */
public class Problem2500DeleteGreatestValueInEachRow {

    public int deleteGreatestValue(int[][] grid) {
        for (int[] row : grid) {
            Arrays.sort(row);
        }
        int m = grid.length, n = grid[0].length, sum = 0;
        for (int j = 0; j < n; j++) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, grid[i][j]);
            }
            sum += max;
        }
        return sum;
    }
}
