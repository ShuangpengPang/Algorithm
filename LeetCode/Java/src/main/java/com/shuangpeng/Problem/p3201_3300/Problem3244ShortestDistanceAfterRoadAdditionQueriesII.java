package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3244ShortestDistanceAfterRoadAdditionQueriesII（新增道路查询后的最短距离II）
 * @date 2024/11/20 2:38 PM
 */
public class Problem3244ShortestDistanceAfterRoadAdditionQueriesII {

    public int[] shortestDistanceAfterQueries0(int n, int[][] queries) {
        int[] next = new int[n];
        Arrays.setAll(next, i -> i + 1);
        int m = queries.length, dis = n - 1;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int s = queries[i][0], e = queries[i][1];
            while (next[s] < e) {
                int t = next[s];
                next[s] = e;
                s = t;
                dis--;
            }
            ans[i] = dis;
        }
        return ans;
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] parent = new int[n - 1];
        Arrays.setAll(parent, i -> i);
        int m = queries.length, dis = n - 1;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int s = queries[i][0], e = queries[i][1];
            int p = find(parent, e - 1);
            for (int j = find(parent, s); j != p; j = find(parent, j + 1)) {
                parent[j] = p;
                dis--;
            }
            ans[i] = dis;
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        int p = x;
        while (parent[p] != p) {
            p = parent[p];
        }
        while (x != p) {
            parent[x] = p;
            x = parent[x];
        }
        return p;
    }
}
