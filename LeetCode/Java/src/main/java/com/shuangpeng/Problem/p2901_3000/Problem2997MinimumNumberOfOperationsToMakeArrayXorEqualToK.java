package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2997MinimumNumberOfOperationsToMakeArrayXorEqualToK（使数组异或和等于K的最少操作次数）
 * @date 2024/1/14 11:51 AM
 */
public class Problem2997MinimumNumberOfOperationsToMakeArrayXorEqualToK {

    public int minOperations(int[] nums, int k) {
        for (int num : nums) {
            k ^= num;
        }
        return Integer.bitCount(k);
    }
}
