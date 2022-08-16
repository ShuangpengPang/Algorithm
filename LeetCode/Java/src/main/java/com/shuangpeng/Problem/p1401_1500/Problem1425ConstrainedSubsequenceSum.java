package com.shuangpeng.Problem.p1401_1500;

import java.util.PriorityQueue;

/**
 * @Description: Problem1425ConstrainedSubsequenceSum（带限制的子序列和）
 * @Date 2022/8/16 11:55 AM
 * @Version 1.0
 */
public class Problem1425ConstrainedSubsequenceSum {

    public int constrainedSubsetSum(int[] nums, int k) {
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
}
