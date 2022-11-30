package com.shuangpeng.Problem.P2201_2300;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2203MinimumWeightedSubgraphWithTheRequiredPaths（得到要求路径的最小带权子图）
 * @date 2022/11/30 4:51 PM
 */
public class Problem2203MinimumWeightedSubgraphWithTheRequiredPaths {

    private static final long INF = Long.MAX_VALUE / 3;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<int[]>[] g1 = new List[n], g2 = new List[n];
        Arrays.setAll(g1, i -> new ArrayList<>());
        Arrays.setAll(g2, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], w = edge[2];
            g1[x].add(new int[]{y, w});
            g2[y].add(new int[]{x, w});
        }
        long[] dis = getDistance(g2, dest);
        if (dis[src1] == INF || dis[src2] == INF) {
            return -1;
        }
        long[] dis1 = getDistance(g1, src1), dis2 = getDistance(g1, src2);
        long ans = INF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dis[i] + dis1[i] + dis2[i]);
        }
        return ans;
    }

    private long[] getDistance(List<int[]>[] g, int src) {
        int n = g.length;
        long[] dis = new long[n];
        Arrays.fill(dis, INF);
        PriorityQueue<Pair<Integer, Long>> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.getValue()));
        dis[src] = 0L;
        pq.offer(new Pair<>(src, 0L));
        while (!pq.isEmpty()) {
            Pair<Integer, Long> p = pq.poll();
            int x = p.getKey();
            if (dis[x] < p.getValue()) {
                continue;
            }
            for (int[] arr : g[x]) {
                int y = arr[0];
                long w = arr[1] + p.getValue();
                if (dis[y] > w) {
                    dis[y] = w;
                    pq.offer(new Pair<>(y, w));
                }
            }
        }
        return dis;
    }
}
