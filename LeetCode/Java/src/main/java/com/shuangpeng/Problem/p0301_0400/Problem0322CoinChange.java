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

class Problem0322CoinChange0 {

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
