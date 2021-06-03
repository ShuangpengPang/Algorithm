package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0523ContinuousSubarraySum {

    public boolean checkSubarraySum0(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        int[] array = new int[nums.length];
        array[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            array[i] = array[i - 1] + nums[i];
        }
        Map<Integer, List> map = new HashMap<>();
        map.put(0, new ArrayList() {{
            add(-1);
        }});
        for (int i = 0; i < array.length; i++) {
            int mod = array[i] % k;
            List<Integer> list = map.getOrDefault(mod, new ArrayList());
            if (list.size() > 1) {
                return true;
            }
            if (list.size() == 1 && (i - list.get(0)) != 1) {
                return true;
            }
            list.add(i);
            map.putIfAbsent(mod, list);
        }
        return false;
    }

    public boolean checkSubarraySum1(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)) {
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            remainder = (remainder + num) % k;
            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) != 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}
