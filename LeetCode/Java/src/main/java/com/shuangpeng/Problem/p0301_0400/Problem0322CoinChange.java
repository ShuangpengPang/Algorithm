package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

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

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
