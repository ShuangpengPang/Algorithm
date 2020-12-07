package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.Set;

public class Problem0217ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                return true;
            }
        }
        return false;
    }
}
