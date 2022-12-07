package com.shuangpeng.Problem.P2201_2300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2242MaximumScoreOfANodeSequence（节点序列的最大得分）
 * @date 2022/12/6 4:27 PM
 */
public class Problem2242MaximumScoreOfANodeSequence {

    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(new int[]{scores[y], y});
            g[y].add(new int[]{scores[x], x});
        }
        for (int i = 0; i < n; i++) {
            if (g[i].size() > 3) {
                g[i].sort((a, b) -> b[0] - a[0]);
                g[i] = g[i].subList(0, 3);
            }
        }
        int ans = -1;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            int score = scores[x] + scores[y];
            for (int[] p1 : g[x]) {
                int a = p1[1];
                int sum = score + p1[0];
                for (int[] p2 : g[y]) {
                    int b = p2[1];
                    if (a != y && b != x && a != b) {
                        ans = Math.max(ans, sum + p2[0]);
                    }
                }
            }
        }
        return ans;
    }
}