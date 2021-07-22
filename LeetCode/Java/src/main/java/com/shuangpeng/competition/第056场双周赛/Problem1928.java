package com.shuangpeng.competition.第056场双周赛;

import java.util.*;

public class Problem1928 {

    public int minCost0(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        Map<Integer, Integer>[] graph = new Map[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];
            graph[u].put(v, Math.min(t, graph[u].getOrDefault(v, Integer.MAX_VALUE)));
            graph[v].put(u, Math.min(t, graph[v].getOrDefault(u, Integer.MAX_VALUE)));
        }
        int cost = dfs(0, 0, maxTime, passingFees, graph, new boolean[n]);
        return cost == (Integer.MAX_VALUE >> 1) ? -1 : cost;
    }

    private int dfs(int u, int cost, int remainTime, int[] passingFees,
                    Map<Integer, Integer>[] graph, boolean[] visited) {
        int n = passingFees.length;
        cost += passingFees[u];
        if (u == n - 1) {
            return cost;
        }
        visited[u] = true;
        int minCost = Integer.MAX_VALUE >> 1;
        for (Map.Entry<Integer, Integer> neighbor : graph[u].entrySet()) {
            int v = neighbor.getKey();
            int time = neighbor.getValue();
            if (!visited[v] && time <= remainTime) {
                minCost = Math.min(minCost, dfs(v, cost, remainTime - time, passingFees, graph, visited));
            }
        }
        visited[u] = false;
        return minCost;
    }

    public int minCost1(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        Map<Integer, Integer>[] graph = new Map[n];
        int totalCost = 0;
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
            totalCost += passingFees[i];
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];
            graph[u].put(v, Math.min(t, graph[u].getOrDefault(v, Integer.MAX_VALUE)));
            graph[v].put(u, Math.min(t, graph[v].getOrDefault(u, Integer.MAX_VALUE)));
        }
        int[] times = new int[n];
        Arrays.fill(times, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];
        times[n - 1] = 0;
        PriorityQueue<Integer> timeQueue = new PriorityQueue<>(Comparator.comparingInt(a -> times[a]));
        timeQueue.offer(n - 1);
        while (!timeQueue.isEmpty()) {
            int city = timeQueue.poll();
            int time = times[city];
            if (!visited[city]) {
                visited[city] = true;
                for (Map.Entry<Integer, Integer> entry : graph[city].entrySet()) {
                    int neighbor = entry.getKey();
                    int t = entry.getValue() + time;
                    if (t < times[neighbor]) {
                        times[neighbor] = t;
                        timeQueue.offer(neighbor);
                    }
                }
            }
        }
        if (times[0] > maxTime) {
            return -1;
        }
        visited = new boolean[n];
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[n - 1] = passingFees[n - 1];
        PriorityQueue<Integer> costQueue = new PriorityQueue<>(Comparator.comparingInt(a -> costs[a]));
        costQueue.offer(n - 1);
        while (!costQueue.isEmpty()) {
            int city = costQueue.poll();
            int cost = costs[city];
            if (!visited[city]) {
                visited[city] = true;
                for (int neighbor : graph[city].keySet()) {
                    int c = cost + passingFees[neighbor];
                    if (c < costs[neighbor]) {
                        costs[neighbor] = c;
                        costQueue.offer(neighbor);
                    }
                }
            }
        }
        int left = costs[0], right = totalCost;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(0, mid, maxTime, new boolean[n], graph, passingFees, times, costs)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int u, int remainCost, int remainTime, boolean[] visited,
                          Map<Integer, Integer>[] graph, int[] passingFees, int[] times, int[] costs) {
        int n = passingFees.length;
        if (u == n - 1) {
            return true;
        }
        remainCost -= passingFees[u];
        visited[u] = true;
        for (Map.Entry<Integer, Integer> entry : graph[u].entrySet()) {
            int v = entry.getKey();
            int time = remainTime - entry.getValue();
            if (!visited[v] && time >= times[v] && remainCost >= costs[v] && check(v, remainCost, time, visited, graph, passingFees, times, costs)) {
                visited[u] = false;
                return true;
            }
        }
        visited[u] = false;
        return false;
    }

    public int minCost2(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        Map<Integer, Integer>[] graph = new Map[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];
            graph[u].put(v, Math.min(t, graph[u].getOrDefault(v, Integer.MAX_VALUE)));
            graph[v].put(u, Math.min(t, graph[v].getOrDefault(u, Integer.MAX_VALUE)));
        }
        int[][] dp = new int[maxTime + 1][n];
        for (int i = 0; i <= maxTime; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE >> 1);
        }
        dp[0][0] = passingFees[0];
        for (int t = 0; t <= maxTime; t++) {
            for (int i = 1; i < n; i++) {
                for (int neighbor : graph[i].keySet()) {
                    int time = t - graph[i].get(neighbor);
                    if (time >= 0) {
                        dp[t][i] = Math.min(dp[t][i], dp[time][neighbor] + passingFees[i]);
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= maxTime; i++) {
            min = Math.min(min, dp[i][n - 1]);
        }
        return min < (Integer.MAX_VALUE >> 1) ? min : -1;
    }

    public int minCost3(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        int[][] dp = new int[maxTime + 1][n];
        final int INFINITE = Integer.MAX_VALUE >> 1;
        for (int i = 0; i <= maxTime; i++) {
            Arrays.fill(dp[i], INFINITE);
        }
        dp[0][0] = passingFees[0];
        for (int t = 1; t <= maxTime; t++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int time = t - edge[2];
                if (time >= 0) {
                    dp[t][u] = Math.min(dp[t][u], dp[time][v] + passingFees[u]);
                    dp[t][v] = Math.min(dp[t][v], dp[time][u] + passingFees[v]);
                }
            }
        }
        int answer = INFINITE;
        for (int t = 1; t <= maxTime; t++) {
            answer = Math.min(answer, dp[t][n - 1]);
        }
        return answer == INFINITE ? -1 : answer;
    }

    public int minCost4(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        HashMap<Integer, HashMap<Integer, Integer>> e = new HashMap<>();
        for (int[] edge : edges) {
            HashMap<Integer, Integer> tmp = e.getOrDefault(edge[0], new HashMap<>());
            Integer oldValue = tmp.getOrDefault(edge[1], Integer.MAX_VALUE);
            tmp.put(edge[1], Math.min(edge[2], oldValue));
            e.put(edge[0], tmp);

            tmp = e.getOrDefault(edge[1], new HashMap<>());
            oldValue = tmp.getOrDefault(edge[0], Integer.MAX_VALUE);
            tmp.put(edge[0], Math.min(edge[2], oldValue));
            e.put(edge[1], tmp);
        }
        PriorityQueue<Integer[]> q = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] integers, Integer[] t1) {
                return integers[1] - t1[1];
            }
        });
        int[][] dis = new int[n][maxTime + 1];
        boolean[][] vis = new boolean[n][maxTime + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dis[i], Integer.MAX_VALUE);
        q.add(new Integer[]{0, passingFees[0], 0});//到达节点0，通行费为passingFees[0],时间为0
        dis[0][0] = passingFees[0];
        while (!q.isEmpty()) {
            Integer[] top = q.poll();
            if (top[0] == n - 1) return top[1];
            if (dis[top[0]][top[2]] < top[1]) continue;
            if (vis[top[0]][top[2]]) continue;
            vis[top[0]][top[2]] = true;
            HashMap<Integer, Integer> tmp = e.getOrDefault(top[0], new HashMap<>());
            if (tmp.size() == 0) continue;
            for (Integer key : tmp.keySet()) {
                int tim = tmp.get(key) + top[2];
                int cost = top[1] + passingFees[key];
                if (tim <= maxTime && dis[key][tim] > cost) {
                    q.add(new Integer[]{key, cost, tim});
                    dis[key][tim] = cost;
                }
            }
        }
        return -1;
    }

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        Map<Integer, Integer>[] graph = new Map[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int t = edge[2];
            graph[u].put(v, Math.min(t, graph[u].getOrDefault(v, Integer.MAX_VALUE)));
            graph[v].put(u, Math.min(t, graph[v].getOrDefault(u, Integer.MAX_VALUE)));
        }
        int[][] costs = new int[n][maxTime + 1];
        boolean[][] visited = new boolean[n][maxTime + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.offer(new int[]{0, 0, passingFees[0]});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int c = p[0];
            int t = p[1];
            int f = p[2];
            if (c == n - 1) {
                return f;
            }
            if (visited[c][t]) {
                continue;
            }
            visited[c][t] = true;
            for (Map.Entry<Integer, Integer> entry : graph[c].entrySet()) {
                int neighbor = entry.getKey();
                int time = t + entry.getValue();
                int fee = f + passingFees[neighbor];
                if (time <= maxTime) {
                    if (fee < costs[neighbor][time]) {
                        costs[neighbor][time] = fee;
                        queue.offer(new int[]{neighbor, time, fee});
                    }
                }
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Problem1928 a = new Problem1928();
//        int[][] edges = {{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15}};
//        int[] passingFees = {5, 1, 2, 20, 20, 3};
//        a.minCost(30, edges, passingFees);
//    }
}

