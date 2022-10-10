package com.shuangpeng.Problem.p1701_1800;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem1761MinimumDegreeOfAConnectedTrioInAGraph（一个图中连通三元组的最小度数）
 * @Date 2022/10/9 6:05 PM
 * @Version 1.0
 */
public class Problem1761MinimumDegreeOfAConnectedTrioInAGraph {

    public int minTrioDegree(int n, int[][] edges) {
        Set<Integer>[] graph = new Set[n];
        Arrays.setAll(graph, e -> new HashSet<>());
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        int ans = Integer.MAX_VALUE;
        for (int x = 0; x < n; x++) {
            if (graph[x].size() > 1) {
                for (int y : graph[x]) {
                    if (y > x) {
                        for (int k : graph[x]) {
                            if (k > y && graph[k].contains(y)) {
                                ans = Math.min(ans, graph[x].size() + graph[y].size() + graph[k].size() - 6);
                            }
                        }
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
