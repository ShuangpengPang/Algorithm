package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem054201Matrix {

    public int[][] updateMatrix0(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int d = 0;
        int[][] coord = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            d++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] array = queue.poll();
                int x = array[0];
                int y = array[1];
                for (int j = 0; j < coord.length; j++) {
                    int a = x + coord[j][0];
                    int b = y + coord[j][1];
                    if (a >= 0 && a < m && b >= 0 && b < n && !visited[a][b]) {
                        visited[a][b] = true;
                        mat[a][b] = d;
                        queue.offer(new int[]{a, b});
                    }
                }
            }
        }
        return mat;
    }

    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE >> 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dist[i][j] = 0;
                    continue;
                }
                if (i > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                if (j > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    dist[i][j] = 0;
                    continue;
                }
                if (i < m - 1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j < n - 1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }
        return dist;
    }
}
