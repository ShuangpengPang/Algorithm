package com.shuangpeng.Problem.p0401_0500;

import java.util.*;

/**
 * @Description: Problem0417PacificAtlanticWaterFlow
 * @Date 2022/4/27 10:12 AM
 * @Version 1.0
 */
public class Problem0417PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic0(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] result = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(heights, i, j, result, visited) == 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
                visited[i][j] = true;
            }
        }
        return ans;
    }

    private int dfs(int[][] heights, int r, int c, int[][] result, boolean[][] visited) {
        int m = heights.length, n = heights[0].length;
        if (r == 0 || c == 0) {
            result[r][c] |= 1;
        }
        if (r == m - 1 || c == n - 1) {
            result[r][c] |= 2;
        }
        if ((result[r][c] & 3) == 3) {
            result[r][c] = 3;
            return 3;
        }
        result[r][c] |= 4;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int x = r + dir[0], y = c + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] <= heights[r][c]) {
                if (visited[x][y] || (result[x][y] & 4) == 4) {
                    result[r][c] |= result[x][y];
                } else {
                    result[r][c] |= dfs(heights, x, y, result, visited);
                }
            }
        }
        result[r][c] &= 3;
        return result[r][c];
    }

    public List<List<Integer>> pacificAtlantic2(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < n; ++i) {
            dfs(heights, 0, i, 1, result);
            dfs(heights, m - 1, i, 2, result);
        }
        for (int i = 0; i < m; ++i) {
            dfs(heights, i, 0, 1, result);
            dfs(heights, i, n - 1, 2, result);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (result[i][j] == 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] heights, int r, int c, int v, int[][] result) {
        int m = heights.length, n = heights[0].length;
        result[r][c] |= v;
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] dir : dirs) {
            int x = r + dir[0], y = c + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[r][c] && (result[x][y] & v) == 0) {
                dfs(heights, x, y, v, result);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; ++i) {
            bfs(heights, result, i, 0, 1);
            bfs(heights, result, i, n - 1, 2);
        }
        for (int i = 0; i < n; ++i) {
            bfs(heights, result, 0, i, 1);
            bfs(heights, result, m - 1, i, 2);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (result[i][j] == 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private void bfs(int[][] heights, int[][] result, int r, int c, int v) {
        int m = heights.length, n = heights[0].length;
        if ((result[r][c] & v) == v) {
            return;
        }
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        result[r][c] |= v;
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int a = pair[0], b = pair[1];
            for (int[] dir : dirs) {
                int x = a + dir[0], y = b + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[a][b] && (result[x][y] & v) == 0) {
                    result[x][y] |= v;
                    queue.add(new int[]{x, y});
                }
            }
        }
    }
}
