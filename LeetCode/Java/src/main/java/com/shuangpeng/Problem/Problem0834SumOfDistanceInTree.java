package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0834SumOfDistanceInTree {

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        if (N <= 1) {
            return new int[]{0};
        }
        List<List<Integer>> adjacents = new ArrayList<>();
        List<List<int[]>> distances = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacents.add(new ArrayList<>());
            distances.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adjacents.get(edges[i][0]).add(edges[i][1]);
            adjacents.get(edges[i][1]).add(edges[i][0]);
        }
        boolean[] visited = new boolean[N];
        dfs(0, adjacents, distances, visited);
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            List<int[]> list = distances.get(i);
            int sum = 0;
            for (int[] a : list) {
                sum += a[1];
            }
            answer[i] = sum;
        }
        return answer;
    }

    private void dfs(int u, List<List<Integer>> adjacents, List<List<int[]>> distances, boolean[] visited) {
        visited[u] = true;
        List<int[]> list = distances.get(u);
        list.add(new int[]{u, 0});
        List<Integer> neighbors = adjacents.get(u);
        for (int v : neighbors) {
            if (!visited[v]) {
                dfs(v, adjacents, distances, visited);
                List<int[]> vList = distances.get(v);
                int size = list.size();
                int vSize = vList.size();
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < vSize; j++) {
                        int[] distance = list.get(i);
                        int[] vDistance = vList.get(j);
                        distances.get(distance[0]).add(new int[]{vDistance[0], distance[1] + vDistance[1] + 1});
                        distances.get(vDistance[0]).add(new int[]{distance[0], distance[1] + vDistance[1] + 1});
                    }
                }
            }
        }
    }
}
