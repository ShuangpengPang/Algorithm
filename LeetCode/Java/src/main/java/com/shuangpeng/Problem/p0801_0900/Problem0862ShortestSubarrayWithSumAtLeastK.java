package com.shuangpeng.Problem.p0801_0900;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0862ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        Deque<Integer> deque = new LinkedList<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; ++i) {
            while (!deque.isEmpty() && preSum[deque.peekLast()] >= preSum[i]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && preSum[deque.peekFirst()] <= preSum[i] - k) {
                ans = Math.min(ans, i - deque.pollFirst());
            }
            deque.offerLast(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
