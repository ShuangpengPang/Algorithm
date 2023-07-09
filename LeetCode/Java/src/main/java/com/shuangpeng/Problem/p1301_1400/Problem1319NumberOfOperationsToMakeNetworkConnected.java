package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1319NumberOfOperationsToMakeNetworkConnected（连通网络的操作次数）
 * @date 2023/7/9 4:50 PM
 */
public class Problem1319NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected0(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] c : connections) {
            int x = c[0], y = c[1];
            g[x].add(y);
            g[y].add(x);
        }
        int cnt = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(g, i, visited);
                cnt++;
            }
        }
        return cnt - 1;
    }

    private void dfs(List<Integer>[] g, int x, boolean[] visited) {
        visited[x] = true;
        for (int y : g[x]) {
            if (!visited[y]) {
                dfs(g, y, visited);
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        int cnt = n;
        for (int[] c : connections) {
            cnt -= union(parent, c[0], c[1]);
        }
        return cnt - 1;
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
