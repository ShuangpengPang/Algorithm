package com.shuangpeng.Problem.p0701_0800;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0785IsGraphBipartite（判断二分图）
 * @date 2023/3/2 11:19 AM
 */
public class Problem0785IsGraphBipartite {

    public boolean isBipartite0(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !dfs(graph, color, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] color, int x, int c) {
        color[x] = c;
        for (int y : graph[x]) {
            if (color[y] == c || color[y] == 0 && !dfs(graph, color, y, 3 - c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite1(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (color[i] != 0) {
                continue;
            }
            color[i] = 1;
            q.offer(i);
            while (!q.isEmpty()) {
                int x = q.poll(), c = 3 - color[x];
                for (int y : graph[x]) {
                    if (color[y] == 0) {
                        color[y] = c;
                        q.offer(y);
                    } else if (color[y] != c) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        for (int i = 0; i < n; i++) {
            for (int x : graph[i]) {
                if (find(parent, i) == find(parent, x)) {
                    return false;
                }
                union(parent, graph[i][0], x);
            }
        }
        return true;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}
