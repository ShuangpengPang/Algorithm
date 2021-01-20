package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0329LongestIncreasingPathInAMatrix {

    public int longestIncreasingPath(int[][] matrix) {
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
}
