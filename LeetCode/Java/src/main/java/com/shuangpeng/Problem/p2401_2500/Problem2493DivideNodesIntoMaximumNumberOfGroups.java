package com.shuangpeng.Problem.p2401_2500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2493DivideNodesIntoMaximumNumberOfGroups（将节点分成尽可能多的组）
 * @date 2022/12/16 11:35 AM
 */
public class Problem2493DivideNodesIntoMaximumNumberOfGroups {

    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        for (int[] edge : edges) {
            int x = edge[0] - 1, y = edge[1] - 1;
            g[x].add(y);
            g[y].add(x);
            union(parent, x, y);
        }
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            int[] group = new int[n];
            int c = 0;
            List<Integer> q = new ArrayList<>();
            q.add(i);
            group[i] = 1;
            boolean valid = true;
            while (valid && !q.isEmpty()) {
                c++;
                List<Integer> tmp = new ArrayList<>();
                for (int j = q.size() - 1; valid && j >= 0; j--) {
                    int x = q.get(j);
                    for (int y : g[x]) {
                        if (group[y] == 0) {
                            group[y] = c + 1;
                            tmp.add(y);
                        } else if (Math.abs(group[y] - c) != 1) {
                            c = 0;
                            valid = false;
                            break;
                        }
                    }
                }
                q = tmp;
            }
            int p = find(parent, i);
            cnt[p] = Math.max(cnt[p], c);
        }
        int ans = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            int p = find(parent, i);
            if (!visited[p]) {
                visited[p] = true;
                int c = cnt[p];
                if (c == 0) {
                    return -1;
                }
                ans += c;
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}
