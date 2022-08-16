package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @Description: Problem1425ConstrainedSubsequenceSum（带限制的子序列和）
 * @Date 2022/8/16 11:55 AM
 * @Version 1.0
 */
public class Problem1425ConstrainedSubsequenceSum {

    public int constrainedSubsetSum0(int[] nums, int k) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && q.peek()[1] < i - k) {
                q.poll();
            }
            int sum = Math.max(nums[i], nums[i] + (q.isEmpty() ? 0 : q.peek()[0]));
            ans = Math.max(ans, sum);
            q.offer(new int[]{sum, i});
        }
        return ans;
    }

    public int constrainedSubsetSum1(int[] nums, int k) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && q.peekFirst()[0] < i - k) {
                q.pollFirst();
            }
            int sum = nums[i] + Math.max(0, q.isEmpty() ? 0 : q.peekFirst()[1]);
            while (!q.isEmpty() && q.peekLast()[1] <= sum) {
                q.pollLast();
            }
            q.addLast(new int[]{i, sum});
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length, ans = nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1, idx = 0; i < n; i++) {
            if (idx < i - k) {
                int max = Integer.MIN_VALUE;
                for (int j = idx + 1; j < i; j++) {
                    if (dp[j] >= max) {
                        max = dp[j];
                        idx = j;
                    }
                }
            }
            dp[i] = nums[i] + Math.max(0, dp[idx]);
            if (dp[i] >= dp[idx]) {
                idx = i;
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
