package com.shuangpeng.Problem.p3301_3400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3372MaximizeTheNumberOfTargetNodesAfterConnectingTreesI（连接两颗树后最大目标节点数目）
 * @date 2025/4/3 14:13
 */
public class Problem3372MaximizeTheNumberOfTargetNodesAfterConnectingTreesI {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1, m = edges2.length + 1;
        int[] ans = new int[n];
        if (k == 0) {
            Arrays.fill(ans, 1);
            return ans;
        }
        List<Integer>[] g1 = build(edges1), g2 = build(edges2);
        Map<Integer, Integer>[] m1 = new Map[n], m2 = new Map[m];
        Arrays.setAll(m1, i -> new HashMap<>());
        Arrays.setAll(m2, i -> new HashMap<>());
        dfs(g1, 0, -1, k, m1);
        dfs0(g1, 0, -1, k, m1);
        dfs(g2, 0, -1, k - 1, m2);
        dfs0(g2, 0, -1, k - 1, m2);
        int mx = 0;
        for (int i = 0; i < m; i++) {
            mx = Math.max(mx, getCount(m2[i]));
            int j = mx;
        }
        for (int i = 0; i < n; i++) {
            ans[i] = getCount(m1[i]) + mx;
        }
        return ans;
    }

    private int getCount(Map<Integer, Integer> m) {
        int cnt = 0;
        for (int c : m.values()) {
            cnt += c;
        }
        return cnt;
    }

    private List<Integer>[] build(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        return g;
    }

    private void dfs(List<Integer>[] g, int x, int p, int k, Map<Integer, Integer>[] maps) {
        Map<Integer, Integer> m = maps[x];
        m.put(0, 1);
        for (int y : g[x]) {
            if (y != p) {
                dfs(g, y, x, k, maps);
                for (Map.Entry<Integer, Integer> entry : maps[y].entrySet()) {
                    int d = entry.getKey() + 1;
                    if (d <= k) {
                        m.merge(d, entry.getValue(), Integer::sum);
                    }
                }
            }
        }
    }

    private void dfs0(List<Integer>[] g, int x, int p, int k, Map<Integer, Integer>[] maps) {
        Map<Integer, Integer> m = maps[x];
        if (p != -1) {
            int mx = 0;
            for (int d : maps[p].keySet()) {
                mx = Math.max(mx, d);
            }
            for (int d = Math.min(mx, k - 1); d >= 0; d--) {
                m.merge(d + 1, maps[p].get(d) - m.getOrDefault(d - 1, 0), Integer::sum);
            }
        }
        for (int y : g[x]) {
            if (y != p) {
                dfs0(g, y, x, k, maps);
            }
        }
    }

//    public static void main(String[] args) {
//        int[][] edge1 = {{2,1},{7,3},{0,4},{7,5},{2,6},{0,2},{0,7}};
//        int[][] edge2 = {{3,0},{1,2},{5,1},{6,3},{9,4},{5,6},{7,5},{9,7},{8,9}};
//        Problem3372MaximizeTheNumberOfTargetNodesAfterConnectingTreesI a = new Problem3372MaximizeTheNumberOfTargetNodesAfterConnectingTreesI();
//        a.maxTargetNodes(edge1, edge2, 7);
//    }
}
