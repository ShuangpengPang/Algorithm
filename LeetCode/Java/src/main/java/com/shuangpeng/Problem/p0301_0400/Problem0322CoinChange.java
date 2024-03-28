package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

/**
 * @description:（零钱兑换）
 * @date 2024/3/27 2:10 PM
 **/
public class Problem0322CoinChange {

//    public static void main(String[] args) {
//        int[] coins = {1, 2, 5};
//        Problem0322CoinChange a = new Problem0322CoinChange();
//        int i = a.coinChange(coins, 100);
//    }

    public int coinChange0(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>(coins.length);
        for (int i = 0; i < coins.length; i++) {
            set.add(coins[i]);
        }
        boolean[] visited = new boolean[amount + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(amount);
        visited[amount] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int data = queue.poll();
                if (set.contains(data)) {
                    return count;
                }
                for (int d : set) {
                    if (data - d > 0 && !visited[data - d]) {
                        visited[data - d] = true;
                        queue.offer(data - d);
                    }
                }
            }
        }
        return -1;
    }

    public int coinChange1(int[] coins, int amount) {
        boolean[] visited = new boolean[amount];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(amount);
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int a = q.poll();
                if (a == 0) {
                    return step;
                }
                for (int coin : coins) {
                    if (a >= coin && !visited[a - coin]) {
                        q.offer(a - coin);
                        visited[a - coin] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}

class Problem0322CoinChange0 {
    private int[] coins;
    private int[][] memo;

    public int coinChange0(int[] coins, int amount) {
        this.coins = coins;
        int n = coins.length;
        memo = new int[n][amount + 1];
        for (int[] row : memo)
            Arrays.fill(row, -1); // -1 表示没有访问过
        int ans = dfs(n - 1, amount);
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    private int dfs(int i, int c) {
        if (i < 0) return c == 0 ? 0 : Integer.MAX_VALUE / 2; // 除 2 是防止下面 + 1 溢出
        if (memo[i][c] != -1) return memo[i][c];
        if (c < coins[i]) return memo[i][c] = dfs(i - 1, c);
        return memo[i][c] = Math.min(dfs(i - 1, c), dfs(i, c - coins[i]) + 1);
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length, N = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[n + 1][amount + 1];
        for (int[] a : dp) {
            Arrays.fill(a, N);
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                    int k = j - coins[i - 1];
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][k], dp[i][k]) + 1);
                }
            }
        }
        return dp[n][amount] == N ? -1 : dp[n][amount];
    }
}

class Problem0322CoinChange1 {

    private static final int N = Integer.MAX_VALUE >> 1;

    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -1);
        int ans = dfs(coins, amount, memo);
        return ans == N ? -1 : ans;
    }

    private int dfs(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != -1) {
            return memo[amount];
        }
        int ans = N;
        for (int coin : coins) {
            if (amount >= coin) {
                ans = Math.min(ans, dfs(coins, amount - coin, memo));
            }
        }
        return memo[amount] = Math.min(N, ans + 1);
    }
}

class Problem0322CoinChange2 {

    public int coinChange0(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2); // 除 2 是防止下面 + 1 溢出
        f[0] = 0;
        for (int x : coins)
            for (int c = x; c <= amount; ++c)
                f[c] = Math.min(f[c], f[c - x] + 1);
        int ans = f[amount];
        return ans < Integer.MAX_VALUE / 2 ? ans : -1;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int N = Integer.MAX_VALUE >> 1;
        Arrays.fill(dp, N);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == N ? -1 : dp[amount];
    }
}
