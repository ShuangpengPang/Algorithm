package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0463IslandPerimeter（岛屿的周长）
 * @date 2023/5/6 12:21 PM
 */
public class Problem0463IslandPerimeter {

    int m, n;
    int[] dirs = {-1, 0, 1, 0, -1};
    int[][] grid;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return dfs(i, j, new boolean[m][n]);
                }
            }
        }
        return 0;
    }

    private int dfs(int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        int ans = 0;
        for (int d = 0; d < 4; d++) {
            int x = i + dirs[d], y = j + dirs[d + 1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                ans++;
            } else if (!visited[x][y]) {
                ans += dfs(x, y, visited);
            }
        }
        return ans;
    }
}

class Problem0463IslandPerimeter0 {

    public int islandPerimeter0(int[][] grid) {
        int[] dirs = {-1, 0, 1, 0, -1};
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int x = i + dirs[d], y = j + dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    ans += 4;
                    if (j < n - 1 && grid[i][j + 1] == 1) {
                        ans -= 2;
                    }
                    if (i < m - 1 && grid[i + 1][j] == 1) {
                        ans -= 2;
                    }
                }
            }
        }
        return ans;
    }
}
