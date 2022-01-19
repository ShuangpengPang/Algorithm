package com.shuangpeng.Problem.p0201_0300;

import java.util.HashSet;
import java.util.Set;

public class Problem0219ContainsDuplicateII {

    public boolean containsNearbyDuplicate0(int[] nums, int k) {
        if (k == 0) {
            return false;
        }
        int n = nums.length;
        Set<Integer> set = new HashSet<>(Math.min(k, n));
        for (int i = 0; i < k && i < n; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        for (int i = k; i < n; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            set.remove(nums[i - k]);
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>(Math.min(k, n));
        for (int i = 0; i < n; ++i) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
