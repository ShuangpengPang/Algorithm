package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3065MinimumOperationsToExceedThresholdValueI（超过阈值的最少操作数I）
 * @date 2024/4/20 10:43 AM
 */
public class Problem3065MinimumOperationsToExceedThresholdValueI {

    public int minOperations(int[] nums, int k) {
        int ans = 0;
        for (int num : nums) {
            if (num < k) {
                ans++;
            }
        }
        return ans;
    }
}
