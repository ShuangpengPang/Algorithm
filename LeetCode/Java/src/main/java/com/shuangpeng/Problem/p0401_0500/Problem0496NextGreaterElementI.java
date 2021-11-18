package com.shuangpeng.Problem.p0401_0500;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Problem0496NextGreaterElementI {

    public int[] nextGreaterElement0(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n1; ++i) {
            indexMap.put(nums1[i], i);
        }
        int[] ans = new int[n1];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n2 - 1; i >= 0; --i) {
            int num = nums2[i];
            while (!stack.isEmpty() && stack.peekLast() <= num) {
                stack.pollLast();
            }
            int idx = indexMap.getOrDefault(num, -1);
            if (idx >= 0) {
                ans[idx] = stack.isEmpty() ? -1 : stack.peekLast();
            }
            stack.offerLast(num);
        }
        return ans;
    }
}
