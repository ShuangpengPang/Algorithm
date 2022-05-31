package com.shuangpeng.competition.第289场周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem2246LongestPathWithDifferentAdjacentCharacters（相邻字符不同的最长路径）
 * @Date 2022/5/29 11:46 PM
 * @Version 1.0
 */
public class Problem2246LongestPathWithDifferentAdjacentCharacters {

    int[] depthMemo;
    int[] dfsMemo;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            int p = parent[i];
            if (p >= 0) {
                graph[parent[i]].add(i);
            }
        }
        depthMemo = new int[n];
        dfsMemo = new int[n];
        return longestPath(graph, 0, s);
    }

    private int longestPath(List<Integer>[] graph, int root, String s) {
        int ans = 0;
        for (int child : graph[root]) {
            ans = Math.max(ans, longestPath(graph, child, s));
        }
        return Math.max(ans, dfs(graph, root, s));
    }

    private int dfs(List<Integer>[] graph, int root, String s) {
        if (dfsMemo[root] != 0) {
            return dfsMemo[root];
        }
        char c = s.charAt(root);
        int first = 0, second = 0;
        for (int child : graph[root]) {
            if (c != s.charAt(child)) {
                int d = depth(graph, child, s);
                if (d >= first) {
                    second = first;
                    first = d;
                } else if (d > second) {
                    second = d;
                }
            }
        }
        dfsMemo[root] = first + second + 1;
        return dfsMemo[root];
    }

    private int depth(List<Integer>[] graph, int root, String s) {
        if (depthMemo[root] != 0) {
            return depthMemo[root];
        }
        int ans = 0;
        char c = s.charAt(root);
        for (int child : graph[root]) {
            if (c != s.charAt(child)) {
                ans = Math.max(ans, depth(graph, child, s));
            }
        }
        depthMemo[root] = ans + 1;
        return depthMemo[root];
    }
}

class Problem2246LongestPathWithDifferentAdjacentCharacters0 {

    int[] paths;
    int[] deeps;
    int ans = 0;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; ++i) {
            int p = parent[i];
            if (p != -1) {
                graph[p].add(i);
            }
        }
        paths = new int[n];
        deeps = new int[n];
        ans = 0;
        for (int i = 0; i < n; ++i) {
            dfs(graph, i, s);
        }
        return ans;
    }

    private void dfs(List<Integer>[] graph, int u, String s) {
        if (paths[u] > 0) {
            return;
        }
        char c = s.charAt(u);
        int first = 0, second = 0;
        for (int v : graph[u]) {
            if (s.charAt(v) != c) {
                int d = depth(graph, v, s);
                if (d >= first) {
                    second = first;
                    first = d;
                } else if (d > second) {
                    second = d;
                }
            }
        }
        paths[u] = first + second + 1;
        ans = Math.max(ans, paths[u]);
    }

    private int depth(List<Integer>[] graph, int u, String s) {
        if (deeps[u] > 0) {
            return deeps[u];
        }
        int c = s.charAt(u);
        int ans = 0;
        for (int v : graph[u]) {
            if (s.charAt(v) != c) {
                ans = Math.max(ans, depth(graph, v, s));
            }
        }
        deeps[u] = ans + 1;
        return deeps[u];
    }
}
