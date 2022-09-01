package com.shuangpeng.competition.第289场周赛;

import java.util.*;

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

class Problem2246LongestPathWithDifferentAdjacentCharacters1 {

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        int[] inDegree = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = parent[i];
            if (p != -1) {
                ++inDegree[p];
            }
        }
        int ans = 1;
        int[][] paths = new int[n][2];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int c = queue.poll();
            int p = parent[c];
            if (p == -1) {
                continue;
            }
            if (s.charAt(p) != s.charAt(c)) {
                int d = Math.max(paths[c][0], paths[c][1]) + 1;
                if (d >= paths[p][0]) {
                    paths[p][1] = paths[p][0];
                    paths[p][0] = d;
                } else if (d > paths[p][1]) {
                    paths[p][1] = d;
                }
            }
            if ((--inDegree[p]) == 0) {
                ans = Math.max(ans, paths[p][0] + paths[p][1] + 1);
                queue.offer(p);
            }
        }
        return ans;
    }
}

class Problem2246LongestPathWithDifferentAdjacentCharacters2 {

    int ans = 0;
    List<Integer>[] graph;
    String s;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            graph[parent[i]].add(i);
        }
        ans = 0;
        dfs(0);
        return ans + 1;
    }

    private int dfs(int u) {
        int maxLen = 0;
        for (int v : graph[u]) {
            int len = dfs(v) + 1;
            if (s.charAt(u) != s.charAt(v)) {
                ans = Math.max(ans, maxLen + len);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}

class Problem2246LongestPathWithDifferentAdjacentCharacters3 {
    private int[] par;
    private int[] firstChild;
    private int[] nxtChild;
    private int[] valChild;
    private int cntChild;
    private char[] str;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        firstChild = new int[n];
        nxtChild = new int[n];
        valChild = new int[n];
        cntChild = 1;
        for (int i = 1; i < n; i++) {
            valChild[cntChild] = i;
            nxtChild[cntChild] = firstChild[parent[i]];
            firstChild[parent[i]] = cntChild++;
        }
        par = parent;
        str = s.toCharArray();
        ans = 0;
        cal(0);
        return ans;
    }

    private int ans;

    private int cal(int index) {
        int max = 0;
        int secMax = 0;
        for (int curr = firstChild[index]; curr != 0; curr = nxtChild[curr]) {
            int temp = cal(curr);
            if (temp >= max) {
                secMax = max;
                max = temp;
            }
            else if (temp > secMax) {
                secMax = temp;
            }
        }
        ans = Math.max(ans, max + secMax + 1);
        return par[index] >= 0 && str[par[index]] == str[index] ? 0 : max + 1;
    }
}
