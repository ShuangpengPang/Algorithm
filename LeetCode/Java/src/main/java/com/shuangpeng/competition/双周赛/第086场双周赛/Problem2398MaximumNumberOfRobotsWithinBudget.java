package com.shuangpeng.competition.双周赛.第086场双周赛;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @Description: Problem2398MaximumNumberOfRobotsWithinBudget（预算内的最多机器人数目）
 * @Date 2022/11/1 4:18 PM
 * @Version 1.0
 */
public class Problem2398MaximumNumberOfRobotsWithinBudget {

    public int maximumRobots0(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length, ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        long sum = 0L;
        for (int l = 0, r = 0; r < n; r++) {
            sum += runningCosts[r];
            pq.offer(new int[]{chargeTimes[r], r});
            while (l <= r && (pq.peek()[1] < l || (r - l + 1) * sum + pq.peek()[0] > budget)) {
                if (pq.peek()[1] < l) {
                    pq.poll();
                    continue;
                }
                sum -= runningCosts[l++];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length, ans = 0;
        long sum = 0L;
        Deque<Integer> q = new ArrayDeque<>();
        for (int l = 0, r = 0; r < n; r++) {
            sum += runningCosts[r];
            while (!q.isEmpty() && chargeTimes[q.peekLast()] <= chargeTimes[r]) {
                q.pollLast();
            }
            q.offerLast(r);
            while (!q.isEmpty() && chargeTimes[q.peekFirst()] + sum * (r - l + 1) > budget) {
                if (q.peekFirst() == l) {
                    q.pollFirst();
                }
                sum -= runningCosts[l++];
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
