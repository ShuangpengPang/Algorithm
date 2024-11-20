package com.shuangpeng.Problem.p3201_3300;

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
 * @description: Problem3243ShortestDistanceAfterRoadAdditionQueriesI（新增道路查询后的最短距离I）
 * @date 2024/11/18 7:38 PM
 */
public class Problem3243ShortestDistanceAfterRoadAdditionQueriesI {

    public int[] shortestDistanceAfterQueries0(int n, int[][] queries) {
        List<Integer>[] g = new List[n];
        g[0] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            g[i] = new ArrayList<>();
            g[i - 1].add(i);
        }
        int[] dis = new int[n];
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int s = queries[i][0], e = queries[i][1];
            g[s].add(e);
            Arrays.fill(dis, n);
            dis[0] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
            pq.offer(new int[]{0, 0});
            while (!pq.isEmpty() && pq.peek()[0] != n - 1) {
                int[] p = pq.poll();
                int x = p[0], d = p[1];
                if (d == dis[x]) {
                    for (int y : g[x]) {
                        if (dis[y] >  d + 1) {
                            dis[y] = d + 1;
                            pq.offer(new int[]{y, dis[y]});
                        }
                    }
                }
            }
            ans[i] = dis[n - 1];
        }
        return ans;
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[i - 1].add(i);
        }
        int m = queries.length;
        int[] ans = new int[m];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < m; i++) {
            g[queries[i][0]].add(queries[i][1]);
            Arrays.fill(visited, false);
            ans[i] = bfs(g, visited);
        }
        return ans;
    }

    private int bfs(List<Integer>[] g, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        int dis = 0, n = g.length;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int x = q.poll();
                if (x == n - 1) {
                    return dis;
                }
                for (int y : g[x]) {
                    if (!visited[y]) {
                        visited[y] = true;
                        q.offer(y);
                    }
                }
            }
            dis++;
        }
        return dis;
    }
}
