package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0802FindEventualSafeStates {

    public List<Integer> eventualSafeNodes0(int[][] graph) {
        int n = graph.length;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer>[] inNeighbors = new List[n];
        int[] outdegree = new int[n];
        for (int i = 0; i < n; ++i) {
            inNeighbors[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            int size = graph[i].length;
            if (size == 0) {
                queue.offer(i);
            }
            outdegree[i] = size;
            for (int v : graph[i]) {
                inNeighbors[v].add(i);
            }
        }
        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int v = queue.poll();
            answer.add(v);
            for (int u : inNeighbors[v]) {
                outdegree[u]--;
                if (outdegree[u] == 0) {
                    queue.offer(u);
                }
            }
        }
        Collections.sort(answer);
        return answer;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] status = new int[n];
        for (int i = 0; i < n; ++i) {
            if (status[i] == 0) {
                dfs(status, i, graph);
            }
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (status[i] == 2) {
                answer.add(i);
            }
        }
        return answer;
    }

    private boolean dfs(int[] status, int u, int[][] graph) {
        if (status[u] == 1) {
            return true;
        }
        if (status[u] == 2) {
            return false;
        }
        status[u] = 1;
        for (int v : graph[u]) {
            if (dfs(status, v, graph)) {
                return true;
            }
        }
        status[u] = 2;
        return false;
    }
}
