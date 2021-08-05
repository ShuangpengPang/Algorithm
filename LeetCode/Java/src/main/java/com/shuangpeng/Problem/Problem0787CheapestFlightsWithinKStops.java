package com.shuangpeng.Problem;

import java.util.*;

public class Problem0787CheapestFlightsWithinKStops {

    public int findCheapestPrice0(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            List<int[]> neighbors = graph.getOrDefault(flight[0], new ArrayList<>());
            neighbors.add(new int[]{flight[1], flight[2]});
            graph.put(flight[0], neighbors);
        }
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE);
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.offer(new int[]{src, -1, 0});
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int u = pair[0];
            int s = pair[1];
            if (u == dst) {
                return pair[2];
            }
            if (s < k && visited[u] > s) {
                visited[u] = s;
                List<int[]> neighbors = graph.get(u);
                if (neighbors != null) {
                    for (int[] neighbor : neighbors) {
                        int v = neighbor[0];
                        int c = neighbor[1];
                        if (visited[v] > s + 1) {
                            queue.offer(new int[]{v, s + 1, pair[2] + c});
                        }
                    }
                }
            }
        }
        return -1;
    }

    // 深度优先TLE
    public int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            List<int[]> neighbors = graph.getOrDefault(flight[0], new ArrayList<>());
            neighbors.add(new int[]{flight[1], flight[2]});
            graph.put(flight[0], neighbors);
        }
        int minCost = dfs(graph, 0, src, dst, k + 1, new boolean[n], new int[]{Integer.MAX_VALUE});
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    private int dfs(Map<Integer, List<int[]>> graph, int cost, int u, int dst, int k,
                    boolean[] visited, int[] memo) {
        if (k < 0) {
            return Integer.MAX_VALUE;
        }
        if (u == dst) {
            memo[0] = cost;
            return cost;
        }
        visited[u] = true;
        List<int[]> neighbors = graph.get(u);
        int minCost = Integer.MAX_VALUE;
        if (neighbors != null) {
            for (int[] pair : neighbors) {
                int v = pair[0];
                int c = pair[1];
                if (!visited[v] && cost + c < memo[0]) {
                    minCost = Math.min(minCost, dfs(graph, cost + c, v, dst, k - 1, visited, memo));
                }
            }
        }
        visited[u] = false;
        return minCost;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[n][K + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[src][0] = 0;
        for (int i = 1; i <= K + 1; i++) {
            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                if (dp[from][i - 1] != Integer.MAX_VALUE) {
                    dp[to][i] = Math.min(dp[to][i], dp[from][i - 1] + flight[2]);
                }
            }
        }
        int minValue = Arrays.stream(dp[dst]).min().getAsInt();
        return minValue != Integer.MAX_VALUE ? minValue : -1;
    }

//    public static void main(String[] args) {
//        Problem0787CheapestFlightsWithinKStops a = new Problem0787CheapestFlightsWithinKStops();
//        int[][] flights = {{0, 1, 100}, {0, 2, 100}, {0, 3, 10}, {1, 2, 100}, {1, 4, 10}, {2, 1, 10}, {2, 3, 100}, {2, 4, 100}, {3, 2, 10}, {3, 4, 100}};
//        a.findCheapestPrice(5, flights, 0, 4, 3);
//    }
}
