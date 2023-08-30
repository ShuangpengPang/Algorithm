package com.shuangpeng.Problem.p1501_1600;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1509MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves（三次操作后最大值与最小值的最小差）
 * @date 2023/8/30 3:25 PM
 */
public class Problem1509MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public int minDifference(int[] nums) {
        if (nums.length <= 3) {
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
