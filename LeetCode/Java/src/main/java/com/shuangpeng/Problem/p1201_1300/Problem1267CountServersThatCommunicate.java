package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1267CountServersThatCommunicate（统计参与通信的服务器）
 * @date 2023/6/17 10:05 PM
 */
public class Problem1267CountServersThatCommunicate {

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int cnt = dfs(grid, visited, i, j);
                    if (cnt > 1) {
                        ans += cnt;
                    }
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, boolean[][] visited, int x, int y) {
        int m = grid.length, n = grid[0].length;
        visited[x][y] = true;
        int ans = 1;
        for (int i = 0; i < m; i++) {
            if (grid[i][y] == 1 && !visited[i][y]) {
                ans += dfs(grid, visited, i, y);
            }
        }
        for (int i = 0; i < n; i++) {
            if (grid[x][i] == 1 && !visited[x][i]) {
                ans += dfs(grid, visited, x, i);
            }
        }
        return ans;
    }
}

class Problem1267CountServersThatCommunicate0 {

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length, N = m * n;
        int[] parent = new int[N], size = new int[N], pre = new int[n];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(size, 1);
        Arrays.fill(pre, -1);
        for (int i = 0, index = 0; i < m; i++) {
            for (int j = 0, p = -1; j < n; j++, index++) {
                if (grid[i][j] == 1) {
                    union(parent, size, p, index);
                    union(parent, size, pre[j], index);
                    p = index;
                    pre[j] = index;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (parent[i] == i && size[i] > 1) {
                ans += size[i];
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int[] size, int x, int y) {
        if (x == -1 || y == -1) {
            return;
        }
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
            size[px] += size[py];
        }
    }
}

class Problem1267CountServersThatCommunicate1 {

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length, N = m + n;
        int[] parent = new int[N], size = new int[N];
        Arrays.setAll(parent, i -> i);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    union(parent, size, i, m + j);
                    size[find(parent, i)]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (parent[i] == i && size[i] > 1) {
                ans += size[i];
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int[] size, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
            size[px] += size[py];
        }
    }
}

class Problem1267CountServersThatCommunicate2 {

    public int countServers0(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] cnt1 = new int[m], cnt2 = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt1[i]++;
                    cnt2[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (cnt1[i] > 1 || cnt2[j] > 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countServers1(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                    ans++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (rows[i] == 1) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        if (cols[j] == 1) {
                            ans--;
                        }
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m], col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
