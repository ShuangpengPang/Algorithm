package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2538DifferenceBetweenMaximumAndMinimumPriceSum（最大价值和与最小价值和的差值）
 * @date 2024/1/19 9:45 AM
 */
public class Problem2538DifferenceBetweenMaximumAndMinimumPriceSum {

    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        long[][] maxPath = new long[n][];
        dfs(graph, price, 0, -1, maxPath);
        long ans = 0;
        Queue<long[]> q = new ArrayDeque<>();
        q.offer(new long[]{0, -1, maxPath[0][1], 0});
        while (!q.isEmpty()) {
            long[] a = q.poll();
            int x = (int) a[0], p = (int) a[1];
            ans = Math.max(ans, a[2]);
            for (int y : graph[x]) {
                if (y != p) {
                    long sum = Math.max(a[3], y == maxPath[x][0] ? maxPath[x][2] : maxPath[x][1]) + price[x];
                    q.offer(new long[]{y, x, Math.max(maxPath[y][1], sum), sum});;
                }
            }
        }
        return ans;
    }

    private void dfs(List<Integer>[] graph, int[] price, int x, int p, long[][] maxPath) {
        long node = -1, first = 0, second = 0;
        for (int y : graph[x]) {
            if (y != p) {
                dfs(graph, price, y, x, maxPath);
                long sum = maxPath[y][1] + price[y];
                if (sum >= first) {
                    node = y;
                    second = first;
                    first = sum;
                } else if (sum > second) {
                    second = sum;
                }
            }
        }
        maxPath[x] = new long[]{node, first, second};
    }
}


class Problem2538DifferenceBetweenMaximumAndMinimumPriceSum0 {

    private long ans = 0;

    public long maxOutput(int n, int[][] edges, int[] price) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        ans = 0;
        dfs(graph, price, 0, -1);
        return ans;
    }

    private long[] dfs(List<Integer>[] graph, int[] price, int x, int p) {
        long max1 = price[x], max2 = 0;
        for (int y : graph[x]) {
            if (y != p) {
                long[] a = dfs(graph, price, y, x);
                long s1 = a[0], s2 = a[1];
                ans = Math.max(ans, Math.max(max1 + s2, max2 + s1));
                max1 = Math.max(max1, s1 + price[x]);
                max2 = Math.max(max2, s2 + price[x]);
            }
        }
        return new long[]{max1, max2};
    }
}
