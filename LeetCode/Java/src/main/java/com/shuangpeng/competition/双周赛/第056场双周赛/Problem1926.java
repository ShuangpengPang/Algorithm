package com.shuangpeng.competition.双周赛.第056场双周赛;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1926 {

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] array = queue.poll();
                for (int j = 0; j < dirs.length; j++) {
                    int x = array[0] + dirs[j][0];
                    int y = array[1] + dirs[j][1];
                    if (x < 0 || x >= m || y < 0 || y >= n
                            || maze[x][y] == '+' || visited[x][y]) {
                        continue;
                    }
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                        return step;
                    }
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return -1;
    }
}
