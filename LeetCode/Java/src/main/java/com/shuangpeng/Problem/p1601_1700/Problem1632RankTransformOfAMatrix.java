package com.shuangpeng.Problem.p1601_1700;

import java.util.*;

/**
 * @Description: Problem1632RankTransformOfAMatrix（矩阵转换后的秩）
 * @Date 2022/9/7 3:00 PM
 * @Version 1.0
 */
public class Problem1632RankTransformOfAMatrix {

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, N = m * n;
        int[] parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(j);
            }
            for (List<Integer> list : map.values()) {
                for (int j = 0; j < list.size() - 1; j++) {
                    int x = list.get(j), y = list.get(j + 1);
                    union(parent, i * n + x, i * m + y);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                map.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(i);
            }
            for (List<Integer> list : map.values()) {
                for (int i = 0; i < list.size() - 1; i++) {
                    int x = list.get(i), y = list.get(i + 1);
                    union(parent, x * n + j, y * n + j);
                }
            }
        }
        List<Integer>[] graph = new List[N];
        Arrays.setAll(graph, e -> new ArrayList<>());
        int[] inDegree = new int[N];
        for (int i = 0; i < m; i++) {
            int[][] arr = new int[n][3];
            for (int j = 0; j < n; j++) {
                arr[j][0] = matrix[i][j];
                arr[j][1] = i;
                arr[j][2] = j;
            }
            Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
            for (int j = 0; j < n - 1; j++) {
                if (arr[j][0] != arr[j + 1][0]) {
                    int x = arr[j][1] * n + arr[j][2], y = arr[j + 1][1] * n + arr[j + 1][2];
                    int px = find(parent, x), py = find(parent, y);
                    graph[px].add(py);
                    inDegree[py]++;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int[][] arr = new int[m][3];
            for (int i = 0; i < m; i++) {
                arr[i][0] = matrix[i][j];
                arr[i][1] = i;
                arr[i][2] = j;
            }
            Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
            for (int i = 0; i < m - 1; i++) {
                if (arr[i][0] != arr[i + 1][0]) {
                    int x = arr[i][1] * n + arr[i][2], y = arr[i + 1][1] * n + arr[i + 1][2];
                    int px = find(parent, x), py = find(parent, y);
                    graph[px].add(py);
                    inDegree[py]++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        int[] rank = new int[N];
        for (int i = 0; i < N; i++) {
            if (parent[i] == i && inDegree[i] == 0) {
                q.add(i);
                rank[i] = 1;
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : graph[x]) {
                rank[y] = Math.max(rank[y], rank[x] + 1);
                inDegree[y]--;
                if (inDegree[y] == 0) {
                    q.offer(y);
                }
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = rank[find(parent, i * n + j)];
            }
        }
        return ans;
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }
}
