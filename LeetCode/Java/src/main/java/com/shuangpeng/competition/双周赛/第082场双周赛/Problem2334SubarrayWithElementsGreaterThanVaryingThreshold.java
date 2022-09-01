package com.shuangpeng.competition.双周赛.第082场双周赛;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

/**
 * @Description: Problem2334SubarrayWithElementsGreaterThanVaryingThreshold（元素值大于变化阈值的子数组）
 * @Date 2022/7/17 4:04 PM
 * @Version 1.0
 */
public class Problem2334SubarrayWithElementsGreaterThanVaryingThreshold {

    int[] fa, sz;

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        fa = new int[n + 1];
        sz = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
            sz[i] = 1;
        }
        Integer[] ids = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids, (a, b) -> nums[b] - nums[a]);
        for (int i : ids) {
            int j = find(i + 1);
            fa[i] = j;
            sz[j] += sz[i];
            if (nums[i] > threshold / (sz[j] - 1)) {
                return sz[j] - 1;
            }
        }
        return -1;
    }

    int find(int x) {
        return fa[x] = x == fa[x] ? x : find(fa[x]);
    }
}

class Problem2334SubarrayWithElementsGreaterThanVaryingThreshold0 {

    public int validSubarraySize0(int[] nums, int threshold) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!stack.isEmpty() && nums[stack.peek()] >= num) {
                int j = stack.pop();
                int cnt = i - 1 - (stack.isEmpty() ? -1 : stack.peek());
                if (nums[j] > threshold / cnt) {
                    return cnt;
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int i = stack.pop();
            int cnt = n - 1 - (stack.isEmpty() ? -1 : stack.peek());
            if (nums[i] > threshold / cnt) {
                return cnt;
            }
        }
        return -1;
    }

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length, top = 0;
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            while (top > 0 && nums[stack[top - 1]] >= nums[i]) {
                int num = nums[stack[top - 1]];
                top--;
                int cnt = i - 1 - (top > 0 ? stack[top - 1] : -1);
                if (num > threshold / cnt) {
                    return cnt;
                }
            }
            stack[top++] = i;
        }
        while (top > 0) {
            int num = nums[stack[top - 1]];
            top--;
            int cnt = n - 1 - (top > 0 ? stack[top - 1] : -1);
            if (num > threshold / cnt) {
                return cnt;
            }
        }
        return -1;
    }
}
