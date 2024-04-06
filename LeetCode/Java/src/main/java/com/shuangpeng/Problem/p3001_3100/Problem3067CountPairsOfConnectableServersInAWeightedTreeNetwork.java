package com.shuangpeng.Problem.p3001_3100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3067CountPairsOfConnectableServersInAWeightedTreeNetwork（在带权树网络中统计可连接服务器对数据）
 * @date 2024/4/5 10:28 PM
 */
public class Problem3067CountPairsOfConnectableServersInAWeightedTreeNetwork {

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int[] a : graph[i]) {
                int cnt = dfs(graph, a[0], i, signalSpeed, a[1]);
                ans[i] += sum * cnt;
                sum += cnt;
            }
        }
        return ans;
    }

    private int dfs(List<int[]>[] graph, int x, int p, int signalSpeed, int d) {
        int ans = d % signalSpeed == 0 ? 1 : 0;
        for (int[] a : graph[x]) {
            int y = a[0], w = a[1];
            if (y != p) {
                ans += dfs(graph, y, x, signalSpeed, d + w);
            }
        }
        return ans;
    }
}

class Problem3067CountPairsOfConnectableServersInAWeightedTreeNetwork0 {

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {

        int n = edges.length + 1;
        List<int[]>[] adjs = new List[n];
        for (int i = 0; i < n; i++)
            adjs[i] = new ArrayList<>();
        int[]r = new int[n];
        for (int[] e : edges) {
            adjs[e[0]].add(new int[] {e[1],e[2]});
            adjs[e[1]].add(new int[] {e[0],e[2]});
        }
        Map<Integer, Integer> memo[][] = new Map[n + 1][n + 1];
        for (int i = 0; i < n; i++) {

            List<Integer> c = new ArrayList<>();
            for (int[] to : adjs[i]) {
                int count = count(to[0],to[1], i, adjs, signalSpeed, memo).getOrDefault(0 ,0);
                if (count != 0)
                    c.add(count);
            }
            for (int p = 0; p < c.size(); p++) {
                for(int q = 0; q <p; q++) {
                    r[i] += c.get(p) *c.get(q);
                }
            }
        }

        return r;
    }

    private Map<Integer, Integer> count(int root, int cost, int p, List<int[]>[]adj, int k, Map<Integer, Integer>[][]memo) {

        if (memo[root][p] == null) {
            Map<Integer, Integer> ret = new HashMap<>();
            ret.put(cost % k, ret.getOrDefault(cost % k, 0) + 1);
            for (int[] to : adj[root]) {
                if (to[0] != p) {
                    for (Map.Entry<Integer, Integer> e : count(to[0], to[1], root, adj, k, memo).entrySet()) {
                        ret.put((e.getKey() + cost) % k, ret.getOrDefault((e.getKey() + cost) % k, 0) + e.getValue());
                    }
                }
            }
            memo[root][p] = ret;
        }
        return memo[root][p];
    }
}
