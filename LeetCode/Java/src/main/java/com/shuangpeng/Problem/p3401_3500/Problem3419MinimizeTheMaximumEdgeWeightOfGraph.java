package com.shuangpeng.Problem.p3401_3500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3419MinimizeTheMaximumEdgeWeightOfGraph（图的最大边权的最小值）
 * @date 2025/4/16 14:47
 */
public class Problem3419MinimizeTheMaximumEdgeWeightOfGraph {

    public int minMaxWeight(int n, int[][] edges, int threshold) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int left = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        for (int[] edge : edges) {
            g[edge[1]].add(new int[]{edge[0], edge[2]});
            left = Math.min(left, edge[2]);
            mx = Math.max(mx, edge[2]);
        }
        boolean[] visited = new boolean[n];
        int right = mx;
        while (left <= right) {
            Arrays.fill(visited, false);
            int mid = left + (right - left >> 1);
            if (dfs(g, 0, mid, visited) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left <= mx ? left : -1;
    }

    private int dfs(List<int[]>[] g, int x, int mx, boolean[] visited) {
        int cnt = 1;
        visited[x] = true;
        for (int[] p : g[x]) {
            if (p[1] <= mx && !visited[p[0]]) {
                cnt += dfs(g, p[0], mx, visited);
            }
        }
        return cnt;
    }
}
