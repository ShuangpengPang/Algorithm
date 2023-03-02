package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0785IsGraphBipartite（判断二分图）
 * @date 2023/3/2 11:19 AM
 */
public class Problem0785IsGraphBipartite {

    public boolean isBipartite(int[][] graph) {
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
        int nextColor = 3 - c;
        for (int y : graph[x]) {
            if (color[y] != 0) {
                if (color[y] != nextColor) {
                    return false;
                }
                continue;
            }
            if (!dfs(graph, color, y, 3 - c)) {
                return false;
            }
        }
        return true;
    }
}
