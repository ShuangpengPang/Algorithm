package com.shuangpeng.Problem.p3401_3500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3419MinimizeTheMaximumEdgeWeightOfGraph（图的最大边权的最小值）
 * @date 2025/4/16 14:47
 */
public class Problem3419MinimizeTheMaximumEdgeWeightOfGraph {

    public int minMaxWeight0(int n, int[][] edges, int threshold) {
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

    public int minMaxWeight(int n, int[][] edges, int threshold) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            g[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        int mx = Integer.MIN_VALUE, cnt = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{Integer.MIN_VALUE, 0});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[1];
            if (visited[x]) {
                continue;
            }
            visited[x] = true;
            mx = Math.max(mx, p[0]);
            cnt++;
            for (int[] e : g[x]) {
                int y = e[0], w = e[1];
                if (!visited[y]) {
                    pq.offer(new int[]{w, y});
                }
            }
        }
        return cnt == n ? mx : -1;
    }
}
