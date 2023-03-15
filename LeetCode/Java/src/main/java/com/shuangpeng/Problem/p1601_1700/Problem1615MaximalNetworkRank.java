package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1615MaximalNetworkRank（最大网络秩）
 * @date 2023/3/15 4:18 PM
 */
public class Problem1615MaximalNetworkRank {

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] cnt = new int[n];
        Set<Integer>[] adj = new Set[n];
        Arrays.setAll(adj, i -> new HashSet<>());
        for (int[] road : roads) {
            int a = road[0], b = road[1];
            cnt[a]++;
            cnt[b]++;
            adj[a].add(b);
            adj[b].add(a);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int c = cnt[i];
            for (int j = i + 1; j < n; j++) {
                int count = c + cnt[j];
                if (adj[i].contains(j)) {
                    count--;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
