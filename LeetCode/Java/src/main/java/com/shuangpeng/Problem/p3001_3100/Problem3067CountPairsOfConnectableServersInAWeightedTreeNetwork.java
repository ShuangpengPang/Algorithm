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

class Problem3067CountPairsOfConnectableServersInAWeightedTreeNetwork1 {

    Map<Integer, Integer>[] maps;
    int signalSpeed;

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        List<int[]>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], w = edge[2];
            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }
        maps = new Map[n];
        Arrays.setAll(maps, i -> new HashMap<>());
        this.signalSpeed = signalSpeed;
        dfs(graph, 0, -1);
        int[] ans = new int[n];
        dfs1(graph, 0, -1, new HashMap<>(), ans);
        for (int i = 0; i < n; i++) {
            ans[i] >>= 1;
        }
        return ans;
    }

    private void dfs(List<int[]>[] graph, int x, int p) {
        for (int[] a : graph[x]) {
            if (a[0] != p) {
                dfs(graph, a[0], x);
                maps[x].merge(a[1] % signalSpeed, 1, Integer::sum);
                for (Map.Entry<Integer, Integer> entry : maps[a[0]].entrySet()) {
                    maps[x].merge((entry.getKey() + a[1]) % signalSpeed, entry.getValue(), Integer::sum);
                }
            }
        }
    }

    private void dfs1(List<int[]>[] graph, int x, int p, Map<Integer, Integer> map, int[] ans) {
        int cnt = map.getOrDefault(0, 0);
        ans[x] = cnt * maps[x].getOrDefault(0, 0);
        for (Map.Entry<Integer, Integer> entry : maps[x].entrySet()) {
            map.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        for (int[] a : graph[x]) {
            if (a[0] != p) {
                int count = maps[a[0]].getOrDefault((signalSpeed - a[1] % signalSpeed) % signalSpeed, 0);
                if (a[1] % signalSpeed == 0) {
                    count++;
                }
                ans[x] += count * (maps[x].getOrDefault(0, 0) - count + cnt);
                for (Map.Entry<Integer, Integer> entry : maps[a[0]].entrySet()) {
                    map.merge((entry.getKey() + a[1]) % signalSpeed, -entry.getValue(), Integer::sum);
                }
                map.merge(a[1] % signalSpeed, -1, Integer::sum);
                Map<Integer, Integer> tmp = new HashMap<>(map.size());
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    tmp.put((entry.getKey() + a[1]) % signalSpeed, entry.getValue());
                }
                tmp.merge(a[1] % signalSpeed, 1, Integer::sum);
                dfs1(graph, a[0], x, tmp, ans);
                for (Map.Entry<Integer, Integer> entry : maps[a[0]].entrySet()) {
                    map.merge((entry.getKey() + a[1]) % signalSpeed, entry.getValue(), Integer::sum);
                }
                map.merge(a[1] % signalSpeed, 1, Integer::sum);
            }
        }
    }
}
