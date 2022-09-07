package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1627GraphConnectivityWithThreshold（带阈值的图连通性）
 * @Date 2022/9/7 11:10 AM
 * @Version 1.0
 */
public class Problem1627GraphConnectivityWithThreshold {

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        int[] parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = threshold + 1; i < n; i++) {
            for (int j = i << 1; j <= n; j += i) {
                union(parent, j - 1, j - i - 1);
            }
        }
        int m = queries.length;
        List<Boolean> ans = new ArrayList<>(m);
        for (int[] q : queries) {
            if (find(parent, q[0] - 1) == find(parent, q[1] - 1)) {
                ans.add(true);
            } else {
                ans.add(false);
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
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}
