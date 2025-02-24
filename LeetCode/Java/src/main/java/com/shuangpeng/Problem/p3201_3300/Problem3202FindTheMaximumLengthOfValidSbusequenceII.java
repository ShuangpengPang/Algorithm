package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3202FindTheMaximumLengthOfValidSbusequenceII（找出有效子序列的最大长度II）
 * @date 2025/2/24 4:05 PM
 */
public class Problem3202FindTheMaximumLengthOfValidSbusequenceII {

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[k][k];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int m = nums[i] % k;
                dp[j][m] = Math.max(dp[j][m], dp[j][(j - m + k) % k] + 1);
                ans = Math.max(ans, dp[j][m]);
            }
        }
        return ans;
    }
}
