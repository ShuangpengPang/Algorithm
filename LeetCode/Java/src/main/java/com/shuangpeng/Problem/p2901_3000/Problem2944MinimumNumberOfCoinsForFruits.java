package com.shuangpeng.Problem.p2901_3000;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2944MinimumNumberOfCoinsForFruits（购买水果需要的最少金币数）
 * @date 2024/1/9 4:18 PM
 */
public class Problem2944MinimumNumberOfCoinsForFruits {

    public int minimumCoins0(int[] prices) {
        int n = prices.length, N = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = N;
            for (int j = i; j >= i >> 1; j--) {
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + prices[j]);
            }
        }
        return dp[n];
    }

    public int minimumCoins(int[] prices) {
        int n = prices.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{i, ans + prices[i]});
            while (pq.peek()[0] < i >> 1) {
                pq.poll();
            }
            ans = pq.peek()[1];
        }
        return ans;
    }
}
