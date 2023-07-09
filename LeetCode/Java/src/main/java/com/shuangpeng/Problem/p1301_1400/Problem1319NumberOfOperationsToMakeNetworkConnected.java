package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1319NumberOfOperationsToMakeNetworkConnected（连通网络的操作次数）
 * @date 2023/7/9 4:50 PM
 */
public class Problem1319NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        int cnt = n;
        for (int[] c : connections) {
            cnt -= union(parent, c[0], c[1]);
        }
        return cnt - 1;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private int union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return 0;
        }
        parent[py] = px;
        return 1;
    }
}
