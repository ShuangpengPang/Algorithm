package com.shuangpeng.Problem.p2501_2600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2547MinimumCostToSplitAnArray（拆分数组的最小代价）
 * @date 2024/1/19 4:42 PM
 */
public class Problem2547MinimumCostToSplitAnArray {

    public int minCost(int[] nums, int k) {
        int n = nums.length, N = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int cost = k;
            dp[i] = N;
            for (int j = i; j > 0; j--) {
                int cnt = map.merge(nums[j - 1], 1, Integer::sum);
                if (cnt == 2) {
                    cost += 2;
                } else if (cnt > 2) {
                    cost++;
                }
                dp[i] = Math.min(dp[i], dp[j - 1] + cost);
            }
        }
        return dp[n];
    }
}
