package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1260Shift2DGrid（二维网格迁移）
 * @Date 2022/7/20 9:56 AM
 * @Version 1.0
 */
public class Problem1260Shift2DGrid {

    public List<List<Integer>> shiftGrid0(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, N = m * n;
        List<List<Integer>> ans = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                list.add(0);
            }
            ans.add(list);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int c = (i * n + j + k) % N;
                int x = c / n, y = c % n;
                ans.get(x).set(y, grid[i][j]);
            }
        }
        return ans;
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, N = m * n;
        k = k % N;
        int x = k / n, y = k % n;
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[x][y] = grid[i][j];
                y++;
                if (y == n) {
                    x++;
                    y = 0;
                    if (x == m) {
                        x = 0;
                    }
                }
            }
        }
        List list = Arrays.asList(map);
        return list;
    }
}
