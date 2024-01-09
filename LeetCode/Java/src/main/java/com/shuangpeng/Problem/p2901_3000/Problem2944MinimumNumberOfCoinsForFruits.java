package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
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

    public int minimumCoins1(int[] prices) {
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

    public int minimumCoins2(int[] prices) {
        int n = prices.length, h = n + 1 >> 1;
        Deque<int[]> q = new ArrayDeque<>(n);
        for (int i = n; i >= h; i--) {
            while (!q.isEmpty() && q.peekLast()[1] >= prices[i - 1]) {
                q.pollLast();
            }
            q.offerLast(new int[]{i, prices[i - 1]});
        }
        int ans = q.peekLast()[1];
        for (int i = h - 1; i >= 1; i--) {
            while (q.peekFirst()[0] > (i << 1) + 1) {
                q.pollFirst();
            }
            ans = prices[i - 1] + q.peekFirst()[1];
            while (!q.isEmpty() && q.peekLast()[1] >= ans) {
                q.pollLast();
            }
            q.offerLast(new int[]{i, ans});
        }
        return ans;
    }

    public int minimumCoins(int[] prices) {
        int n = prices.length;
        Deque<int[]> q = new ArrayDeque<>(n);
        q.offerLast(new int[]{n + 1, 0});
        for (int i = n; i > 0; i--) {
            while (q.peekLast()[0] > (i << 1) + 1) {
                q.pollLast();
            }
            int num = prices[i - 1] + q.peekLast()[1];
            while (q.peekFirst()[1] >= num) {
                q.pollFirst();
            }
            q.offerFirst(new int[]{i, num});
        }
        return q.pollFirst()[1];
    }
}
