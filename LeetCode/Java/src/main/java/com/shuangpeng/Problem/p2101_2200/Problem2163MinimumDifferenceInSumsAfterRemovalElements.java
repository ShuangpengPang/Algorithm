package com.shuangpeng.Problem.p2101_2200;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2163MinimumDifferenceInSumsAfterRemovalElements（删除元素后和的最小差值）
 * @date 2022/11/21 4:39 PM
 */
public class Problem2163MinimumDifferenceInSumsAfterRemovalElements {

    public long minimumDifference(int[] nums) {
        int n = nums.length, l = n / 3;
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        long s1 = 0;
        for (int i = n - 1; i >= n - l; i--) {
            s1 += nums[i];
            q1.offer(nums[i]);
        }
        long[] right = new long[n];
        right[n - l] = s1;
        for (int i = n - l - 1; i >= l; i--) {
            if (nums[i] > q1.peek()) {
                s1 += nums[i] - q1.poll();
                q1.offer(nums[i]);
            }
            right[i] = s1;
        }
        long s2 = 0;
        PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < l; i++) {
            s2 += nums[i];
            q2.offer(nums[i]);
        }
        long ans = s2 - right[l];
        for (int i = l; i < n - l; i++) {
            if (nums[i] < q2.peek()) {
                s2 += nums[i] - q2.poll();
                q2.offer(nums[i]);
            }
            ans = Math.min(ans, s2 - right[i + 1]);
        }
        return ans;
    }
}