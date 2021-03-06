package com.shuangpeng.Problem;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Problem0503NextGreaterElementII {

    public int[] nextGreaterElements0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peekLast()] < num) {
                answer[stack.pollLast()] = num;
            }
            stack.offerLast(i);
        }
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peekLast()] < num) {
                int index = stack.pollLast();
                if (index > i) {
                    answer[index] = num;
                }
            }
            stack.offerLast(i);
        }
        return answer;
    }

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            int j = i % n;
            while (!stack.isEmpty() && nums[stack.peekLast()] < nums[j]) {
                answer[stack.pollLast()] = nums[j];
            }
            stack.offerLast(j);
        }
        return answer;
    }
}
