package com.shuangpeng.competition.第290到300场周赛.第299场周赛;

import java.util.*;

/**
 * @Description: Problem2322MinimumScoreAfterRemovalsOnATree（从树中删除边的最小分数）
 * @Date 2022/7/4 11:15 PM
 * @Version 1.0
 */
public class Problem2322MinimumScoreAfterRemovalsOnATree {

    int[] nums;
    int[] arr;
    int[] in, out;
    int time;
    List<Integer>[] graph;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        time = 0;
        arr = new int[n];
        in = new int[n];
        out = new int[n];
        graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        dfs(0, new boolean[n]);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum1 = 0, sum2 = 0, sum3 = 0;
                if (in[i] < in[j] && in[j] < out[i]) {
                    sum1 = arr[0] ^ arr[i];
                    sum2 = arr[j];
                    sum3 = arr[i] ^ sum2;
                } else if (in[j] < in[i] && in[i] < out[j]) {
                    sum1 = arr[0] ^ arr[j];
                    sum2 = arr[i];
                    sum3 = arr[j] ^ sum2;
                } else {
                    sum1 = arr[i];
                    sum2 = arr[j];
                    sum3 = arr[0] ^ arr[i] ^ arr[j];
                }
                int min = Math.min(sum1, Math.min(sum2, sum3));
                int max = Math.max(sum1, Math.max(sum2, sum3));
                ans = Math.min(ans, max - min);
            }
        }
        return ans;
    }

    private int dfs(int u, boolean[] visited) {
        in[u] = time++;
        visited[u] = true;
        int ans = nums[u];
        for (int v : graph[u]) {
            if (!visited[v]) {
                ans ^= dfs(v, visited);
            }
        }
        out[u] = time;
        arr[u] = ans;
        return ans;
    }
}


class Problem2322MinimumScoreAfterRemovalsOnATree0 {

    int[] nums;
    int[] parent, xorSum;
    boolean[][] isParent;
    List<Integer>[] g;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        g = new List[n];
        xorSum = new int[n];
        parent = new int[n];
        isParent = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            parent[i] = i;
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        dfs(0, -1);
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, -1});
        while (!q.isEmpty()) {
            int[] e = q.poll();
            int curr = e[0], prev = e[1];
            for (int x : g[curr]) {
                if (x == prev) {
                    continue;
                }
                parent[x] = curr;
                q.offer(new int[]{x, curr});
                int p = curr;
                while (p != parent[p]) {
                    isParent[x][p] = true;
                    p = parent[p];
                }
                isParent[x][p] = true;
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum1 = 0, sum2 = 0, sum3 = 0;
                if (isParent[i][j]) {
                    sum1 = xorSum[0] ^ xorSum[j];
                    sum2 = xorSum[i];
                    sum3 = xorSum[j] ^ sum2;
                } else if (isParent[j][i]) {
                    sum1 = xorSum[0] ^ xorSum[i];
                    sum2 = xorSum[j];
                    sum3 = xorSum[i] ^ sum2;
                } else {
                    sum1 = xorSum[i];
                    sum2 = xorSum[j];
                    sum3 = xorSum[0] ^ sum1 ^ sum2;
                }
                ans = Math.min(ans, Math.max(sum1, Math.max(sum2, sum3)) - Math.min(sum1, Math.min(sum2, sum3)));
                if (ans == 0) {
                    return 0;
                }
            }
        }
        return ans;
    }

    private void dfs(int x, int p) {
        int ans = nums[x];
        for (int y : g[x]) {
            if (y != p) {
                dfs(y, x);
                ans ^= xorSum[y];
            }
        }
        xorSum[x] = ans;
    }
}

