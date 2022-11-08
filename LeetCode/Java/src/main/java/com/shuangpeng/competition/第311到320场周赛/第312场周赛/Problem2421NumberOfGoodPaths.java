package com.shuangpeng.competition.第311到320场周赛.第312场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2421NumberOfGoodPaths（好路径的数目）
 * @date 2022/11/8 3:54 PM
 */
public class Problem2421NumberOfGoodPaths {

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        Integer[] nodes = new Integer[n];
        Arrays.setAll(nodes, i -> i);
        Arrays.sort(nodes, Comparator.comparingInt(i -> vals[i]));
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            graph[x].add(y);
            graph[y].add(x);
        }
        int[] parent = new int[n], size = new int[n];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(size, 1);
        Map<Integer, Integer>[] maps = new Map[n];
        Arrays.setAll(maps, i -> {
            maps[i] = new HashMap<>();
            maps[i].put(vals[i], 1);
            return maps[i];
        });
        boolean[] visited = new boolean[n];
        int ans = 0;
        for (int node : nodes) {
            ans++;
            for (int x : graph[node]) {
                if (visited[x]) {
                    ans += union(parent, size, maps, node, x, vals[node]);
                }
            }
            visited[node] = true;
        }
        return ans;
    }

    private int union(int[] parent, int[] size, Map<Integer, Integer>[] maps, int x, int y, int val) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return 0;
        }
        if (size[px] >= size[py]) {
            return connect(parent, size, maps, px, py, val);
        }
        return connect(parent, size, maps, py, px, val);
    }

    private int connect(int[] parent, int[] size, Map<Integer, Integer>[] maps, int x, int y, int val) {
        parent[y] = x;
        size[x] += size[y];
        Map<Integer, Integer> map = maps[x];
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : maps[y].entrySet()) {
            int value = entry.getKey(), cnt = entry.getValue();
            if (value == val) {
                ans = map.getOrDefault(value, 0) * cnt;
            }
            map.put(value, map.getOrDefault(value, 0) + cnt);
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}
