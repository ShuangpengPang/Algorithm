package com.shuangpeng.Problem.p3201_3300;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3264FindArrayStateAfterKMultiplicationOperationsI（K次乘运算后的最终数组I）
 * @date 2024/11/8 11:18 AM
 */
public class Problem3264FindArrayStateAfterKMultiplicationOperationsI {

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            pq.offer(i);
        }
        for (int i = 0; i < k; i++) {
            int idx = pq.poll();
            nums[idx] *= multiplier;
            pq.offer(idx);
        }
        return nums;
    }
}
