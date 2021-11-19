package com.shuangpeng.Problem.p0401_0500;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem0407TrappingRainWaterII {

    public int trapRainWater0(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pg = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < n; ++i) {
            pg.offer(new int[]{0, i, heightMap[0][i]});
            pg.offer(new int[]{m - 1, i, heightMap[m - 1][i]});
            visited[0][i] = true;
            visited[m - 1][i] = true;
        }
        for (int i = 1; i < m - 1; ++i) {
            pg.offer(new int[]{i, 0, heightMap[i][0]});
            pg.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pg.isEmpty()) {
            int[] tuple = pg.poll();
            int x = tuple[0], y = tuple[1], h = tuple[2];
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    int height = Math.max(h, heightMap[nx][ny]);
                    ans += height - heightMap[nx][ny];
                    pg.offer(new int[]{nx, ny, height});
                    visited[nx][ny] = true;
                }
            }
        }
        return ans;
    }

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        int maxHeight = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                maxHeight = Math.max(maxHeight, heightMap[i][j]);
            }
        }
        int[][] water = new int[m][n];
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                water[i][j] = maxHeight;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            water[0][i] = heightMap[0][i];
            water[m - 1][i] = heightMap[m - 1][i];
            queue.offer(i);
            queue.offer((m - 1) * n + i);
        }
        for (int i = 1; i < m - 1; ++i) {
            water[i][0] = heightMap[i][0];
            water[i][n - 1] = heightMap[i][n - 1];
            queue.offer(i * n);
            queue.offer(i * n + n - 1);
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int num = queue.poll();
            int x = num / n, y = num % n;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                        && water[nx][ny] > water[x][y] && water[nx][ny] > heightMap[nx][ny]) {
                    water[nx][ny] = Math.max(water[x][y], heightMap[nx][ny]);
                    queue.offer(nx * n + ny);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += water[i][j] - heightMap[i][j];
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0407TrappingRainWaterII a = new Problem0407TrappingRainWaterII();
//        int[][] heightMap = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
//        a.trapRainWater(heightMap);
//    }
}
