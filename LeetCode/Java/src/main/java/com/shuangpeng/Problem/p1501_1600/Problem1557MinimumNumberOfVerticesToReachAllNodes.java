package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1557MinimumNumberOfVerticesToReachAllNodes（可以到达所有点的最少点数目）
 * @date 2023/9/5 5:00 PM
 */
public class Problem1557MinimumNumberOfVerticesToReachAllNodes {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] visited = new boolean[n];
        for (List<Integer> e : edges) {
            int x = e.get(0), y = e.get(1);
            if (x != y) {
                visited[y] = true;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
