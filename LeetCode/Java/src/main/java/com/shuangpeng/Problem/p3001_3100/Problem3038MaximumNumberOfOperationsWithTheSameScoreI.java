package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3038MaximumNumberOfOperationsWithTheSameScoreI（相同分数的最大操作数目I）
 * @date 2024/4/20 4:17 PM
 */
public class Problem3038MaximumNumberOfOperationsWithTheSameScoreI {

    public int maxOperations(int[] nums) {
        int ans = 1, sum = nums[0] + nums[1];
        for (int n = nums.length, i = 3; i < n && nums[i] + nums[i - 1] == sum; i += 2) {
            ans++;
        }
        return ans;
    }
}
