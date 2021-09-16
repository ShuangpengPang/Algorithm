package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem0913CatAndMouse {

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        final int DRAW = 0, MOUSE = 1, CAT = 2;
        int[][][] color = new int[n][n][3];
        int[][][] degree = new int[n][n][3];
        for (int m = 0; m < n; ++m) {
            for (int c = 0; c < n; ++c) {
                degree[m][c][MOUSE] = graph[m].length;
                for (int i : graph[c]) {
                    if (i != 0) {
                        degree[m][c][CAT]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int c = 0; c < n; ++c) {
            for (int t = 1; t <= 2; ++t) {
                color[0][c][t] = MOUSE;
                queue.offer(new int[]{0, c, t, MOUSE});
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int t = 1; t <= 2; ++t) {
                color[i][i][t] = CAT;
                queue.offer(new int[]{i, i, t, CAT});
            }
        }
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int d = node[3];
            List<int[]> nodes = parents(node, graph);
            for (int[] parent : nodes) {
                int m1 = parent[0], c1 = parent[1], t1 = parent[2];
                if (color[m1][c1][t1] == DRAW) {
                    if (t1 == d) {
                        color[m1][c1][t1] = d;
                        queue.offer(new int[]{m1, c1, t1, d});
                    } else {
                        degree[m1][c1][t1]--;
                        if (degree[m1][c1][t1] == 0) {
                            color[m1][c1][t1] = d;
                            queue.offer(new int[]{m1, c1, t1, d});
                        }
                    }
                }
            }
        }
        return color[1][2][1];
    }

    private List<int[]> parents(int[] node, int[][] graph) {
        int m = node[0], c = node[1], t = node[2];
        List<int[]> ans = new ArrayList<>();
        if (t == 1) {
            for (int i : graph[c]) {
                if (i != 0) {
                    ans.add(new int[]{m, i, 3 - t});
                }
            }
        } else {
            for (int i : graph[m]) {
                ans.add(new int[]{i, c, 3 - t});
            }
        }
        return ans;
    }
}
