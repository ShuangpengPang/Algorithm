package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1413MinimumValueToGetPositiveStepByStepSum（逐步求和得到正数的最小值）
 * @Date 2022/8/9 10:13 AM
 * @Version 1.0
 */
public class Problem1413MinimumValueToGetPositiveStepByStepSum {

    public int minStartValue(int[] nums) {
        int n = nums.length, min = nums[0];
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
            min = Math.min(min, nums[i]);
        }
        return min < 1 ? 1 - min : 1;
    }
}
