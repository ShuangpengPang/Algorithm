package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3040MaximumNumberOfOperationsWithTheSameScoreII（相同分数的最大操作数目II）
 * @date 2024/2/23 12:01 PM
 */
public class Problem3040MaximumNumberOfOperationsWithTheSameScoreII {

    public int maxOperations(int[] nums) {
        int n = nums.length;
        if (n == 2) {
            return 1;
        }
        return Math.max(getCount(nums, nums[0] + nums[n - 1]),
                Math.max(getCount(nums, nums[0] + nums[1]), getCount(nums, nums[n - 2] + nums[n - 1])));
    }

    private int getCount(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            boolean flag = nums[i] + nums[i + 1] == sum;
            dp[i][i + 1] = flag ? 1 : 0;
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = flag ? 1 + dp[i + 2][j] : 0;
                if (nums[j - 1] + nums[j] == sum) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 2] + 1);
                }
                if (nums[i] + nums[j] == sum) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 1);
                }
            }
        }
        return dp[0][n - 1];
    }
}
