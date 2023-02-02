package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1129ShortestPathWithAlternatingColors（颜色交替的最短路径）
 * @date 2023/2/2 11:02 AM
 */
public class Problem1129ShortestPathWithAlternatingColors {

    public int[] shortestAlternatingPaths0(int n, int[][] redEdges, int[][] blueEdges) {
        int[][] dp = new int[n][2];
        List<Integer>[][] graph = new List[2][n];
        Arrays.setAll(graph, i -> new List[n]);
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
            graph[0][i] = new ArrayList<>();
            graph[1][i] = new ArrayList<>();
        }
        Arrays.fill(dp[0], 0);
        int[][][] edges = {redEdges, blueEdges};
        for (int i = 0; i < edges.length; i++) {
            for (int[] edge : edges[i]) {
                int x = edge[0], y = edge[1];
                graph[i][x].add(y);
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0});
        pq.offer(new int[]{0, 1, 0});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[0], c = p[1], d = p[2];
            if (d > dp[x][c]) {
                continue;
            }
            c ^= 1;
            d++;
            for (int y : graph[c][x]) {
                if (dp[y][c] > d) {
                    dp[y][c] = d;
                    pq.offer(new int[]{y, c, d});
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 1; i < n; i++) {
            ans[i] = dp[i][0] != INF || dp[i][1] != INF ? Math.min(dp[i][0], dp[i][1]) : -1;
        }
        return ans;
    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[][] graph = new List[2][n];
        Arrays.setAll(graph, i -> new List[n]);
        int[][] dp = new int[n][2];
        int INF = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
            graph[0][i] = new ArrayList<>();
            graph[1][i] = new ArrayList<>();
        }
        int[][][] edges = {redEdges, blueEdges};
        for (int i = 0; i < edges.length; i++) {
            for (int[] edge : edges[i]) {
                int x = edge[0], y = edge[1];
                graph[i][x].add(y);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        dp[0][0] = dp[0][1] = 0;
        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});
        int d = 0;
        while (!q.isEmpty()) {
            d++;
            for (int i = q.size() - 1; i >= 0; i--) {
                int[] p = q.poll();
                int x = p[0], c = p[1] ^ 1;
                for (int y : graph[c][x]) {
                    if (dp[y][c] > d) {
                        dp[y][c] = d;
                        q.offer(new int[]{y, c});
                    }
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = dp[i][0] != INF || dp[i][1] != INF ? Math.min(dp[i][0], dp[i][1]) : -1;
        }
        return ans;
    }
}
