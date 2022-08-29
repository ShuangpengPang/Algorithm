package com.shuangpeng.competition.第305场周赛;

import java.util.*;

/**
 * @Description: Problem2368ReachableNodesWithRestrictions（受限条件下可到达的节点数目）
 * @Date 2022/8/20 7:24 PM
 * @Version 1.0
 */
public class Problem2368ReachableNodesWithRestrictions {

    // 比赛时写法
    public int reachableNodes0(int n, int[][] edges, int[] restricted) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            graph[x].add(y);
            graph[y].add(x);
        }
        Set<Integer> set = new HashSet<>();
        for (int r : restricted) {
            set.add(r);
        }
        return dfs(graph, 0, -1, set);
    }

    private int dfs(List<Integer>[] graph, int n, int p, Set<Integer> set) {
        int ans = 1;
        for (int x : graph[n]) {
            if (x != p && !set.contains(x)) {
                ans += dfs(graph, x, n, set);
            }
        }
        return ans;
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean[] invalid = new boolean[n];
        for (int r : restricted) {
            invalid[r] = true;
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            if (invalid[x] || invalid[y]) {
                continue;
            }
            union(parent, x, y);
        }
        return -parent[find(parent, 0)];
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (parent[px] <= parent[py]) {
            parent[px] += parent[py];
            parent[py] = px;
        } else {
            parent[py] += parent[px];
            parent[px] = py;
        }
    }

    private int find(int[] parent, int x) {
        if (parent[x] >= 0) {
            parent[x] = find(parent, parent[x]);
            return parent[x];
        }
        return x;
    }

    // xj - xi + yi + yj = t
    // xj + yj - (xi - yi) = t
}
