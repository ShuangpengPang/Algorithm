package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1519NumberOfNodesInTheSubTreeWithTheSameLabel（子树中标签相同的节点数）
 * @date 2023/8/31 5:53 PM
 */
public class Problem1519NumberOfNodesInTheSubTreeWithTheSameLabel {

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int[] ans = new int[n];
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        dfs(graph, 0, -1, labels, ans);
        return ans;
    }

    private int[] dfs(List<Integer>[] graph,int x, int p, String labels, int[] ans) {
        int[] cnt = new int[26];
        for (int y : graph[x]) {
            if (y != p) {
                addCount(cnt, dfs(graph, y, x, labels, ans));
            }
        }
        ans[x] = ++cnt[labels.charAt(x) - 'a'];
        return cnt;
    }

    private void addCount(int[] cnt1, int[] cnt2) {
        for (int i = 0; i < 26; i++) {
            cnt1[i] += cnt2[i];
        }
    }
}
