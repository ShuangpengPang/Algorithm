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
