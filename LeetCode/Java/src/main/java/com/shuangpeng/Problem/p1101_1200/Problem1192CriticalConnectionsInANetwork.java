package com.shuangpeng.Problem.p1101_1200;

import java.util.*;

/**
 * @Description: Problem1192CriticalConnectionsInANetwork（查找集群内的关键连接）
 * @Date 2022/7/21 2:40 PM
 * @Version 1.0
 */
public class Problem1192CriticalConnectionsInANetwork {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (List<Integer> conn : connections) {
            int x = conn.get(0), y = conn.get(1);
            graph[x].add(y);
            graph[y].add(x);
        }
        int[] ids = new int[n];
        Arrays.fill(ids, -1);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(graph, 0, -1, 0, ids, ans);
        return ans;
    }

    private int dfs(List<Integer>[] graph, int x, int p, int id, int[] ids, List<List<Integer>> ans) {
        ids[x] = id;
        for (int y : graph[x]) {
            if (y == p) {
                continue;
            } else if (ids[y] == -1) {
                ids[x] = Math.min(ids[x], dfs(graph, y, x, id + 1, ids, ans));
            } else {
                ids[x] = Math.min(ids[x], ids[y]);
            }
        }
        if (ids[x] == id && x != 0) {
            ans.add(Arrays.asList(p, x));
        }
        return ids[x];
    }
}
