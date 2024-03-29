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

class Problem1632RankTransformOfAMatrix0 {

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, N = m * n;
        Integer[] ids = new Integer[N];
        int[] parent = new int[N];
        int[] rank = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            parent[i] = i;
        }
        Arrays.sort(ids, Comparator.comparingInt(a -> matrix[a / n][a % n]));
        int[] maxColIndex = new int[m], maxRowIndex = new int[n];
        Arrays.fill(maxColIndex, -1);
        Arrays.fill(maxRowIndex, -1);
        for (int id : ids) {
            int x = id / n, y = id % n;
            int r = 1;
            if (maxColIndex[x] != -1) {
                int j = maxColIndex[x];
                if (matrix[x][y] == matrix[x][j]) {
                    r = rank[find(parent, x * n + j)];
                    union(parent, x * n + y, x * n + j);
                } else {
                    r = rank[find(parent, x * n + j)] + 1;
                }
            }
            if (maxRowIndex[y] != -1) {
                int i = maxRowIndex[y];
                if (matrix[x][y] == matrix[i][y]) {
                    r = Math.max(r, rank[find(parent, i * n + y)]);
                    union(parent, x * n + y, i * n+ y);
                } else {
                    r = Math.max(r, rank[find(parent, i * n + y)] + 1);
                }
            }
            maxColIndex[x] = y;
            maxRowIndex[y] = x;
            int py = find(parent, x * n + y);
            rank[py] = Math.max(rank[py], r);
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

class Problem1632RankTransformOfAMatrix1 {

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, N = m * n;
        Integer[] ids = new Integer[N];
        int[] parent = new int[N], rank = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            parent[i] = i;
        }
        Arrays.sort(ids, Comparator.comparingInt(a -> matrix[a / n][a % n]));
        int[] maxCol = new int[m], maxRow = new int[n];
        Arrays.fill(maxCol, -1);
        Arrays.fill(maxRow, -1);
        for (int id : ids) {
            int i = id / n, j = id % n, r = 1;
            int x = maxRow[j], y = maxCol[i];
            if (y != -1) {
                if (matrix[i][j] == matrix[i][y]) {
                    r = Math.max(r, rank[find(parent, i * n + y)]);
                    union(parent, id, i * n + y);
                } else {
                    r = Math.max(r, rank[find(parent, i * n + y)] + 1);
                }
            }
            if (x != -1) {
                if (matrix[i][j] == matrix[x][j]) {
                    r = Math.max(r, rank[find(parent, x * n + j)]);
                    union(parent, id, x * n + j);
                } else {
                    r = Math.max(r, rank[find(parent, x * n + j)] + 1);
                }
            }
            int p = find(parent, id);
            rank[p] = Math.max(rank[p], r);
            maxCol[i] = j;
            maxRow[j] = i;
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = rank[find(parent, i * n + j)];
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}

class Problem1632RankTransformOfAMatrix2 {

    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, N = m * n;
        int[] parent = new int[N], degree = new int[N];
        Arrays.setAll(parent, i -> i);
        for (int i = 0; i < m; i++) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            int h = i * n;
            for (int j = 0; j < n; j++) {
                map.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(h + j);
            }
            for (List<Integer> list : map.values()) {
                int size = list.size();
                for (int j = 1; j < size; j++) {
                    union(parent, list.get(j - 1), list.get(j));
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int h = j;
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                map.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(h);
                h += n;
            }
            for (List<Integer> list : map.values()) {
                int size = list.size();
                for (int i = 1; i < size; i++) {
                    union(parent, list.get(i - 1), list.get(i));
                }
            }
        }
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0, h = i * n; j < n; j++) {
                map.put(matrix[i][j], h + j);
            }
            List<Integer> list = new ArrayList<>(map.keySet());
            Collections.sort(list);
            int size = list.size();
            for (int j = 1; j < size; j++) {
                int p1 = find(parent, map.get(list.get(j - 1)));
                int p2 = find(parent, map.get(list.get(j)));
                degree[p2]++;
                adj.computeIfAbsent(p1, k -> new ArrayList<>()).add(p2);
            }
        }
        for (int j = 0; j < n; j++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0, h = j; i < m; i++) {
                map.put(matrix[i][j], h);
                h += n;
            }
            List<Integer> list = new ArrayList<>(map.keySet());
            Collections.sort(list);
            int size = list.size();
            for (int i = 1; i < size; i++) {
                int p1 = find(parent, map.get(list.get(i - 1)));
                int p2 = find(parent, map.get(list.get(i)));
                degree[p2]++;
                adj.computeIfAbsent(p1, k -> new ArrayList<>()).add(p2);
            }
        }
        int[] rank = new int[N];
        Arrays.fill(rank, 1);
        Deque<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0, h = i * n; j < n; j++) {
                int p = find(parent, h + j);
                if (set.add(p) && degree[p] == 0) {
                    q.offer(p);
                }
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            if (adj.containsKey(x)) {
                for (int y : adj.get(x)) {
                    rank[y] = Math.max(rank[y], rank[x] + 1);
                    if (--degree[y] == 0) {
                        q.offer(y);
                    }
                }
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0, h = i * n; j < n; j++) {
                ans[i][j] = rank[find(parent, h + j)];
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}
