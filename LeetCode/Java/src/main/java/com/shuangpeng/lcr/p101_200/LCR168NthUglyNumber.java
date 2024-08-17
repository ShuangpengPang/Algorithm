package com.shuangpeng.lcr.p101_200;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR168NthUglyNumber（LCR 168. 丑数）
 * @date 2024/8/16 5:03 PM
 */
public class LCR168NthUglyNumber {

    public int nthUglyNumber0(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            while (pq.peek() == ans)  {
                pq.poll();
            }
            ans = pq.poll();
            pq.offer(ans * 2);
            pq.offer(ans * 3);
            pq.offer(ans * 5);
        }
        return (int) ans;
    }

    public int nthUglyNumber(int n) {
        int[] p = {0, 0, 0}, factors = {2, 3, 5};
        long[] dp = new long[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            long num = Long.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                num = Math.min(num, dp[p[j]] * factors[j]);
            }
            for (int j = 0; j < 3; j++) {
                if (dp[p[j]] * factors[j] == num) {
                    p[j]++;
                }
            }
            dp[i] = num;
        }
        return (int) dp[n - 1];
    }
}
