package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:（树中距离之和）
 * @date 2023/7/17 4:05 PM
 **/
public class Problem0834SumOfDistanceInTree {

    public int[] sumOfDistancesInTree0(int N, int[][] edges) {
        if (N <= 1) {
            return new int[]{0};
        }
        List<List<Integer>> adjacents = new ArrayList<>();
        List<List<int[]>> distances = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacents.add(new ArrayList<>());
            distances.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adjacents.get(edges[i][0]).add(edges[i][1]);
            adjacents.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] visited = new boolean[N];
        dfs(0, adjacents, distances, visited);
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            List<int[]> list = distances.get(i);
            int sum = 0;
            for (int[] a : list) {
                sum += a[1];
            }
            answer[i] = sum;
        }
        return answer;
    }

    private void dfs(int u, List<List<Integer>> adjacents, List<List<int[]>> distances, boolean[] visited) {
        visited[u] = true;
        List<int[]> list = distances.get(u);
        list.add(new int[]{u, 0});
        List<Integer> neighbors = adjacents.get(u);
        for (int v : neighbors) {
            if (!visited[v]) {
                dfs(v, adjacents, distances, visited);
                List<int[]> vList = distances.get(v);
                int size = list.size();
                int vSize = vList.size();
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < vSize; j++) {
                        int[] distance = list.get(i);
                        int[] vDistance = vList.get(j);
                        distances.get(distance[0]).add(new int[]{vDistance[0], distance[1] + vDistance[1] + 1});
                        distances.get(vDistance[0]).add(new int[]{distance[0], distance[1] + vDistance[1] + 1});
                    }
                }
            }
        }
    }

    private int[] answer;
    private int[] size;
    private int[] dp;
    private List<List<Integer>> graph;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (N <= 1) {
            return new int[]{0};
        }
        answer = new int[N];
        size = new int[N];
        dp = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs1(0, -1);
        return answer;
    }

    private void dfs(int u, int f) {
        size[u] = 1;
        dp[u] = 0;
        for (int v : graph.get(u)) {
            if (v != f) {
                dfs(v, u);
                dp[u] += dp[v] + size[v];
                size[u] += size[v];
            }
        }
    }

    private void dfs1(int u, int f) {
        answer[u] = dp[u];
        for (int v : graph.get(u)) {
            if (v != f) {
                int du = dp[u], su = size[u];
                int dv = dp[v], sv = size[v];

                dp[u] -= dp[v] + size[v];
                size[u] -= size[v];
                dp[v] += dp[u] + size[u];
                size[v] += size[u];

                dfs1(v, u);

                dp[u] = du;
                size[u] = su;
                dp[v] = dv;
                size[v] = sv;
            }
        }
    }
}

class Problem0834SumOfDistanceInTree0 {

    List<Integer>[] g;
    int[] size, ans;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        size = new int[n];
        ans = new int[n];
        dfsDp(0, -1, 0);
        dfs(0, -1, ans[0] + n);
        return ans;
    }

    private void dfs(int x, int p, int s) {
        ans[x] = s + ans.length - (size[x] << 1);
        for (int y : g[x]) {
            if (y != p) {
                dfs(y, x, ans[x]);
            }
        }
    }

    private void dfsDp(int x, int p, int d) {
        ans[0] += d;
        size[x] = 1;
        for (int y : g[x]) {
            if (y != p) {
                dfsDp(y, x, d + 1);
                size[x] += size[y];
            }
        }
    }
}
