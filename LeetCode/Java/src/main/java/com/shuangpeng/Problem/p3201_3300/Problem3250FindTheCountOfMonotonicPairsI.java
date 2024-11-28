package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3250FindTheCountOfMonotonicPairsI（单调数组对的数目I）
 * @date 2024/11/28 10:39 AM
 */
public class Problem3250FindTheCountOfMonotonicPairsI {

    public int countOfPairs(int[] nums) {
        int n = nums.length, N = (int) 1e9 + 7;
        int[][] dp = new int[2][51];
        for (int i = 0; i <= nums[0]; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            int index = i & 1, prevIndex = index ^ 1;
            int num = nums[i], prev = nums[i - 1];
            int sum = 0;
            for (int j = 0, k = Math.min(prev - num, 0); j <= num; j++, k++) {
                if (k >= 0) {
                    sum = (sum + dp[prevIndex][k]) % N;
                }
                dp[index][j] = sum;
            }
        }
        int ans = 0;
        int[] arr = dp[n - 1 & 1];
        for (int i = 0; i <= nums[n - 1]; i++) {
            ans = (ans + arr[i]) % N;
        }
        return ans;
    }
}
