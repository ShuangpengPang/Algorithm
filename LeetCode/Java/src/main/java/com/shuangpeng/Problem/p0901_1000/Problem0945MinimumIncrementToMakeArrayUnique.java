package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0945MinimumIncrementToMakeArrayUnique（使数组唯一的最小增量）
 * @date 2022/12/9 6:06 PM
 */
public class Problem0945MinimumIncrementToMakeArrayUnique {

    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0, prev = -1;
        for (int i = 0; i < n; i++) {
            ans += Math.max(prev + 1 - nums[i], 0);
            prev = Math.max(prev + 1, nums[i]);
        }
        return ans;
    }
}
