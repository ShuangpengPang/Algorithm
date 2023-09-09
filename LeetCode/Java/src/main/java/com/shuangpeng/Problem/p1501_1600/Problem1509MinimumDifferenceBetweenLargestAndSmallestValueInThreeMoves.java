package com.shuangpeng.Problem.p1501_1600;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1509MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves（三次操作后最大值与最小值的最小差）
 * @date 2023/8/30 3:25 PM
 */
public class Problem1509MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public int minDifference0(int[] nums) {
        int n = nums.length, N = Integer.MAX_VALUE;
        if (n <= 4) {
            return 0;
        }
        int[] m1 = new int[5], m2 = new int[5];
        for (int i = 0; i < 4; i++) {
            m1[i] = N;
            m2[i] = -N;
        }
        for (int num : nums) {
            int idx = 3;
            while (idx >= 0 && num < m1[idx]) {
                m1[idx + 1] = m1[idx];
                idx--;
            }
            m1[idx + 1] = num;
            idx = 3;
            while (idx >= 0 && num > m2[idx]) {
                m2[idx + 1] = m2[idx];
                idx--;
            }
            m2[idx + 1] = num;
        }
        int ans = N;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, m2[3- i] - m1[i]);
        }
        return ans;
    }

    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a), minQ = new PriorityQueue<>();
        for (int num : nums) {
            if (maxQ.size() < 4 || maxQ.peek() > num) {
                maxQ.offer(num);
                if (maxQ.size() > 4) {
                    maxQ.poll();
                }
            }
            if (minQ.size() < 4 || minQ.peek() < num) {
                minQ.offer(num);
                if (minQ.size() > 4) {
                    minQ.poll();
                }
            }
        }
        int[] left = new int[4], right = new int[4];
        for (int i = 3; i >= 0; i--) {
            left[i] = maxQ.poll();
            right[3 - i] = minQ.poll();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, right[i] - left[i]);
        }
        return ans;
    }
}
