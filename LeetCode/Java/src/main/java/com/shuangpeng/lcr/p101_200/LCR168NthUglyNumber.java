package com.shuangpeng.lcr.p101_200;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR168NthUglyNumber（LCR 168. 丑数）
 * @date 2024/8/16 5:03 PM
 */
public class LCR168NthUglyNumber {

    public int nthUglyNumber(int n) {
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
}
