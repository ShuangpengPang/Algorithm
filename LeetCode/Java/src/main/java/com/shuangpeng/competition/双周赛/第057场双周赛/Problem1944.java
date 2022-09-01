package com.shuangpeng.competition.双周赛.第057场双周赛;

import java.util.Deque;
import java.util.LinkedList;

public class Problem1944 {

    public int[] canSeePersonsCount0(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] < heights[i]) {
                counts[stack.pollLast()]++;
            }
            if (!stack.isEmpty()) {
                counts[stack.peekLast()]++;
            }
            stack.offerLast(i);
        }
        return counts;
    }

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] counts = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peekLast()] < heights[i]) {
                counts[i]++;
                stack.pollLast();
            }
            if (!stack.isEmpty()) {
                counts[i]++;
            }
            stack.offerLast(i);
        }
        return counts;
    }
}
