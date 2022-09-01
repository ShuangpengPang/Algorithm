package com.shuangpeng.competition.第251到260场周赛.第258场周赛;

import java.util.*;

public class Problem2003 {

    public int[] smallestMissingValueSubtree0(int[] parents, int[] nums) {
        int n = parents.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 1; i < n; ++i) {
            graph.get(parents[i]).add(i);
        }
        int[] ans = new int[n];
        dfs(0, graph, nums, ans);
        return ans;
    }

    private List<int[]> dfs(int node, Map<Integer, List<Integer>> graph, int[] nums, int[] ans) {
        List<Integer> children = graph.get(node);
        if (children.isEmpty()) {
            ans[node] = nums[node] == 1 ? 2 : 1;
            return new LinkedList<int[]>() {{
                add(new int[]{nums[node], nums[node]});
            }};
        }
        PriorityQueue<List<int[]>> queue = new PriorityQueue<>((a, b) -> {
            int[] firstA = a.get(0), firstB = b.get(0);
            if (firstA[0] != firstB[0]) {
                return firstA[0] - firstB[0];
            }
            return firstB[1] - firstB[1];
        });
        queue.offer(new LinkedList<int[]>() {{ add(new int[]{nums[node], nums[node]}); }});
        for (int child : children) {
            queue.offer(dfs(child, graph, nums, ans));
        }
        List<int[]> result = new LinkedList<>();
        int start = queue.peek().get(0)[0], end = queue.peek().get(0)[1];
        while (!queue.isEmpty()) {
            List<int[]> list = queue.poll();
            int[] pair = list.remove(0);
            if (pair[0] <= end + 1) {
                end = Math.max(end, pair[1]);
            } else {
                result.add(new int[]{start, end});
                start = pair[0];
                end = pair[1];
            }
            if (!list.isEmpty()) {
                queue.offer(list);
            }
        }
        result.add(new int[]{start, end});
        ans[node] = result.get(0)[0] == 1 ? result.get(0)[1] + 1 : 1;
        return result;
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n; ++i) {
            graph.putIfAbsent(parents[i], new ArrayList<>());
            graph.get(parents[i]).add(i);
        }
        int[] ans = new int[n];
        containsOne(0, graph, nums, ans);
        if (ans[0] == 1) {
            return ans;
        }
        dfs(0, graph, nums, new boolean[n + 2], new int[]{1}, ans);
        return ans;
    }

    private boolean containsOne(int u, Map<Integer, List<Integer>> graph, int[] nums, int[] ans) {
        List<Integer> children = graph.get(u);
        boolean result = nums[u] == 1;
        if (children != null) {
            for (int child : children) {
                result |= containsOne(child, graph, nums, ans);
            }
        }
        if (!result) {
            ans[u] = 1;
        }
        return result;
    }

    private void dfs(int u, Map<Integer, List<Integer>> graph, int[] nums, boolean[] visited, int[] p, int[] ans) {
        List<Integer> children = graph.get(u);
        if (children != null) {
            for (int child : children) {
                if (ans[child] != 1) {
                    dfs(child, graph, nums, visited, p, ans);
                }
            }
            for (int child : children) {
                if (ans[child] == 1) {
                    dfsVisited(child, graph, nums, visited);
                }
            }
        }
        visited[nums[u]] = true;
        while (visited[p[0]]) {
            ++p[0];
        }
        ans[u] = p[0];
    }

    private void dfsVisited(int u, Map<Integer, List<Integer>> graph, int[] nums, boolean[] visited) {
        visited[nums[u]] = true;
        List<Integer> children = graph.get(u);
        if (children != null) {
            for (int child : children) {
                dfsVisited(child, graph, nums, visited);
            }
        }
    }
}
