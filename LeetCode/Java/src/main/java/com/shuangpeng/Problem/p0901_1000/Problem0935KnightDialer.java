package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0935KnightDialer {

    public int knightDialer0(int n) {
        List<Integer>[] lists = new List[10];
        for (int i = 0; i < 10; ++i) {
            lists[i] = new ArrayList<>();
        }
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        for (int i = 0; i < 4; ++i) {
            for (int j = 1; j < 4; ++j) {
                int idx = i * 3 + j;
                if (idx < 1) {
                    continue;
                }
                if (idx > 9 && idx != 11) {
                    continue;
                }
                idx = idx == 11 ? 0 : idx;
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    int key = x * 3 + y;
                    if (x < 0 || x >= 4 || y < 1 || y >= 4 || (key > 9 && key != 11)) {
                        continue;
                    }
                    key = key == 11 ? 0 : key;
                    lists[idx].add(key);
                }
            }
        }
        int[] dp = new int[10];
        Arrays.fill(dp, 1);
        final int M = (int) 1e9 + 7;
        for (int i = 1; i < n; ++i) {
            int[] temp = new int[10];
            for (int j = 0; j < 10; ++j) {
                for (int k : lists[j]) {
                    temp[j] += dp[k];
                    if (temp[j] >= M) {
                        temp[j] -= M;
                    }
                }
            }
            dp = temp;
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            ans += dp[i];
            if (ans >= M) {
                ans -= M;
            }
        }
        return ans;
    }

    public int knightDialer1(int N) {
        int MOD = 1_000_000_007;
        int[][] moves = new int[][]{
                {4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0},
                {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < N - 1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei : moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= MOD;
                }
        }

        long ans = 0;
        for (int x : dp[~N & 1])
            ans += x;
        return (int) (ans % MOD);
    }

    public int knightDialer(int n) {
        int[][] moves = {
                {4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9},
                {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}
        };
        final int M = (int) 1e9 + 7;
        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < n; ++i) {
            int k = i & 1;
            Arrays.fill(dp[k], 0);
            for (int j = 0; j < 10; ++j) {
                for (int p : moves[j]) {
                    dp[k][j] += dp[1 - k][p];
                    if (dp[k][j] >= M) {
                        dp[k][j] -= M;
                    }
                }
            }
        }
        long ans = 0;
        int j = (n - 1) & 1;
        for (int i = 0; i < 10; ++i) {
            ans += dp[j][i];
        }
        return (int) (ans % M);
    }
}
