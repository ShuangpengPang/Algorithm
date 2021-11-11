package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem1466ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    public int minReorder(int n, int[][] connections) {
        if (n < 2) {
            return 0;
        }
        Map<Integer, List<int[]>> graph = new HashMap<>(n);
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new int[]{v, 1});
            graph.get(v).add(new int[]{u, 0});
        }
        return dfs(0, -1, graph);
    }

    private int dfs(int u, int f, Map<Integer, List<int[]>> graph) {
        List<int[]> neighbors = graph.get(u);
        int count = 0;
        for (int[] neighbor : neighbors) {
            int v = neighbor[0];
            if (v != f) {
                count += neighbor[1];
                count += dfs(v, u, graph);
            }
        }
        return count;
    }
}
