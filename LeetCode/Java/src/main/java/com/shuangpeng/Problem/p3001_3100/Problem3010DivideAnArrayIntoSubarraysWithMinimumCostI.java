package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3010DivideAnArrayIntoSubarraysWithMinimumCostI（将数组分成最小总代价的子数组I）
 * @date 2024/4/24 12:06 AM
 */
public class Problem3010DivideAnArrayIntoSubarraysWithMinimumCostI {

    public int minimumCost(int[] nums) {
        int n = nums.length, first = Integer.MAX_VALUE, second = first;
        for (int i = 1; i < n; i++) {
            if (nums[i] < first) {
                second = first;
                first = nums[i];
            } else if (nums[i] < second) {
                second = nums[i];
            }
        }
        return nums[0] + first + second;
    }
}
