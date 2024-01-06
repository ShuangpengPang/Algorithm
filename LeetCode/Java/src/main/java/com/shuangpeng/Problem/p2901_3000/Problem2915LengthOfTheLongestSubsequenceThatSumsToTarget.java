package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2915LengthOfTheLongestSubsequenceThatSumsToTarget（和为目标值的最长子序列的长度）
 * @date 2024/1/6 8:17 PM
 */
public class Problem2915LengthOfTheLongestSubsequenceThatSumsToTarget {

    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        int s = 0;
        for (int num : nums) {
            s = Math.min(s + num, target);
            for (int i = s; i >= num; i--) {
                dp[i] = Math.max(dp[i], dp[i - num] + 1);
            }
        }
        return dp[target] < 0 ? -1 : dp[target];
    }
}
