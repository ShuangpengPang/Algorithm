package com.shuangpeng.competition.第299场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
