package com.shuangpeng.Problem.p1001_1100;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1046LastStoneWeight（最后一块石头的重量）
 * @date 2024/1/7 11:05 AM
 */
public class Problem1046LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int s : stones) {
            pq.offer(s);
        }
        while (pq.size() > 1) {
            int diff = pq.poll() - pq.poll();
            if (diff != 0) {
                pq.offer(diff);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
