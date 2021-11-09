package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0996NumberOfSquarefullArrays {

    public int numSquarefulPerms0(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        for (int key : count.keySet()) {
            graph.put(key, new ArrayList<>());
        }
        for (int x : count.keySet()) {
            for (int y : count.keySet()) {
                int k = (int) (Math.sqrt(x + y) + 0.5);
                if (k * k == x + y) {
                    graph.get(x).add(y);
                }
            }
        }
        int ans = 0;
        for (int x : count.keySet()) {
            ans += dfs(x, count, graph, n);
        }
        return ans;
    }

    private int dfs(int x, Map<Integer, Integer> count, Map<Integer, List<Integer>> graph, int remain) {
        if (remain == 1) {
            return 1;
        }
        count.put(x, count.get(x) - 1);
        int ans = 0;
        for (int y : graph.get(x)) {
            if (count.get(y) > 0) {
                ans += dfs(y, count, graph, remain - 1);
            }
        }
        count.put(x, count.get(x) + 1);
        return ans;
    }

    public int numSquarefulPerms(int[] nums) {
        int n = nums.length;
        int M = 1 << n;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int x = nums[i], y = nums[j];
                int k = (int) (Math.sqrt(x + y) + 0.5);
                if (k * k == x + y) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        int[][] dp = new int[M][n];
        for (int i = 0; i < n; ++i) {
            dp[1 << i][i] = 1;
        }
        for (int m = 1; m < M; ++m) {
            for (int i = 0; i < n; ++i) {
                if (dp[m][i] == 0) {
                    continue;
                }
                for (int j : graph.get(i)) {
                    if ((m & (1 << j)) == 0) {
                        dp[m | (1 << j)][j] += dp[m][i];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += dp[M - 1][i];
        }
        int[] permutation = new int[n + 1];
        permutation[1] = 1;
        for (int i = 2; i <= n; ++i) {
            permutation[i] = permutation[i - 1] * i;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        for (int key : count.keySet()) {
            ans /= permutation[count.get(key)];
        }
        return ans;
    }
}
