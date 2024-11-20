package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3244ShortestDistanceAfterRoadAdditionQueriesII（新增道路查询后的最短距离II）
 * @date 2024/11/20 2:38 PM
 */
public class Problem3244ShortestDistanceAfterRoadAdditionQueriesII {

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] next = new int[n];
        Arrays.setAll(next, i -> i + 1);
        int m = queries.length, dis = n - 1;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int s = queries[i][0], e = queries[i][1];
            int j = next[s];
            while (j < e) {
                int t = next[j];
                next[j] = n;
                j = t;
                dis--;
            }
            next[s] = Math.max(next[s], e);
            ans[i] = dis;
        }
        return ans;
    }
}
