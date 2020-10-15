package com.shuangpeng;

import java.util.*;

public class Problem0015ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int data = nums[i];
            if (!set.contains(data)) {
                List<List<Integer>> lists = twoSum(nums, i + 1, -data);
                for (int j = 0; j < lists.size(); j++) {
                    List<Integer> item = lists.get(j);
                    if (!set.contains(item.get(0)) && !set.contains(item.get(1))) {
                        item.add(data);
                        result.add(item);
                    }
                }
                set.add(data);
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = start; i < length; i++) {
            int data = nums[i];
            int other = target - data;
            if (map.containsKey(other)) {
                if (!map.get(other)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(other);
                    list.add(data);
                    result.add(list);
                    map.put(data, true);
                    map.put(other, true);
                }
            } else if (!map.containsKey(data)) {
                map.put(data, false);
            }
        }
        return result;
    }
}
