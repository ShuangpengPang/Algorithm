package com.shuangpeng.Problem.p3401_3500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3409LongestSubsequenceWithDecreasingAdjacentDifference（最长相邻绝对差递减子序列）
 * @date 2025/4/11 12:06
 */
public class Problem3409LongestSubsequenceWithDecreasingAdjacentDifference {

    public int longestSubsequence(int[] nums) {
        int n = nums.length, mx = 0, mi = Integer.MAX_VALUE;
        for (int num : nums) {
            mx = Math.max(mx, num);
            mi = Math.min(mi, num);
        }
        int maxD = mx - mi;
        int[][] dp = new int[n][maxD + 2];
        int[] last = new int[mx + 1];
        Arrays.fill(last, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = maxD; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i][j + 1], 1);
                if (x - j >= 0 && last[x - j] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[last[x - j]][j] + 1);
                }
                if (x + j <= mx && last[x + j] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[last[x + j]][j] + 1);
                }
                ans = Math.max(ans, dp[i][j]);
            }
            last[x] = i;
        }
        return ans;
    }
}
