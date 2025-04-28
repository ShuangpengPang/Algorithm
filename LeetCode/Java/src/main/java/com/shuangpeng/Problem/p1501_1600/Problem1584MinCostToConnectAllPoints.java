package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1584MinCostToConnectAllPoints（连接所有点的最小费用）
 * @date 2025/4/27 14:48
 */
public class Problem1584MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        List<int[]> pairs = new ArrayList<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int d = Math.abs(x1 - points[j][0]) + Math.abs(y1 - points[j][1]);
                pairs.add(new int[]{d, i, j});
            }
        }
        pairs.sort(Comparator.comparingInt(a -> a[0]));
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        int ans = 0, cnt = n, size = pairs.size();
        for (int i = 0; i < size && cnt > 1; i++) {
            int[] p = pairs.get(i);
            int d = p[0], x = p[1], y = p[2];
            int px = find(parent, x), py = find(parent, y);
            if (px != py) {
                ans += d;
                parent[px] = py;
                cnt--;
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}
