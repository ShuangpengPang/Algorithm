package com.shuangpeng.Problem.p1001_1100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1091ShortestPathInBinaryMatrix（二进制矩阵中的最短路径）
 * @date 2023/5/26 1:55 PM
 */
public class Problem1091ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = -1;
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int x = p[0], y = p[1];
                if (x == n - 1 && y == n - 1) {
                    return step;
                }
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int nx = x + dx, ny = y + dy;
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                            grid[nx][ny] = -1;
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
