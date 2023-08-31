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


class Problem1519NumberOfNodesInTheSubTreeWithTheSameLabel0 {

    // 边的编号
    int idx;
    // e[idx]：编号为idx所指向的节点
    int[] e;
    // ne[idx]: 编号为idx的边的上一条边
    int[] ne;
    // h[x]: x节点的最后一条边
    int[] h;
    int[] cs;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        int N = n - 1 << 1;
        idx = 0;
        e = new int[N];
        ne = new int[N];
        h = new int[n];
        Arrays.fill(h, -1);
        for (int[] p : edges) {
            add(p[0], p[1]);
            add(p[1], p[0]);
        }
        cs = new int[n];
        for (int i = 0; i < n; i++) {
            cs[i] = labels.charAt(i) - 'a';
        }
        int[] ans = new int[n];
        dfs(0, -1, ans);
        return ans;
    }

    private void add(int x, int y) {
        e[idx] = y;
        ne[idx] = h[x];
        h[x] = idx++;
    }

    private int[] dfs(int x, int p, int[] ans) {
        int[] cnt = new int[26];
        for (int i = h[x]; i != -1; i = ne[i]) {
            int y = e[i];
            if (y != p) {
                int[] tmp = dfs(y, x, ans);
                for (int j = 0; j < 26; j++) {
                    cnt[j] += tmp[j];
                }
            }
        }
        ans[x] = ++cnt[cs[x]];
        return cnt;
    }
}
