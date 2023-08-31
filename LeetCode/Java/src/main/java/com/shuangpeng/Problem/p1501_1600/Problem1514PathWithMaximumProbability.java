package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1514PathWithMaximumProbability（概率最大的路径）
 * @date 2023/8/31 5:28 PM
 */
public class Problem1514PathWithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        double[] dis = new double[n];
        dis[start_node] = 1.0;
        List<double[]>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            int[] e = edges[i];
            graph[e[0]].add(new double[]{e[1], succProb[i]});
            graph[e[1]].add(new double[]{e[0], succProb[i]});
        }
        PriorityQueue<double[]> q = new PriorityQueue<>((a, b) -> b[1] > a[1] ? 1 : -1);
        q.offer(new double[]{start_node, 1});
        while (!q.isEmpty()) {
            double[] p = q.poll();
            int x = (int) p[0];
            if (p[1] < dis[x]) {
                continue;
            }
            if (x == end_node) {
                return p[1];
            }
            for (double[] arr : graph[x]) {
                int y = (int) arr[0];
                if (dis[y] < p[1] * arr[1]) {
                    dis[y] = p[1] * arr[1];
                    q.offer(new double[]{y, dis[y]});
                }
            }
        }
        return 0;
    }
}
