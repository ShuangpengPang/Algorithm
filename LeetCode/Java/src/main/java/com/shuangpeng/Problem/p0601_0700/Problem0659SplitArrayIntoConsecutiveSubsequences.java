package com.shuangpeng.Problem.p0601_0700;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0659SplitArrayIntoConsecutiveSubsequences（分割数组为连续子序列）
 * @date 2022/11/17 6:09 PM
 */
public class Problem0659SplitArrayIntoConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            PriorityQueue<Integer> q = map.get(num - 1);
            map.computeIfAbsent(num, k -> new PriorityQueue<>()).offer(q == null || q.isEmpty() ? 1 : q.poll() + 1);
        }
        for (PriorityQueue<Integer> q : map.values()) {
            if (!q.isEmpty() && q.peek() < 3) {
                return false;
            }
        }
        return true;
    }
}
