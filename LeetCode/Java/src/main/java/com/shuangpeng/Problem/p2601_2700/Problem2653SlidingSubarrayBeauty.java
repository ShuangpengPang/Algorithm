package com.shuangpeng.Problem.p2601_2700;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2653SlidingSubarrayBeauty（滑动子数组的美丽值）
 * @date 2023/12/10 6:15 PM
 */
public class Problem2653SlidingSubarrayBeauty {

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length, m = n - k + 1, cnt = 0;
        boolean[] has = new boolean[n];
        PriorityQueue<Integer> q1 = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        for (int i = 0; i < k; i++) {
            if (cnt < x) {
                q1.offer(i);
                has[i] = true;
                cnt++;
            } else {
                q2.offer(i);
                check(nums, has, 0, q1, q2);
            }
        }
        int[] ans = new int[m];
        ans[0] = Math.min(0, nums[q1.peek()]);
        for (int i = 1, j = i + k - 1; i < m; i++, j++) {
            q2.offer(j);
            if (has[i - 1]) {
                cnt--;
            }
            while (cnt < x) {
                while (q2.peek() < i) {
                    q2.poll();
                }
                int idx = q2.peek();
                q1.offer(q2.poll());
                has[idx] = true;
                cnt++;
            }
            check(nums, has, i, q1, q2);
            while (q1.peek() < i) {
                q1.poll();
            }
            ans[i] = Math.min(0, nums[q1.peek()]);
        }
        return ans;
    }

    private void check(int[] nums, boolean[] has, int i, PriorityQueue<Integer> q1, PriorityQueue<Integer> q2) {
        while (q1.peek() < i) {
            q1.poll();
        }
        while (!q2.isEmpty() && q2.peek() < i) {
            q2.poll();
        }
        if (q2.isEmpty()) {
            return;
        }
        if (nums[q2.peek()] < nums[q1.peek()]) {
            int idx1 = q1.peek(), idx2 = q2.peek();
            has[idx1] = false;
            has[idx2] = true;
            q1.poll();
            q2.poll();
            q1.offer(idx2);
            q2.offer(idx1);
        }
    }
}
