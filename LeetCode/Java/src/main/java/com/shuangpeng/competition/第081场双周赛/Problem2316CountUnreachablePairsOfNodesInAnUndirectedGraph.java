package com.shuangpeng.competition.第081场双周赛;

/**
 * @Description: Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph（统计无向图中无法互相到达点对数）
 * @Date 2022/7/4 2:12 PM
 * @Version 1.0
 */
public class Problem2316CountUnreachablePairsOfNodesInAnUndirectedGraph {

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
