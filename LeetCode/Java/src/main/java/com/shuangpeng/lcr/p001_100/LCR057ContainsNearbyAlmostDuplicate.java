package com.shuangpeng.lcr.p001_100;

import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR057ContainsNearbyAlmostDuplicate（存在重复元素III）
 * @date 2024/6/24 2:16 PM
 */
public class LCR057ContainsNearbyAlmostDuplicate {

    public boolean containsNearbyAlmostDuplicate0(int[] nums, int k, int t) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int n = nums.length, i = 0; i < n; i++) {
            if (i > k) {
                int cnt = treeMap.get(nums[i - k - 1]) - 1;
                if (cnt == 0) {
                    treeMap.remove(nums[i - k - 1]);
                } else {
                    treeMap.put(nums[i - k - 1], cnt);
                }
            }
            Integer ceil = treeMap.ceilingKey(nums[i]), lower = treeMap.lowerKey(nums[i]);
            if (ceil != null && (long) ceil - nums[i] <= t || lower != null && (long) nums[i] - lower <= t) {
                return true;
            }
            treeMap.merge(nums[i], 1, Integer::sum);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        for (int n = nums.length, i = 0; i < n; i++) {
            Long ceiling = treeMap.ceilingKey((long) nums[i] - t);
            if (ceiling != null && ceiling <= (long) nums[i] + t) {
                return true;
            }
            treeMap.merge((long) nums[i], 1, Integer::sum);
            if (i >= k) {
                int cnt = treeMap.get((long) nums[i - k]) - 1;
                if (cnt == 0) {
                    treeMap.remove((long) nums[i - k]);
                } else {
                    treeMap.put((long) nums[i - k], cnt);
                }
            }
        }
        return false;
    }
}
