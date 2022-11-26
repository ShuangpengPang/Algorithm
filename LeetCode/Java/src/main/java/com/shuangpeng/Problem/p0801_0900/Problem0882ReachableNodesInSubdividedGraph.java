package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0882ReachableNodesInSubdividedGraph {

    public int reachableNodes0(int[][] edges, int maxMoves, int n) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], c = edge[2];
            graph[u].add(new int[]{v, c});
            graph[v].add(new int[]{u, c});
        }
        int[][] visited = new int[n][n];
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});
        int ans = 0;
        boolean[] seen = new boolean[n];
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0], m = node[1];
            if (seen[u]) {
                continue;
            }
            seen[u] = true;
            ++ans;
            if (dis[u] < maxMoves) {
                for (int[] pair : graph[u]) {
                    int v = pair[0];
                    int c = visited[v][u] == 0 ? pair[1] : pair[1] + 1 - visited[v][u];
                    int count = Math.min(maxMoves - dis[u], c);
                    ans += count;
                    visited[u][v] = count + 1;
                    int d = m + c + 1;
                    if (d < dis[v]) {
                        dis[v] = d;
                        if (d <= maxMoves) {
                            pq.offer(new int[]{v, d});
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int reachableNodes(int[][] edges, int M, int N) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap();
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.computeIfAbsent(u, x->new HashMap()).put(v, w);
            graph.computeIfAbsent(v, x->new HashMap()).put(u, w);
        }

        PriorityQueue<ANode> pq = new PriorityQueue<ANode>(
                (a, b) -> Integer.compare(a.dist, b.dist));
        pq.offer(new ANode(0, 0));

        Map<Integer, Integer> dist = new HashMap();
        dist.put(0, 0);
        Map<Integer, Integer> used = new HashMap();
        int ans = 0;

        while (!pq.isEmpty()) {
            ANode anode = pq.poll();
            int node = anode.node;
            int d = anode.dist;

            if (d > dist.getOrDefault(node, 0)) continue;
            // Each node is only visited once.  We've reached
            // a node in our original graph.
            ans++;

            if (!graph.containsKey(node)) continue;
            for (int nei: graph.get(node).keySet()) {
                // M - d is how much further we can walk from this node;
                // weight is how many new nodes there are on this edge.
                // v is the maximum utilization of this edge.
                int weight = graph.get(node).get(nei);
                int v = Math.min(weight, M - d);
                used.put(N * node + nei, v);

                // d2 is the total distance to reach 'nei' (nei***or) node
                // in the original graph.
                int d2 = d + weight + 1;
                if (d2 < dist.getOrDefault(nei, M+1)) {
                    pq.offer(new ANode(nei, d2));
                    dist.put(nei, d2);
                }
            }
        }

        // At the end, each edge (u, v, w) can be used with a maximum
        // of w new nodes: a max of used[u, v] nodes from one side,
        // and used[v, u] nodes from the other.
        // [We use the encoding (u, v) = u * N + v.]
        for (int[] edge: edges) {
            ans += Math.min(edge[2], used.getOrDefault(edge[0] * N + edge[1], 0) +
                    used.getOrDefault(edge[1] * N + edge[0], 0) );
        }

        return ans;
    }

    class ANode {
        int node, dist;
        ANode(int n, int d) {
            node = n;
            dist = d;
        }
    }
}










class Problem0882ReachableNodesInSubdividedGraph0 {

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        Map<Integer, Integer>[] visited = new Map[n];
        Arrays.setAll(visited, i -> new HashMap<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], c = edge[2];
            g[x].add(new int[]{y, c});
            g[y].add(new int[]{x, c});
            visited[x].put(y, 0);
            visited[y].put(x, 0);
        }
        int[] reach = new int[n];
        Arrays.fill(reach, -1);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        q.offer(new int[]{0, maxMoves});
        int ans = 0;
        while (!q.isEmpty()) {
            while (!q.isEmpty() && q.peek()[1] < reach[q.peek()[0]]) {
                q.poll();
            }
            if (!q.isEmpty()) {
                int[] arr = q.poll();
                int x = arr[0], m = arr[1];
                ans += 1;
                for (int[] p : g[x]) {
                    int y = p[0], c = p[1], cnt = Math.min(m, c - visited[y].get(x));
                    ans += cnt;
                    visited[x].put(y, cnt);
                    int next = m - c - 1;
                    if (next > reach[y]) {
                        reach[y] = next;
                        q.offer(new int[]{y, next});
                    }
                }
            }
        }
        return ans;
    }

    private int dfs(List<int[]>[] g, Map<Integer, Integer>[] visited, int[] reach, int x, int moves) {
        if (moves < 0 || reach[x] >= moves + 1) {
            return 0;
        }
        int ans = reach[x] == 0 ? 1 : 0;
        reach[x] = moves + 1;
        for (int[] arr : g[x]) {
            int y = arr[0], c = arr[1], cnt1 = visited[x].get(y), cnt2 = visited[y].get(x);
            int cnt = Math.min(Math.max(cnt1, moves), c - cnt2);
            visited[x].put(y, cnt);
            ans += cnt - cnt1 + dfs(g, visited, reach, y, moves - c - 1);
        }
        return ans;
    }
}