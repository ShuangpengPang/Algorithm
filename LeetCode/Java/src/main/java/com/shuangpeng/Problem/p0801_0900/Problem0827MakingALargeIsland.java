package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem0827MakingALargeIsland {

    public int largestIsland0(int[][] grid) {
        int n = grid.length;
        int m = n * n;
        int[] parent = new int[m];
        int[] size = new int[m];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    int k = i * n + j;
                    parent[k] = k;
                    size[k] = 1;
                    int index = i * n + j;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        union(parent, size, index, (i - 1) * n + j);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        union(parent, size, index, i * n + j - 1);
                    }
                    ans = Math.max(ans, size[find(parent, index)]);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans = Math.max(ans, getCount(grid, parent, size, i, j));
                }
            }
        }
        return ans;
    }

    private int getCount(int[][] grid, int[] parent, int[] size, int i, int j) {
        int n = grid.length;
        final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Set<Integer> set = new HashSet<>();
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                set.add(find(parent, x * n + y));
            }
        }
        int ans = 1;
        for (int p : set) {
            ans += size[p];
        }
        return ans;
    }

    int union(int[] parent, int[] size, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return px;
        }
        if (size[px] >= size[py]) {
            parent[py] = px;
            size[px] += size[py];
            return px;
        }
        parent[px] = py;
        size[py] += size[px];
        return py;
    }

    int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = n * n;
        int index = 2;
        int[] size = new int[m + 2];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    size[index] = dfs(grid, i, j, index);
                    ans = Math.max(ans, size[index]);
                    ++index;
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    Set<Integer> set = new HashSet<>();
                    for (int neighbor : neighbors(grid, i, j)) {
                        set.add(grid[neighbor / n][neighbor % n]);
                    }
                    int count = 1;
                    for (int neighbor : set) {
                        count += size[neighbor];
                    }
                    ans = Math.max(ans, count);
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int index) {
        int n = grid.length;
        grid[i][j] = index;
        int ans = 1;
        for (int neighbor : neighbors(grid, i, j)) {
            int x = neighbor / n, y = neighbor % n;
            if (grid[x][y] == 1) {
                ans += dfs(grid, x, y, index);
            }
        }
        return ans;
    }

    private List<Integer> neighbors(int[][] grid, int i, int j) {
        int n = grid.length;
        final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        List<Integer> ans = new ArrayList<>();
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] != 0) {
                ans.add(x * n + y);
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0827MakingALargeIsland a = new Problem0827MakingALargeIsland();
//        int[][] grid = {{1, 0}, {0, 1}};
//        int i = a.largestIsland(grid);
//        int j = 1;
//    }
}
