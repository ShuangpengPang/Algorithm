package com.shuangpeng.Problem;

import java.util.*;

public class Problem0560SubarraySumEqualsK {

    public int subarraySum0(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>(nums.length);
        int[] array = new int[nums.length];
        int sum = 0;
        map.put(0, new ArrayList<>());
        map.get(0).add(-1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) {
                map.put(sum, new ArrayList<>());
            }
            map.get(sum).add(i);
            array[i] = sum;
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int target = array[i] - k;
            if (map.containsKey(target)) {
                List<Integer> list = map.get(target);
                for (int index : list) {
                    if (index < i) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
