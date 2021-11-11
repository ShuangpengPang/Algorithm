package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Problem1443MinimumTimeToCollectAllApplesInATree {

    public int minTime0(int n, int[][] edges, List<Boolean> hasApple) {
        if (n <= 1) {
            return 0;
        }
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>(2));
        }
        int length = edges.length;
        for (int i = 0; i < length; i++) {
            int[] edge = edges[i];
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        int distance = dfs(graph, 0, hasApple, visited);
        return distance < 0 ? 0 : 2 * distance;
    }

    private int dfs(List<List<Integer>> graph, int node, List<Boolean> hasApple, boolean[] visited) {
        visited[node] = true;
        List<Integer> children = graph.get(node);
        boolean has = hasApple.get(node);
        if (children.isEmpty() && !has) {
            return -1;
        }
        if (children.isEmpty()) {
            return 0;
        }
        int distance = 0;
        for (int child : children) {
            if (!visited[child]) {
                distance += dfs(graph, child, hasApple, visited) + 1;
            }
        }
        return distance > 0 || has ? distance : -1;
    }

    public void buildReverseEdges(List<List<Integer>> nodeMap, int val) {
        for (int pairVal : nodeMap.get(val)) {
            if (pairVal != 0 && reverseEdges[pairVal] == -1) {
                reverseEdges[pairVal] = val;
                buildReverseEdges(nodeMap, pairVal);
            }
        }
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<List<Integer>> nodeMap = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodeMap.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            nodeMap.get(edge[0]).add(edge[1]);
            nodeMap.get(edge[1]).add(edge[0]);
        }
        reverseEdges = new int[n];
        Arrays.fill(reverseEdges, -1);
        //这一步建了一颗以0为根节点的树，确保能通过子结点找到父结点，父结点记录在reverseEdges数组中
        buildReverseEdges(nodeMap, 0);
        visited = new boolean[n];
        visited[0] = true;

        for (int i = 0; i < n; i++) {
            if (hasApple.get(i)) {
                dfsEdge(i);
            }
        }
        return ans * 2;
    }

    int ans = 0;
    int[] reverseEdges;
    boolean[] visited;

    public void dfsEdge(int to) {
        if (!visited[to]) {
            visited[to] = true;
            ans++;
            dfsEdge(reverseEdges[to]);
        }
    }
}
