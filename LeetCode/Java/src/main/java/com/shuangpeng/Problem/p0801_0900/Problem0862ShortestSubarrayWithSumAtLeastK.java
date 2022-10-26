package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description:（和至少为K的最短子数组）
 * @Date 2022/10/26 7:26 PM
 **/
public class Problem0862ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray0(int[] nums, int k) {
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

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(0);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && preSum[stack.peekLast()] >= preSum[i]) {
                stack.pollLast();
            }
            while (!stack.isEmpty() && preSum[i] - preSum[stack.peekFirst()] >= k) {
                ans = Math.min(ans, i - stack.pollFirst());
            }
            stack.offerLast(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

//    public static void main(String[] args) {
//        Problem0862ShortestSubarrayWithSumAtLeastK a = new Problem0862ShortestSubarrayWithSumAtLeastK();
//        int[] nums = {84, -37, 32, 40, 95};
//        a.shortestSubarray(nums, 167);
//    }
}
