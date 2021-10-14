package com.shuangpeng.competition.第262场周赛;

import java.util.*;

public class Problem2032 {

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();
        addToSet(set1, nums1);
        addToSet(set2, nums2);
        addToSet(set3, nums3);
        Map<Integer, Integer> map = new HashMap<>();
        addToMap(map, set1);
        addToMap(map, set2);
        addToMap(map, set3);
        List<Integer> ans = new ArrayList<>();
        for (int num : map.keySet()) {
            if (map.get(num) >= 2) {
                ans.add(num);
            }
        }
        return ans;
    }

    private void addToSet(Set<Integer> set, int[] nums) {
        for (int num : nums) {
            set.add(num);
        }
    }

    private void addToMap(Map<Integer, Integer> map, Set<Integer> set) {
        for (int num : set) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }
}
