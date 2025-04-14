package com.shuangpeng.Problem.p3401_3500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3409LongestSubsequenceWithDecreasingAdjacentDifference（最长相邻绝对差递减子序列）
 * @date 2025/4/11 12:06
 */
public class Problem3409LongestSubsequenceWithDecreasingAdjacentDifference {

    public int longestSubsequence0(int[] nums) {
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

    public int longestSubsequence(int[] nums) {
        int mx = 0, mi = Integer.MAX_VALUE;
        for (int x : nums) {
            mx = Math.max(mx, x);
            mi = Math.min(mi, x);
        }
        int maxD = mx - mi;
        int[][] dp = new int[mx + 1][maxD + 1];
        int ans = 0;
        for (int i : nums) {
            int x = 1;
            for (int j = maxD; j >= 0; j--) {
                if (i - j >= 0) {
                    x = Math.max(x, dp[i - j][j] + 1);
                }
                if (i + j <= mx) {
                    x = Math.max(x, dp[i + j][j] + 1);
                }
                dp[i][j] = x;
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}
