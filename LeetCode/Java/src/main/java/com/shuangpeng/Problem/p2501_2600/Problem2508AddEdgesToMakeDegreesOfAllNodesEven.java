package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2508AddEdgesToMakeDegreesOfAllNodesEven（添加边使所有节点度数都为偶数）
 * @date 2024/7/7 9:48 PM
 */
public class Problem2508AddEdgesToMakeDegreesOfAllNodesEven {

    public boolean isPossible(int n, List<List<Integer>> edges) {
        Set<Integer>[] g = new Set[n];
        Arrays.setAll(g, i -> new HashSet<>());
        for (List<Integer> edge : edges) {
            int x = edge.get(0) - 1, y = edge.get(1) - 1;
            g[x].add(y);
            g[y].add(x);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((g[i].size() & 1) == 1) {
                list.add(i);
            }
        }
        if (list.size() > 4 || (list.size() & 1) == 1) {
            return false;
        }
        if (check(g, list, new boolean[list.size()], 0)) {
            return true;
        }
        if (list.size() == 2) {
            boolean[] visited = new boolean[n];
            int cnt = 0;
            for (int x : list) {
                for (int y : g[x]) {
                    if (!visited[y]) {
                        visited[y] = true;
                        cnt++;
                    }
                }
            }
            return cnt < n;
        }
        return list.isEmpty();
    }

    private boolean check(Set<Integer>[] g, List<Integer> list, boolean[] visited, int pos) {
        int n = list.size();
        if (pos == n) {
            return true;
        }
        if (visited[pos]) {
            return check(g, list, visited, pos + 1);
        }
        int x = list.get(pos);
        Set<Integer> set = g[x];
        for (int i = pos + 1; i < n; i++) {
            int y = list.get(i);
            if (!set.contains(y) && !g[y].contains(x)) {
                visited[i] = true;
                if (check(g, list, visited, pos + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
