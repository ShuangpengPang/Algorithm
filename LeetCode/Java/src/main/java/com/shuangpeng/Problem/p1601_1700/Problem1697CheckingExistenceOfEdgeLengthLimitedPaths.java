package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1697CheckingExistenceOfEdgeLengthLimitedPaths（检查边长度限制的路径是否存在）
 * @Date 2022/9/23 3:11 PM
 * @Version 1.0
 */
public class Problem1697CheckingExistenceOfEdgeLengthLimitedPaths {

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int m = queries.length;
        int[][] map = new int[m][2];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            map[i][0] = q[2];
            map[i][1] = i;
        }
        Arrays.sort(map, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        boolean[] ans = new boolean[m];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0, j = 0; i < m; i++) {
            while (j < edgeList.length && edgeList[j][2] < map[i][0]) {
                union(parent, edgeList[j][0], edgeList[j][1]);
                j++;
            }
            int k = map[i][1];
            ans[k] = find(parent, queries[k][0]) == find(parent, queries[k][1]);
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[px] = py;
        }
    }
}
