package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3251FindTheCountOfMonotonicPairsII（单调数组对的数目II）
 * @date 2024/11/29 11:50 AM
 */
public class Problem3251FindTheCountOfMonotonicPairsII {

    public int countOfPairs(int[] nums) {
        int n = nums.length, N = (int) 1e9 + 7;
        int[][] dp = new int[2][1001];
        Arrays.fill(dp[0], 0, nums[0] + 1, 1);
        for (int i = 1; i < n; i++) {
            int index = i & 1, prev = index ^ 1;
            int sum = 0;
            for (int j = 0, k = Math.min(0, nums[i - 1] - nums[i]); j <= nums[i]; j++, k++) {
                if (k >= 0) {
                    sum = (sum + dp[prev][k]) % N;
                }
                dp[index][j] = sum;
            }
        }
        int ans = 0, num = nums[n - 1];
        int[] arr = dp[n - 1 & 1];
        for (int i = 0; i <= num; i++) {
            ans = (ans + arr[i]) % N;
        }
        return ans;
    }
}
