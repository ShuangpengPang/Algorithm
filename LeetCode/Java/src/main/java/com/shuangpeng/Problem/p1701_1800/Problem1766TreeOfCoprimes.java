package com.shuangpeng.Problem.p1701_1800;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1766TreeOfCoprimes（互质树）
 * @Date 2022/10/10 2:54 PM
 * @Version 1.0
 */
public class Problem1766TreeOfCoprimes {

    static int N = 51;
    static boolean[][] isPrime = new boolean[N][N];
    static {
        for (int i = 1; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (gcd(i, j) == 1) {
                    isPrime[i][j] = true;
                    isPrime[j][i] = true;
                }
            }
        }
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            graph[x].add(y);
            graph[y].add(x);
        }
        int[] depth = new int[n];
        buildDepth(graph, depth, 0, -1, 0);
        int[] last = new int[N];
        Arrays.fill(last, -1);
        int[] ans = new int[n];
        dfs(graph, nums, depth, last, ans, 0, -1);
        return ans;
    }

    private void dfs(List<Integer>[] graph, int[] nums, int[] depth, int[] last, int[] ans, int x, int p) {
        int d = -1, res = -1;
        for (int i = 1; i < N; i++) {
            if (last[i] != -1 && isPrime[i][nums[x]] && depth[last[i]] > d) {
                d = depth[last[i]];
                res = last[i];
            }
        }
        ans[x] = res;
        int t = last[nums[x]];
        last[nums[x]] = x;
        for (int y : graph[x]) {
            if (y != p) {
                dfs(graph, nums, depth, last, ans, y, x);
            }
        }
        last[nums[x]] = t;
    }

    private void buildDepth(List<Integer>[] graph, int[] depth, int x, int p, int d) {
        depth[x] = d;
        for (int c : graph[x]) {
            if (c != p) {
                buildDepth(graph, depth, c, x, d + 1);
            }
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
