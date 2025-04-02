package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3366MinimumArraySum（最小数组和）
 * @date 2025/4/2 15:20
 */
public class Problem3366MinimumArraySum {

    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length, N = Integer.MAX_VALUE >> 1;
        int[][] dp = new int[op1 + 1][op2 + 1];
        for (int i = 0; i <= op1; i++) {
            Arrays.fill(dp[i], N);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int o1 = Math.min(op1, i); o1 >= 0; o1--) {
                for (int o2 = Math.min(op2, i); o2 >= 0; o2--) {
                    dp[o1][o2] += nums[i - 1];
                    int x = nums[i - 1] - (nums[i - 1] >> 1);
                    if (o1 > 0) {
                        dp[o1][o2] = Math.min(dp[o1][o2], dp[o1 - 1][o2] + x);
                    }
                    if (nums[i - 1] >= k && o2 > 0) {
                        dp[o1][o2] = Math.min(dp[o1][o2], dp[o1][o2 - 1] + nums[i - 1] - k);
                    }
                    if (o1 > 0 && o2 > 0 && nums[i - 1] >= k) {
                        int y = nums[i - 1] - k;
                        dp[o1][o2] = Math.min(dp[o1][o2], dp[o1 - 1][o2 - 1] + y - (y >> 1));
                        if (x >= k) {
                            dp[o1][o2] = Math.min(dp[o1][o2], dp[o1 - 1][o2 - 1] + x - k);
                        }
                    }
                }
            }
        }
        int ans = N;
        for (int i = 0; i <= op1; i++) {
            for (int j = 0; j <= op2; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
