package com.shuangpeng.Problem.p0201_0300;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Problem0239SlidingWindowMaximum {

    // 双向队列
    public int[] maxSlidingWindow0(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int length = nums.length - k + 1;
        int[] result = new int[length];
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i - k + 1 >= 0) {
                if (deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    // 动态规划
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int[] left = new int[nums.length];
        int i = 0;
        int count = 0;
        while (i < nums.length) {
            if (count == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            count++;
            i++;
            count = count % k;
        }
        int[] right = new int[nums.length];
        right[nums.length - 1] = nums[nums.length - 1];
        i = nums.length - 2;
        int start = i / k * k;
        while (i >= 0) {
            if (i == start + k - 1) {
                right[i] = nums[i];
            } else {
                right[i] = Math.max(right[i + 1], nums[i]);
            }
            i--;
            if (i < start) {
                start -= k;
            }
        }
        int[] result = new int[nums.length - k + 1];
        int max;
        for (i = 0; i + k - 1 < nums.length; i++) {
            max = Math.max(right[i], left[i + k - 1]);
            result[i] = max;
        }
        return result;
    }

    // 动态规划（优化）
    public int[] maxSlidingWindow(int[] nums, int k) {
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = nums[0];
        right[length - 1] = nums[length - 1];
        for (int i = 1; i < length; i++) {
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
            int j = length - i - 1;
            if (j % k == k - 1) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }
        int[] result = new int[length - k + 1];
        for (int i = 0; i + k - 1 < length; i++) {
            result[i] = Math.max(right[i], left[i + k - 1]);
        }
        return result;
    }
}
