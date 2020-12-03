package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem0128LongestConsecutiveSequence {

    public int longestConsecutive0(int[] nums) {
//        100,4,200,1,3,2
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Boolean> visited = new HashMap<>(nums.length);
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            visited.put(nums[i], false);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited.get(nums[i]) && !result.containsKey(nums[i])) {
                int lenth = 0;
                while (visited.containsKey(nums[i] + lenth)) {
                    visited.put(nums[i] + lenth, true);
                    if (result.containsKey(nums[i] + lenth)) {
                        lenth = lenth + result.get(nums[i] + lenth);
                        break;
                    }
                    lenth++;
                }
                result.put(nums[i], lenth);
            }
        }
        int max = 1;
        for (int key : result.keySet()) {
            max = Math.max(max, result.get(key));
        }
        return max;
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) {
                int j = 0;
                while (set.contains(nums[i] + j)) {
                    j++;
                }
                max = Math.max(max, j);
            }
        }
        return max;
    }
}
