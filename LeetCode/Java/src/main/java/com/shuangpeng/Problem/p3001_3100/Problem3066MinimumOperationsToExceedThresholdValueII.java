package com.shuangpeng.Problem.p3001_3100;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3066MinimumOperationsToExceedThresholdValueII（超过阈值的最少操作数II）
 * @date 2024/3/6 12:10 PM
 */
public class Problem3066MinimumOperationsToExceedThresholdValueII {

    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (long num : nums) {
            pq.offer(num);
        }
        int ans = 0;
        while (pq.peek() < k) {
            long x = pq.poll(), y = pq.poll();
            pq.offer((Math.min(x, y) << 1) + Math.max(x, y));
            ans++;
        }
        return ans;
    }
}
