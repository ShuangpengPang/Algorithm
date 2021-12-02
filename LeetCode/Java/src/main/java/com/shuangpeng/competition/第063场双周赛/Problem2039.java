package com.shuangpeng.competition.第063场双周赛;

import java.util.*;

public class Problem2039 {

    // 比赛时代码
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            List<Integer> listU = graph.getOrDefault(u, new ArrayList<>());
            listU.add(v);
            graph.put(u, listU);
            List<Integer> listV = graph.getOrDefault(v, new ArrayList<>());
            listV.add(u);
            graph.put(v, listV);
        }
        int[] paths = new int[n];
        Arrays.fill(paths, -1);
        paths[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int p = 0;
        while (!queue.isEmpty()) {
            ++p;
            for (int i = queue.size() - 1; i >= 0; --i) {
                int u = queue.poll();
                for (int v : graph.get(u)) {
                    if (paths[v] == -1) {
                        paths[v] = p << 1;
                        queue.offer(v);
                    }
                }
            }
        }
        int maxTime = 0;
        for (int i = 1; i < n; ++i) {
            int count = (paths[i] - 1) / patience[i];
            maxTime = Math.max(maxTime, count * patience[i] + paths[i] + 1);
        }
        return maxTime;
    }
}
