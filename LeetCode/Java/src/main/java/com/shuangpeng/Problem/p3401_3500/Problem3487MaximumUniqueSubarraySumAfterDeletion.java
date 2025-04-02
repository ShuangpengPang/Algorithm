package com.shuangpeng.Problem.p3401_3500;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3487MaximumUniqueSubarraySumAfterDeletion（删除后的最大子数组元素和）
 * @date 2025/4/2 10:19
 */
public class Problem3487MaximumUniqueSubarraySumAfterDeletion {

    public int maxSum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int sum = nums[0];
        set.add(nums[0]);
        for (int i = 1, n = nums.length; i < n; i++) {
            if (set.add(nums[i])) {
                sum = Math.max(sum, Math.max(sum + nums[i], nums[i]));
            }
        }
        return sum;
    }
}
