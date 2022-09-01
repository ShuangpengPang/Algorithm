package com.shuangpeng.competition.第261到270场周赛.第270场周赛;

import java.util.*;

public class Problem2097ValidArrangementOfPairs {

    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : pairs) {
            int u = pair[0], v = pair[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(v);
            outDegree.put(u, outDegree.getOrDefault(u, 0) + 1);
            inDegree.put(v, inDegree.getOrDefault(v, 0) + 1);
        }
        int start = pairs[0][0];
        for (int u : outDegree.keySet()) {
            if (outDegree.get(u) == inDegree.getOrDefault(u, 0) + 1) {
                start = u;
                break;
            }
        }
        List<int[]> list = new ArrayList<>();
        hierholzer(graph, start, list);
        int n = pairs.length;
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; ++i) {
            ans[i] = list.get(n - i - 1);
        }
        return ans;
    }

    private void hierholzer(Map<Integer, List<Integer>> graph, int u, List<int[]> list) {
        List<Integer> neighbors = graph.get(u);
        if (neighbors == null) {
            return;
        }
        while (!neighbors.isEmpty()) {
            int v = neighbors.remove(neighbors.size() - 1);
            hierholzer(graph, v, list);
            list.add(new int[]{u, v});
        }
    }
}
