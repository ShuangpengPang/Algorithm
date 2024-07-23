package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3190FindMinimumOperationsToMakeAllElementsDivisibleByThree（使所有元素都可以被3整除的最少操作数）
 * @date 2024/7/24 12:26 AM
 */
public class Problem3190FindMinimumOperationsToMakeAllElementsDivisibleByThree {

    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans += num % 3 + 1 >> 1;
        }
        return ans;
    }
}
