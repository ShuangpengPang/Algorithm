package com.shuangpeng.Problem;

import java.util.*;

public class Problem0743NetworkDelayTime {

    public int networkDelayTime0(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int t = time[2];
            List<int[]> list = graph.getOrDefault(u, new ArrayList<>());
            list.add(new int[]{v, t});
            graph.put(u, list);
        }
        boolean[] visited = new boolean[n + 1];
        int[] array = new int[n + 1];
        Arrays.fill(array, Integer.MAX_VALUE);
        array[k] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(a -> array[a]));
        queue.offer(k);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (!visited[u]) {
                visited[u] = true;
                List<int[]> neighbors = graph.get(u);
                if (neighbors != null) {
                    for (int[] neighbor : neighbors) {
                        int v = neighbor[0];
                        int t = neighbor[1];
                        if (!visited[v]) {
                            array[v] = Math.min(array[v], array[u] + t);
                            queue.offer(v);
                        }
                    }
                }
            }
        }
        int maxTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (array[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxTime = Math.max(maxTime, array[i]);
        }
        return maxTime;
    }

    public int networkDelayTime1(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE >> 1;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        for (int[] time : times) {
            int u = time[0] - 1;
            int v = time[1] - 1;
            g[u][v] = time[2];
        }
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int m = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (m == -1 || dist[j] <= dist[m])) {
                    m = j;
                }
            }
            if (dist[m] == INF) {
                return -1;
            }
            visited[m] = true;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && g[m][j] != INF) {
                    dist[j] = Math.min(dist[j], dist[m] + g[m][j]);
                }
            }
        }
        return Arrays.stream(dist).max().getAsInt();
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE >> 1;
        List<List<int[]>> g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] time : times) {
            g.get(time[0] - 1).add(new int[]{time[1] - 1, time[2]});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.offer(new int[]{k - 1, 0});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int u = pair[0];
            int t = pair[1];
            if (dist[u] < t) {
                continue;
            }
            for (int[] neighbor : g.get(u)) {
                int v = neighbor[0];
                int c = neighbor[1];
                if (dist[v] > dist[u] + c) {
                    dist[v] = dist[u] + c;
                    queue.offer(new int[]{v, dist[v]});
                }
            }
        }
        int maxTime = Arrays.stream(dist).max().getAsInt();
        return maxTime == INF ? -1 : maxTime;
    }
}
