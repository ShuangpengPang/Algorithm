package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2642DesignGraphWithShortestPathCalculator（设计可以求最短路径的图类）
 * @date 2024/2/2 10:55 AM
 */
public class Problem2642DesignGraphWithShortestPathCalculator {
}

class Graph {

    int N = Integer.MAX_VALUE;
    List<int[]>[] graph;
    int[][] dis;

    public Graph(int n, int[][] edges) {
        dis = new int[n][n];
        graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }
    }

    public void addEdge(int[] edge) {
        graph[edge[0]].add(new int[]{edge[1], edge[2]});
    }

    public int shortestPath(int node1, int node2) {
        Arrays.fill(dis[node1], N);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        dis[node1][node1] = 0;
        pq.offer(new int[]{node1, 0});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int x = p[0], c = p[1];
            if (c > dis[node1][x]) {
                continue;
            }
            if (x == node2) {
                return c;
            }
            for (int[] a : graph[x]) {
                int y = a[0], w = a[1];
                if (dis[node1][y] > c + w) {
                    dis[node1][y] = c + w;
                    pq.offer(new int[]{y, c + w});
                }
            }
        }
        return -1;
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */
