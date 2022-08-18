package com.shuangpeng.Problem.p0501_0600;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem0547NumberOfProvinces（省份数量）
 * @Date 2022/8/18 2:31 PM
 * @Version 1.0
 */
public class Problem0547NumberOfProvinces {

    public int findCircleNum0(int[][] isConnected) {
        int n = isConnected.length;
        int ans = n;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    int x = find(parent, i), y = find(parent, j);
                    if (x != y) {
                        ans--;
                        parent[x] = y;
                    }
                }
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    public int findCircleNum1(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, i, visited);
                ans++;
            }
        }
        return ans;
    }

    private void dfs(int[][] isConnected, int city, boolean[] visited) {
        int n = isConnected.length;
        visited[city] = true;
        for (int i = 0; i < n; i++) {
            if (isConnected[city][i] == 1 && !visited[i]) {
                dfs(isConnected, i, visited);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans++;
                visited[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while (!q.isEmpty()) {
                    int c = q.poll();
                    for (int j = 0; j < n; j++) {
                        if (isConnected[c][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
