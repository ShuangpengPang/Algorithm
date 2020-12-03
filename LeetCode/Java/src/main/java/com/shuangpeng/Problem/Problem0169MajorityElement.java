package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0169MajorityElement {

    public int majorityElement0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2 + 1);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
            if (map.get(nums[i]) > nums.length / 2) {
                return nums[i];
            }
        }
        return 0;
    }

    // 投票算法
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            count = count + (nums[i] == candidate ? 1 : -1);
        }
        return candidate;
    }
}
