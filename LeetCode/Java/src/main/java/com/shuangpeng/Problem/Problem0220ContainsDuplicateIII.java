package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Problem0220ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate0(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        if (k <= 0) {
            return false;
        }
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long ceil = set.ceiling((long) nums[i] - t);
            if (ceil != null && ceil <= (long) nums[i] + t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<Long, Long>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k <= 0) {
            return false;
        }
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        long w = (long) t + 1;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && ((long) nums[i] - map.get(id - 1)) <= t) {
                return true;
            }
            if (map.containsKey(id + 1) && ((long) map.get(id + 1) - nums[i]) <= t) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }
}
