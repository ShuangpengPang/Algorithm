package com.shuangpeng.competition.第059场双周赛;

import javafx.util.Pair;

import java.util.*;

public class Problem1976 {

    public int countPaths0(int n, int[][] roads) {
        final int M = (int) 1e9 + 7;
        Map<Integer, List<int[]>> graph = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int t = road[2];
            graph.get(u).add(new int[]{v, t});
            graph.get(v).add(new int[]{u, t});
        }
        int[] ways = new int[n];
        long[] costs = new long[n];
        Arrays.fill(costs, Long.MAX_VALUE);
        costs[0] = 0;
        ways[0] = 1;
        PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        queue.offer(new long[]{0, 0});
        while (!queue.isEmpty()) {
            long[] pair = queue.poll();
            int u = (int) pair[0];
            long t = pair[1];
            if (t > costs[u]) {
                continue;
            }
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int c = neighbor[1];
                long time = t + c;
                if (time < costs[v]) {
                    ways[v] = ways[u];
                    costs[v] = time;
                    queue.offer(new long[]{v, time});
                } else if (time == costs[v]) {
                    ways[v] = (ways[v] + ways[u]) % M;
                }
            }
        }
        return ways[n - 1];
    }

    public int countPaths1(int n, int[][] roads) {
        long[][] dist = new long[n][n];
        long[][] graph = new long[n][n];
        final long INF = Long.MAX_VALUE >> 1;
        final int M = (int) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], INF);
            Arrays.fill(graph[i], INF);
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int t = road[2];
            dist[u][u] = 0;
            dist[v][v] = 0;
            dist[u][v] = t;
            dist[v][u] = t;
            graph[u][u] = 0;
            graph[v][v] = 0;
            graph[u][v] = t;
            graph[v][u] = t;
        }
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        Map<Integer, List<Integer>> outGraph = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            outGraph.put(i, new ArrayList<>());
        }
        Map<Integer, Integer> inDegree = new HashMap<>();
        boolean[] visited = new boolean[n];
        inDegree.put(0, 0);
        Queue<Pair<Integer, Long>> queue = new LinkedList<>();
        queue.add(new Pair(0, 0L));
        visited[0] = true;
        long minTime = dist[0][n - 1];
        while (!queue.isEmpty()) {
            Pair<Integer, Long> pair = queue.poll();
            int u = pair.getKey();
            long t = pair.getValue();
            for (int v = 0; v < n; ++v) {
                if (v != u && graph[u][v] != INF) {
                    long time = t + graph[u][v];
                    if (time + dist[v][n - 1] == minTime) {
                        if (!visited[v]) {
                            visited[v] = true;
                            queue.offer(new Pair<>(v, time));
                        }
                        outGraph.get(u).add(v);
                        inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
                    }
                }
            }
        }
        int[] dp = new int[n];
        dp[0] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : outGraph.get(u)) {
                dp[v] = (dp[v] + dp[u]) % M;
                int degree = inDegree.get(v) - 1;
                inDegree.put(v, degree);
                if (degree == 0) {
                    q.offer(v);
                }
            }
        }
        return dp[n - 1];
    }

    public int countPaths(int n, int[][] roads) {
        long INF = Long.MAX_VALUE >> 1;
        long[][] dist = new long[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], t = road[2];
            dist[u][v] = t;
            dist[v][u] = t;
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int u = -1;
            for (int j = 0; j < n; ++j) {
                if (!visited[j] && (u == -1 || dist[0][j] < dist[0][u])) {
                    u = j;
                }
            }
            visited[u] = true;
            for (int v = 0; v < n; ++v) {
                if (dist[0][v] > dist[0][u] + dist[u][v]) {
                    dist[0][v] = dist[0][u] + dist[u][v];
                }
            }
        }
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int t = road[2];
            if (dist[0][u] + t == dist[0][v]) {
                graph.get(u).add(v);
            } else if (dist[0][v] + t == dist[0][u]) {
                graph.get(v).add(u);
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 1;
        return dfs(dp, 0, graph);
    }

    private int dfs(int[] dp, int u, List<List<Integer>> graph) {
        final int M = (int) 1e9 + 7;
        if (dp[u] != -1) {
            return dp[u];
        }
        dp[u] = 0;
        for (int v : graph.get(u)) {
            dp[u] += dfs(dp, v, graph);
            if (dp[u] >= M) {
                dp[u] -= M;
            }
        }
        return dp[u];
    }

//    public static void main(String[] args) {
//        Problem1976 a = new Problem1976();
//        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
//        a.countPaths(7, roads);
//    }
}
