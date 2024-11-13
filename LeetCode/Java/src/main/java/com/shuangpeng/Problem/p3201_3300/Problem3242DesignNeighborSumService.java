package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3242DesignNeighborSumService（设计相邻元素求和服务）
 * @date 2024/11/12 6:00 PM
 */
public class Problem3242DesignNeighborSumService {

    static class NeighborSum {

        private int n;
        private int[] location;
        private int[][] grid;

        private static final int[] dirAdj = {-1, 0, 1, 0, -1};
        private static final int[] dirDiag = {-1, -1, 1, 1, -1};

        public NeighborSum(int[][] grid) {
            this.grid = grid;
            n = grid.length;
            location = new int[n * n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    location[grid[i][j]] = i * n + j;
                }
            }
        }

        public int adjacentSum(int value) {
            return getSum(value, dirAdj);
        }

        public int diagonalSum(int value) {
            return getSum(value, dirDiag);
        }

        private int getSum(int value, int[] dir) {
            int index = location[value];
            int x = index / n, y = index % n;
            int sum = 0;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i], ny = y + dir[i + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    sum += grid[nx][ny];
                }
            }
            return sum;
        }
    }

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */

}
