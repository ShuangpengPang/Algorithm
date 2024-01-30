package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2608ShortestCycleInAGraph（图中的最短环）
 * @date 2024/1/30 10:29 PM
 */
public class Problem2608ShortestCycleInAGraph {

    private int[] visited;

    public int findShortestCycle(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        int N = n + 1, step = N;
        visited = new int[n];
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            if (union(parent, x, y)) {
                step = Math.min(step, getStep(graph, x, y, step + 1 >> 1));
            }
        }
        return step == N ? -1 : step + 1;
    }

    private int getStep(List<Integer>[] graph, int x, int y, int step) {
        int k = x << 10 | y;
        Queue<Integer> q1 = new ArrayDeque<>(), q2 = new ArrayDeque<>();
        q1.offer(x);
        q2.offer(y);
        visited[x] = k << 10 | 1;
        visited[y] = k << 10 | 2;
        for (int s = 0; s < step; s++) {
            if (check(graph, q1, y, k, 1)) {
                return (s << 1) + 1;
            }
            if (check(graph, q2, x, k, 2)) {
                return s + 1 << 1;
            }
        }
        return step << 1;
    }

    private boolean check(List<Integer>[] graph, Queue<Integer> q, int x, int k, int m) {
        for (int i = q.size() - 1; i >= 0; i--) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (v == x) {
                    continue;
                }
                if (visited[v] >> 10 != k) {
                    visited[v] = k << 10 | m;
                    q.offer(v);
                } else if ((visited[v] & 3) == (3 ^ m)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }

    private boolean union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return true;
        }
        parent[py] = px;
        return false;
    }
}
