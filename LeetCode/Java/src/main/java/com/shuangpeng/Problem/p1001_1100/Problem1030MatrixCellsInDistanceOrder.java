package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1030MatrixCellsInDistanceOrder（距离顺序排列矩阵单元格）
 * @date 2024/1/6 4:47 PM
 */
public class Problem1030MatrixCellsInDistanceOrder {

    public int[][] allCellsDistOrder0(int rows, int cols, int rCenter, int cCenter) {
        int n = rows * cols;
        int[][] ans = new int[n][2];
        int index = 0;
        for (int d = 0; index < n; d++) {
            for (int r = rCenter - d; r <= rCenter + d; r++) {
                if (r >= 0 && r < rows) {
                    int len = d - Math.abs(r - rCenter), c1 = cCenter - len, c2 = cCenter + len;
                    if (c1 >= 0 && c1 < cols) {
                        ans[index][0] = r;
                        ans[index][1] = c1;
                        index++;
                    }
                    if (c1 != c2 && c2 >= 0 && c2 < cols) {
                        ans[index][0] = r;
                        ans[index][1] = c2;
                        index++;
                    }
                }
            }
        }
        return ans;
    }

    public int[][] allCellsDistOrder1(int rows, int cols, int rCenter, int cCenter) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] ans = new int[rows * cols][];
        boolean[][] visited = new boolean[rows][cols];
        q.offer(new int[]{rCenter, cCenter});
        visited[rCenter][cCenter] = true;
        int index = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                for (int d = 0; d < 4; d++) {
                    int x = p[0] + dirs[d], y = p[1] + dirs[d + 1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y]) {
                        q.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
                ans[index++] = p;
            }
        }
        return ans;
    }

    public int[][] allCellsDistOrder2(int rows, int cols, int rCenter, int cCenter) {
        int n = rows * cols;
        int[][] ans = new int[n][2];
        for (int i = 0, index = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++, index++) {
                ans[index][0] = i;
                ans[index][1] = j;
            }
        }
        Arrays.sort(ans, Comparator.comparing(a -> Math.abs(rCenter - a[0]) + Math.abs(cCenter -a[1])));
        return ans;
    }

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int[] dr = {1, 1, -1, -1};
        int[] dc = {1, -1, -1, 1};
        int[][] ans = new int[rows * cols][];
        int index = 0, r = rCenter, c = cCenter;
        ans[index++] = new int[]{r, c};
        int maxDist = Math.max(rCenter, rows - rCenter - 1) + Math.max(cCenter, cols - cCenter - 1);
        for (int d = 1; d <= maxDist; d++) {
            r--;
            for (int i = 0; i < 4; i++) {
                while (i % 2 == 0 && r != rCenter || i % 2 != 0 && c != cCenter) {
                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        ans[index++] = new int[]{r, c};
                    }
                    r += dr[i];
                    c += dc[i];
                }
            }
        }
        return ans;
    }
}
