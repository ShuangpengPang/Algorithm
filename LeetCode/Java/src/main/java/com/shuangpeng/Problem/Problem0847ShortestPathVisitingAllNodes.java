package com.shuangpeng.Problem;

import java.util.*;

public class Problem0847ShortestPathVisitingAllNodes {

    // TLE
    public int shortestPathLength0(int[][] graph) {
        int n = graph.length;
        int[] minPath = new int[]{Integer.MAX_VALUE};
        Map<Integer, Integer> minPathMap = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            dfs(graph, i, 0, 0, (1 << n) - 1, minPath, new HashMap<>(), new HashMap<>());
        }
        return minPath[0];
    }

    private int dfs(int[][] graph, int u, int path, int status, int target, int[] p,
                    Map<Integer, Integer> pathMap, Map<Integer, Integer> minPathMap) {
        status |= 1 << u;
        int n = graph.length;
        int key = (u << n) | status;
        if (minPathMap.containsKey(key)) {
            int result = path + minPathMap.get(key);
            p[0] = Math.min(p[0], result);
            return result;
        }
        if (status == target) {
            p[0] = Math.min(p[0], path);
            return path;
        }
        if (path >= p[0]) {
            return Integer.MAX_VALUE;
        }
        if (path > pathMap.getOrDefault(key, Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        }
        pathMap.put(key, path);
        int minPath = Integer.MAX_VALUE;
        for (int v : graph[u]) {
            minPath = Math.min(minPath, dfs(graph, v, path + 1, status, target, p, pathMap, minPathMap));
        }
        if (minPath != Integer.MAX_VALUE) {
            minPathMap.put(key, minPath - path);
        }
        p[0] = Math.min(p[0], minPath);
        return minPath;
    }

    public int shortestPathLength1(int[][] graph) {
        int n = graph.length;
        int target = (1 << n) - 1;
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer>[] masks = new Set[n];
        for (int i = 0; i < n; ++i) {
            int mask = 1 << i;
            queue.offer(new int[]{i, mask, 0});
            masks[i] = new HashSet<>();
            masks[i].add(mask);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] tuple = queue.poll();
                int u = tuple[0];
                int mask = tuple[1];
                int path = tuple[2];
                if (mask == target) {
                    return path;
                }
                for (int v : graph[u]) {
                    int m = mask | 1 << v;
                    if (!masks[v].contains(m)) {
                        masks[v].add(m);
                        queue.offer(new int[]{v, m, path + 1});
                    }
                }
            }
        }
        return -1;
    }

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        final int M = 1 << n;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; ++i) {
            dist[i][i] = 0;
            for (int j : graph[i]) {
                dist[i][j] = 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dist[i][j] != Integer.MAX_VALUE) {
                    for (int k = 0; k < n; ++k) {
                        if (dist[j][k] != Integer.MAX_VALUE) {
                            dist[i][k] = Math.min(dist[i][k], dist[i][j] + dist[j][k]);
                            dist[k][i] = dist[i][k];
                        }
                    }
                }
            }
        }
        int[][] dp = new int[n][M];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; ++i) {
            dp[i][1 << i] = 0;
        }
        for (int status = 0; status < M; ++status) {
            for (int i = 0; i < n; ++i) {
                int m = 1 << i;
                if ((status & m) != 0) {
                    continue;
                }
                for (int j = 0; j < n; ++j) {
                    if ((status & (1 << j)) != 0) {
                        dp[i][status | m] = Math.min(dp[i][status | m], dp[j][status] + dist[j][i]);
                    }
                }
            }
        }
        int minPath = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            minPath = Math.min(minPath, dp[i][M - 1]);
        }
        return minPath;
    }

//    public static void main(String[] args) {
//        Problem0847ShortestPathVisitingAllNodes a = new Problem0847ShortestPathVisitingAllNodes();
//        a.shortestPathLength(new int[][]{{1, 2, 3}, {0}, {0}, {0}});
//    }
}
