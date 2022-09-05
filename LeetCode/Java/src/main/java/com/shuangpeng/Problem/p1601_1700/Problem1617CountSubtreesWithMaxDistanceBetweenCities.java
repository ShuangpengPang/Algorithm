package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1617CountSubtreesWithMaxDistanceBetweenCities（统计子树中城市之间最大距离）
 * @Date 2022/9/5 4:28 PM
 * @Version 1.0
 */
public class Problem1617CountSubtreesWithMaxDistanceBetweenCities {

    int maxDistance, cnt;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            graph[x].add(y);
            graph[y].add(x);
        }
        int[] ans = new int[n - 1];
        for (int i = 1; i < 1 << n; i++) {
            int count = Integer.bitCount(i);
            if (count < 2) {
                continue;
            }
            int j = 0;
            while (j < n && (i & (1 << j)) == 0) {
                j++;
            }
            if (j < n) {
                maxDistance = 0;
                cnt = 0;
                getDepth(graph, j, -1, i);
                if (count == cnt) {
                    ans[maxDistance - 1]++;
                }
            }
        }
        return ans;
    }

    private int getDepth(List<Integer>[] graph, int c, int p, int m) {
        cnt++;
        int maxDepth = 0;
        for (int x : graph[c]) {
            if (x != p && (m & (1 << x)) != 0) {
                int depth = getDepth(graph, x, c, m) + 1;
                maxDistance = Math.max(maxDistance, maxDepth + depth);
                maxDepth = Math.max(maxDepth, depth);
            }
        }
        return maxDepth;
    }
}
