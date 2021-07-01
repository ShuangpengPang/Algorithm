package com.shuangpeng.lcp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LCP07 {

    public int numWays0(int n, int[][] relation, int k) {
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < relation.length; i++) {
            int u = relation[i][0];
            int v = relation[i][1];
            edges[u].add(v);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int step = 0;
        while (!queue.isEmpty() && step < k) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                for (int v : edges[u]) {
                    queue.offer(v);
                }
            }
        }
        if (step == k) {
            int count = 0;
            while (!queue.isEmpty()) {
                count += queue.poll() == n - 1 ? 1 : 0;
            }
            return count;
        }
        return 0;
    }

    public int numWays1(int n, int[][] relation, int k) {
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] r : relation) {
            edges[r[0]].add(r[1]);
        }
        if (edges[0].isEmpty()) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] temp = new int[n];
            for (int u = 0; u < n; u++) {
                if (dp[u] > 0) {
                    for (int v : edges[u]) {
                        temp[v] += dp[u];
                    }
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }

    public int numWays(int n, int[][] relation, int k) {
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] r : relation) {
            edges[r[0]].add(r[1]);
        }
        int[] answer = new int[1];
        dfs(edges, 0, k, answer);
        return answer[0];
    }

    private void dfs(List<Integer>[] edges, int i, int remain, int[] answer) {
        int n = edges.length;
        if (remain == 0) {
            if (i == n - 1) {
                answer[0]++;
            }
            return;
        }
        for (int j : edges[i]) {
            dfs(edges, j, remain - 1, answer);
        }
    }
}
