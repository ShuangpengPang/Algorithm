package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1749MaximumAbsoluteSumOfAnySubarray（任意子数组和的绝对值的最大值）
 * @date 2023/8/8 10:29 AM
 */
public class Problem1749MaximumAbsoluteSumOfAnySubarray {

    public int maxAbsoluteSum(int[] nums) {
        int min = 0, max = 0, s1 = 0, s2 = 0;
        for (int num : nums) {
            s1 = Math.min(s1, 0) + num;
            s2 = Math.max(s2, 0) + num;
            min = Math.min(min, s1);
            max = Math.max(max, s2);
        }
        return Math.max(-min, max);
    }
}
