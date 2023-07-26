package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1334FindTheCityWithSmallestNumberOfNeighborsAtAThresholdDistance（阈值距离内邻居最少的城市）
 * @date 2023/7/26 5:00 PM
 */
public class Problem1334FindTheCityWithSmallestNumberOfNeighborsAtAThresholdDistance {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(new int[]{e[1], e[2]});
            g[e[1]].add(new int[]{e[0], e[2]});
        }
        List<Integer>[] neighbors = new List[n];
        Arrays.setAll(neighbors, i -> new ArrayList<>());
        int[] dis = new int[n];
        PriorityQueue<int[]> q = new PriorityQueue<>(n, Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis, Integer.MAX_VALUE >> 1);
            q.clear();
            dis[i] = 0;
            q.offer(new int[]{i, 0});
            while (!q.isEmpty()) {
                int[] p = q.poll();
                int x = p[0], d = p[1];
                if (d <= dis[x]) {
                    neighbors[i].add(x);
                    for (int[] arr : g[x]) {
                        int y = arr[0], w = arr[1] + d;
                        if (w <= distanceThreshold && dis[y] > w) {
                            dis[y] = w;
                            q.offer(new int[]{y, w});
                        }
                    }
                }
            }
        }
        int cnt = Integer.MAX_VALUE, ans = 0;
        for (int i = 0; i < n; i++) {
            int size = neighbors[i].size();
            if (size <= cnt) {
                cnt = size;
                ans = i;
            }
        }
        return ans;
    }
}
