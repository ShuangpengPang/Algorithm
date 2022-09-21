package com.shuangpeng.Problem.p1601_1700;

import java.util.PriorityQueue;

/**
 * @Description: Problem1675MinimizeDeviationInArray（数组的最小偏移量）
 * @Date 2022/9/21 11:41 AM
 * @Version 1.0
 */
public class Problem1675MinimizeDeviationInArray {

    public int minimumDeviation(int[] nums) {
        int min = Integer.MAX_VALUE;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            if ((num & 1) == 1) {
                num <<= 1;
            }
            q.offer(num);
            min = Math.min(min, num);
        }
        int ans = q.peek() - min;
        while ((q.peek() & 1) == 0) {
            int num = q.poll() >> 1;
            q.offer(num);
            min = Math.min(min, num);
            ans = Math.min(ans, q.peek() - min);
        }
        return ans;
    }
}
