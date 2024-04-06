package com.shuangpeng.Problem.p3001_3100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            ans[i] = getCount(graph, i, signalSpeed);
        }
        return ans;
    }

    private int getCount(List<int[]>[] graph, int x, int signalSpeed) {
        int n = graph[x].size(), sum = 0;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = dfs(graph, graph[x].get(i)[0], x, signalSpeed, graph[x].get(i)[1]);
            sum += cnt[i];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += cnt[i] * (sum - cnt[i]);
        }
        return ans >> 1;
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
