package com.shuangpeng.competition.第305场周赛;

import java.util.*;

/**
 * @Description: Problem2368ReachableNodesWithRestrictions（受限条件下可到达的节点数目）
 * @Date 2022/8/20 7:24 PM
 * @Version 1.0
 */
public class Problem2368ReachableNodesWithRestrictions {

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
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
}
