package com.shuangpeng.Problem.p1801_1900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1857LargestColorValueInADirectedGraph（有向图中最大颜色值）
 * @Date 2022/10/20 5:38 PM
 * @Version 1.0
 */
public class Problem1857LargestColorValueInADirectedGraph {

    int maxFreq;

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        boolean[] hasInDegree = new boolean[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            hasInDegree[edge[1]] = true;
        }
        int ans = -1;
        boolean[] visited = new boolean[n];
        int[][] memo = new int[n][0];
        for (int i = 0; i < n; i++) {
            if (!hasInDegree[i]) {
                maxFreq = 0;
                if (dfs(colors, graph, i, visited, memo) == null) {
                    return -1;
                }
                ans = Math.max(ans, maxFreq);
            }
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return -1;
            }
        }
        return ans;
    }

    private int[] dfs(String colors, List<Integer>[] graph, int x, boolean[] visited, int[][] memo) {
        if (memo[x].length != 0) {
            return memo[x];
        }
        if (visited[x]) {
            return null;
        }
        visited[x] = true;
        int[] cnt = new int[26];
        for (int y : graph[x]) {
            int[] arr = dfs(colors, graph, y, visited, memo);
            if (arr == null) {
                return null;
            }
            for (int j = 0; j < 26; j++) {
                cnt[j] = Math.max(cnt[j], arr[j]);
            }
        }
        cnt[colors.charAt(x) - 'a']++;
        for (int i = 0; i < 26; i++) {
            maxFreq = Math.max(maxFreq, cnt[i]);
        }
        memo[x] = cnt;
        return cnt;
    }
}
