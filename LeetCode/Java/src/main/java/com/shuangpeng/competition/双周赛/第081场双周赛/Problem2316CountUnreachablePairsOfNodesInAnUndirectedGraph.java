package com.shuangpeng.competition.双周赛.第081场双周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph（统计无向图中无法互相到达点对数）
 * @Date 2022/7/4 2:12 PM
 * @Version 1.0
 */
public class Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph {

    // 比赛时写法
    public long countPairs(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] p : edges) {
            union(parent, size, p[0], p[1]);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += n - size[find(parent, i)];
        }
        return ans / 2;
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

class Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph0 {

    public long countPairs(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] edge : edges) {
            union(parent, size, edge[0], edge[1]);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += n - size[find(parent, i)];
        }
        return ans >> 1;
    }

    private void union(int[] parent, int[] size, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            if (size[px] <= size[py]) {
                parent[py] = px;
                size[px] += size[py];
            } else {
                parent[px] = py;
                size[py] += size[px];
            }
        }
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}

class Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph1 {

    List<Integer>[] g;
    boolean[] visited;
    int cnt = 0;

    public long countPairs(int n, int[][] edges) {
        g = new List[n];
        visited = new boolean[n];
        cnt = 0;
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        long ans = 0;
        for (int i = 0, total = 0; i < n; i++) {
            if (!visited[i]) {
                cnt = 0;
                dfs(i);
                ans += (long) cnt * total;
                total += cnt;
            }
        }
        return ans;
    }

    private void dfs(int i) {
        visited[i] = true;
        cnt++;
        for (int u : g[i]) {
            if (!visited[u]) {
                dfs(u);
            }
        }
    }
}

class Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph2 {

    public long countPairs(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        long ans = (long) n * (n - 1) / 2;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            int px = find(parent, x), py = find(parent, y);
            if (px != py) {
                ans -= (long) size[px] * size[py];
                parent[py] = px;
                size[px] += size[py];
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}
