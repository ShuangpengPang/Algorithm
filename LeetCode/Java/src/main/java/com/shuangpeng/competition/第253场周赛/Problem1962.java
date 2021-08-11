package com.shuangpeng.competition.第253场周赛;

import java.util.PriorityQueue;

public class Problem1962 {

    public int minStoneSum0(int[] piles, int k) {
        int n = piles.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b -a);
        for (int i = 0; i < n; ++i) {
            queue.offer(piles[i]);
        }
        int i = 0;
        while (i < k) {
            int p = queue.poll();
            p = p - (int) Math.floor(p / 2);
            queue.offer(p);
            i++;
        }
        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }
        return sum;
    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b -a);
        int sum = 0;
        for (int p : piles) {
            queue.offer(p);
            sum += p;
        }
        for (int i = 0; i < k; ++i) {
            int p = queue.poll();
            int r = (int) Math.floor(p >> 1);
            queue.offer(p - r);
            sum -= r;
        }
        return sum;
    }
}
