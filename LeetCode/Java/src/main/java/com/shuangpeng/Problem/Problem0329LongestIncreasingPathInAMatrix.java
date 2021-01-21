package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Problem0329LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath0(int[][] matrix) {
        int[][] coord = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int rows = matrix.length;
        int cols = matrix[0].length;
        Map<Integer, Integer> map = new HashMap<>(rows * cols);
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                count = Math.max(count, dfs(matrix, map, coord, rows, cols, i, j));
            }
        }
        return count;
    }

    private int dfs(int[][] matrix, Map<Integer, Integer> map,
                    int[][] coord, int rows, int cols, int i, int j) {
        int key = i * cols + j;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int count = 1;
        for (int k = 0; k < coord.length; k++) {
            int x = i + coord[k][0];
            int y = j + coord[k][1];
            if (x >= 0 && x < rows && y >= 0 && y < cols
                    && matrix[x][y] > matrix[i][j]) {
                count = Math.max(count, 1 + dfs(matrix, map, coord, rows, cols, x, y));
            }
        }
        map.put(key, count);
        return count;
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] outdegree = new int[rows][cols];
        int[][] location = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int a = 0; a < location.length; a++) {
                    int x = i + location[a][0];
                    int y = j + location[a][1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols
                            && matrix[i][j] < matrix[x][y]) {
                        outdegree[i][j]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (outdegree[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int answer = 0;
        while (!queue.isEmpty()) {
            answer++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] data = queue.poll();
                int row = data[0];
                int col = data[1];
                for (int a = 0; a < location.length; a++) {
                    int newRow = row + location[a][0];
                    int newCol = col + location[a][1];
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                            && matrix[row][col] > matrix[newRow][newCol]) {
                        outdegree[newRow][newCol]--;
                        if (outdegree[newRow][newCol] == 0) {
                            queue.offer(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }
        return answer;
    }
}
