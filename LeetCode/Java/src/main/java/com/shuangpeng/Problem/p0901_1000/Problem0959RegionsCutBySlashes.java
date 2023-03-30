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
