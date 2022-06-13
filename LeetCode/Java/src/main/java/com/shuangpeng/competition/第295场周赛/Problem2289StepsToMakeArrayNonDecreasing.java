package com.shuangpeng.competition.第295场周赛;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem2289StepsToMakeArrayNonDecreasing（使数组按非递减顺序排序）
 * @Date 2022/6/13 3:09 PM
 * @Version 1.0
 */
public class Problem2289StepsToMakeArrayNonDecreasing {

    public int totalSteps(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] counts = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int max = 0;
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                max = Math.max(max, counts[stack.pop()]);
            }
            counts[i] = stack.isEmpty() ? 0 : max + 1;
            ans = Math.max(ans, counts[i]);
            stack.push(i);
        }
        return ans;
    }
}
