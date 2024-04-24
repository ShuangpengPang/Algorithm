package com.shuangpeng.Problem.p2901_3000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2996SmallestMissingIntegerGreaterThanSequentialPrefixSum（大于等于顺序前缀和的最小缺失整数）
 * @date 2024/4/24 12:21 AM
 */
public class Problem2996SmallestMissingIntegerGreaterThanSequentialPrefixSum {

    public int missingInteger(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int x = nums[0], n = nums.length, i = 1;
        while (i < n && nums[i] == nums[i - 1] + 1) {
            x += nums[i++];
        }
        while (set.contains(x)) {
            x++;
        }
        return x;
    }
}
