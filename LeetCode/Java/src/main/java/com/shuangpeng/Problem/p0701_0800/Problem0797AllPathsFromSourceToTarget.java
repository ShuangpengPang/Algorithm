package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.List;

public class Problem0797AllPathsFromSourceToTarget {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(graph, 0, new ArrayList<>(), answer);
        return answer;
    }

    private void dfs(int[][] graph, int u, List<Integer> list, List<List<Integer>> answer) {
        int n = graph.length;
        if (u == n - 1) {
            answer.add(new ArrayList<>(list));
            answer.get(answer.size() - 1).add(u);
            return;
        }
        list.add(u);
        for (int v : graph[u]) {
            dfs(graph, v, list, answer);
        }
        list.remove(list.size() - 1);
    }

    // 1, 2, 4, 8
}
