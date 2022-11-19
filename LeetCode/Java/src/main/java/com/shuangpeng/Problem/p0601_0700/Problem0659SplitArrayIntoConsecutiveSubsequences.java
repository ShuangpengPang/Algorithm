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

    public boolean isPossible0(int[] nums) {
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

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (count.get(num) == 0) {
                continue;
            }
            if (map.getOrDefault(num - 1, 0) > 0) {
                map.put(num, map.getOrDefault(num , 0) + 1);
                map.put(num - 1, map.get(num - 1) - 1);
                count.put(num, count.get(num) - 1);
            } else if (count.getOrDefault(num + 1, 0) == 0 || count.getOrDefault(num + 2, 0) == 0) {
                return false;
            } else {
                count.put(num, count.get(num) - 1);
                count.put(num + 1, count.get(num + 1) - 1);
                count.put(num + 2, count.get(num + 2) - 1);
                map.put(num + 2, map.getOrDefault(num + 2, 0) + 1);
            }
        }
        return true;
    }
}
