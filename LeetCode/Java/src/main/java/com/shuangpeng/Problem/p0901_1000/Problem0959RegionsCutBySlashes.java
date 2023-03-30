package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0959RegionsCutBySlashes（由斜杠划分区域）
 * @date 2023/3/30 3:56 PM
 */
public class Problem0959RegionsCutBySlashes {

    public int regionsBySlashes(String[] grid) {
        int n = grid.length, N = n << 2;
        List<Integer>[] adj = new List[N * n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = 0; j < n; j++, k += 4) {
                int k1 = k + 1, k2 = k + 2, k3 = k + 3;
                if (i > 0) {
                    connect(adj, k, k - N + 2);
                }
                if (j > 0) {
                    connect(adj, k3, k - 3);
                }
                char c = grid[i].charAt(j);
                if (c == '/') {
                    connect(adj, k, k3);
                    connect(adj, k1, k2);
                } else if (c == '\\') {
                    connect(adj, k, k1);
                    connect(adj, k2, k3);
                } else {
                    connect(adj, k, k1);
                    connect(adj, k1, k2);
                    connect(adj, k2, k3);
                }
            }
        }
        int ans = 0;
        boolean[] visited = new boolean[N * n];
        for (int i = 0; i < N * n; i++) {
            if (!visited[i]) {
                ans++;
                dfs(adj, i, visited);
            }
        }
        return ans;
    }

    private void connect(List<Integer>[] adj, int x, int y) {
        adj[x].add(y);
        adj[y].add(x);
    }

    private void dfs(List<Integer>[] adj, int x, boolean[] visited) {
        visited[x] = true;
        for (int y : adj[x]) {
            if (!visited[y]) {
                dfs(adj, y, visited);
            }
        }
    }
}

class Problem0959RegionsCutBySlashes0 {

    public int regionsBySlashes(String[] grid) {
        int n = grid.length, M = n << 2, N = M * n, ans = N;
        int[] parent = new int[N];
        Arrays.setAll(parent, i -> i);
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = 0; j < n; j++, k += 4) {
                int k1 = k + 1, k2 = k + 2, k3 = k + 3;
                if (i > 0) {
                    ans -= union(parent, k, k - M + 2);
                }
                if (j > 0) {
                    ans -= union(parent, k3, k - 3);
                }
                char c = grid[i].charAt(j);
                if (c == '/') {
                    ans -= union(parent, k, k3);
                    ans -= union(parent, k1, k2);
                } else if (c == '\\') {
                    ans -= union(parent, k, k1);
                    ans -= union(parent, k2, k3);
                } else {
                    ans -= union(parent, k, k1);
                    ans -= union(parent, k1, k2);
                    ans -= union(parent, k2, k3);
                }
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private int union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return 0;
        }
        parent[py] = px;
        return 1;
    }
}
