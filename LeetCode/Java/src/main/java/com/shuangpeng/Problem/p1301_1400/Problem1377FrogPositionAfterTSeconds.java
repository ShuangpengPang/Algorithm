package com.shuangpeng.Problem.p1301_1400;

import java.util.*;

/**
 * @Description: Problem1377FrogPositionAfterTSeconds（T秒后青蛙的位置）
 * @Date 2022/8/9 6:37 PM
 * @Version 1.0
 */
public class Problem1377FrogPositionAfterTSeconds {

    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        double[] values = new double[n];
        values[0] = 1d;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, -1});
        boolean stop = false;
        for (int i = 0; i < t && !stop; i++) {
            double[] tmp = new double[n];
            stop = true;
            for (int j = q.size() - 1; j >= 0; j--) {
                int[] arr = q.poll();
                int x = arr[0], p = arr[1];
                boolean find = false;
                for (int y : graph[x]) {
                    if (y == p) {
                        continue;
                    }
                    find = true;
                    stop = false;
                    int cnt = x == 0 ? graph[x].size() : graph[x].size() - 1;
                    tmp[y] = values[x] / cnt;
                    q.offer(new int[]{y, x});
                }
                if (!find) {
                    tmp[x] = values[x];
                    q.offer(new int[]{x, p});
                }
            }
            values = tmp;
        }
        return values[target - 1];
    }
}

class Problem1377FrogPositionAfterTSeconds0 {

    int target;
    List<Integer>[] g;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        g = new List[n];
        this.target = target - 1;
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        return dfs(0, t, -1);
    }

    private double dfs(int x, int t, int p) {
        int m = g[x].size();
        m = p == -1 ? m : m - 1;
        if (t == 0 || m == 0) {
            return x == target ? 1.0 : 0.0;
        }
        double ans = 0.0;
        for (int y : g[x]) {
            if (y != p) {
                ans += dfs(y, t - 1, x);
            }
        }
        return ans / m;
    }
}

class Problem1377FrogPositionAfterTSeconds1 {

    int target;
    List<Integer>[] g;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        g = new List[n];
        this.target = target - 1;
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        g[0].add(-1);
        long ans = dfs(0, t, -1);
        return ans == 0 ? 0 : 1.0 / ans;
    }

    private long dfs(int x, int t, int p) {
        int m = g[x].size() - 1;
        if (t == 0 || m == 0) {
            return x == target ? 1 : 0;
        }
        for (int y : g[x]) {
            if (y != p) {
                long value = dfs(y, t - 1, x);
                if (value != 0) {
                    return value * m;
                }
            }
        }
        return 0;
    }
}
